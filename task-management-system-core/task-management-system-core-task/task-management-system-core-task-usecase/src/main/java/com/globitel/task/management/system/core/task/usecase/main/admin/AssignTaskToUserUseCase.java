package com.globitel.task.management.system.core.task.usecase.main.admin;

import com.globitel.task.management.system.core.notification.factory.domain.NotificationUseCaseFactoryDomain;
import com.globitel.task.management.system.core.notification.factory.domain.request.CreateNotificationRequest;
import com.globitel.task.management.system.core.notification.factory.respose.NotificationResponse;
import com.globitel.task.management.system.core.notification.identity.NotificationIdentity;
import com.globitel.task.management.system.core.shared.exception.BadRequestException;
import com.globitel.task.management.system.core.shared.socket.TMSWebSocket;
import com.globitel.task.management.system.core.shared.usecase.ValidatorFunctionalUseCase;
import com.globitel.task.management.system.core.task.dao.TaskDao;
import com.globitel.task.management.system.core.task.factory.request.AssignTaskToUserRequest;
import com.globitel.task.management.system.core.task.factory.response.TaskResponse;
import com.globitel.task.management.system.core.task.identity.TaskIdentity;
import com.globitel.task.management.system.core.task.usecase.error.TaskError;
import com.globitel.task.management.system.core.user.factory.domain.UserUseCaseFactoryDomain;
import com.globitel.task.management.system.core.user.identity.UserIdentity;
import jakarta.validation.Validator;

public class AssignTaskToUserUseCase  extends ValidatorFunctionalUseCase<TaskResponse, AssignTaskToUserRequest> {

    private final TaskDao taskDao;
    private final UserUseCaseFactoryDomain  userUseCaseFactoryDomain;
    private final NotificationUseCaseFactoryDomain notificationUseCaseFactoryDomain;
    private final TMSWebSocket tmsWebSocket;
    public AssignTaskToUserUseCase(Validator validator, UserUseCaseFactoryDomain userUseCaseFactoryDomain,TaskDao taskDao,NotificationUseCaseFactoryDomain notificationUseCaseFactoryDomain,TMSWebSocket tmsWebSocket ) {
        super(validator);
        this.userUseCaseFactoryDomain = userUseCaseFactoryDomain;
        this.taskDao = taskDao;
        this.notificationUseCaseFactoryDomain = notificationUseCaseFactoryDomain;
        this.tmsWebSocket = tmsWebSocket;
    }

    @Override
    protected TaskResponse doExecute(AssignTaskToUserRequest request) {

        UserIdentity userIdentity=userUseCaseFactoryDomain.findUserByIdUseCase()
                .execute(request.userUuid());

        TaskIdentity taskIdentity=taskDao.find(request.taskUuid())
                .orElseThrow(()->new BadRequestException(TaskError.TASK_NOT_FOUND));

        TaskIdentity updatedTask=taskDao.updater(taskIdentity)
                .user(userIdentity)
                .update();

        TaskIdentity savedTask=taskDao.save(updatedTask);

        NotificationIdentity notificationIdentity=notificationUseCaseFactoryDomain
                .createNotificationUseCase().execute(
                        new CreateNotificationRequest(
                                savedTask.getUser(),
                                "Assigned to task",
                                "Admin assigned you to "+taskIdentity.getTitle(),
                                "make it before the deadline in "+taskIdentity.getDueDate()
                        )
                );

        tmsWebSocket.sendMessage(savedTask.getUser().getUserName(),
                new NotificationResponse(
                        notificationIdentity.getUuid(),
                        notificationIdentity.getTitle(),
                        notificationIdentity.getMessage(),
                        notificationIdentity.getAction(),
                        notificationIdentity.getCreatedAt(),
                        notificationIdentity.isRead()
                ));

        return new TaskResponse(
                savedTask.getUuid(),
                savedTask.getTitle(),
                savedTask.getDescription(),
                savedTask.getUser().getUuid(),
                savedTask.getUser().getName(),
                savedTask.getDueDate(),
                savedTask.getTaskStatus()
        );
    }
}
