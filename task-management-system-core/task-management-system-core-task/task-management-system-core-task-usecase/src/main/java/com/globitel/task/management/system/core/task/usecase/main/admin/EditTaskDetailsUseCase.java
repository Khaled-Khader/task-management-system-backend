package com.globitel.task.management.system.core.task.usecase.main.admin;

import com.globitel.task.management.system.core.notification.factory.domain.NotificationUseCaseFactoryDomain;
import com.globitel.task.management.system.core.notification.factory.domain.request.CreateNotificationRequest;
import com.globitel.task.management.system.core.notification.factory.respose.NotificationResponse;
import com.globitel.task.management.system.core.notification.identity.NotificationIdentity;
import com.globitel.task.management.system.core.shared.exception.BadRequestException;
import com.globitel.task.management.system.core.shared.socket.TMSWebSocket;
import com.globitel.task.management.system.core.shared.usecase.ValidatorFunctionalUseCase;
import com.globitel.task.management.system.core.task.dao.TaskDao;
import com.globitel.task.management.system.core.task.factory.request.EditTaskDetailsRequest;
import com.globitel.task.management.system.core.task.factory.response.TaskResponse;
import com.globitel.task.management.system.core.task.identity.TaskIdentity;
import com.globitel.task.management.system.core.task.usecase.error.TaskError;
import jakarta.validation.Validator;

public class EditTaskDetailsUseCase extends ValidatorFunctionalUseCase<TaskResponse, EditTaskDetailsRequest> {

    private final TaskDao  taskDao;
    private final NotificationUseCaseFactoryDomain notificationUseCaseFactoryDomain;
    private final TMSWebSocket tmsWebSocket;

    public EditTaskDetailsUseCase(Validator validator, TaskDao taskDao,NotificationUseCaseFactoryDomain notificationUseCaseFactoryDomain, TMSWebSocket tmsWebSocket ) {
        super(validator);
        this.taskDao = taskDao;
        this.notificationUseCaseFactoryDomain = notificationUseCaseFactoryDomain;
        this.tmsWebSocket = tmsWebSocket;
    }

    @Override
    protected TaskResponse doExecute(EditTaskDetailsRequest request) {

        TaskIdentity taskIdentity=taskDao.find(request.uuid())
                .orElseThrow(()->new BadRequestException(TaskError.TASK_NOT_FOUND));

        TaskIdentity updatedTask=taskDao.updater(taskIdentity)
                .title(request.title())
                .description(request.description())
                .taskStatus(request.status())
                .dueDate(request.dueDate())
                .update();

        TaskIdentity savedTask=taskDao.save(updatedTask);

        if(request.status()!=null){
            NotificationIdentity notificationIdentity=notificationUseCaseFactoryDomain
                    .createNotificationUseCase().execute(
                            new CreateNotificationRequest(
                                    savedTask.getUser(),
                                    "task data changed",
                                    "Admin change task data ",
                                    " check it "
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
        }


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
