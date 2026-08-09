package com.globitel.task.management.system.core.comment.usecase.main.admin;

import com.globitel.task.management.system.core.comment.dao.CommentDao;
import com.globitel.task.management.system.core.comment.factory.request.CreateInitCommentRequest;
import com.globitel.task.management.system.core.comment.factory.response.CommentResponse;
import com.globitel.task.management.system.core.comment.identity.CommentIdentity;
import com.globitel.task.management.system.core.notification.factory.domain.NotificationUseCaseFactoryDomain;
import com.globitel.task.management.system.core.notification.factory.domain.request.CreateNotificationRequest;
import com.globitel.task.management.system.core.notification.factory.respose.NotificationResponse;
import com.globitel.task.management.system.core.notification.identity.NotificationIdentity;
import com.globitel.task.management.system.core.shared.security.context.TMSSecurityContext;
import com.globitel.task.management.system.core.shared.socket.TMSWebSocket;
import com.globitel.task.management.system.core.shared.usecase.ValidatorFunctionalUseCase;
import com.globitel.task.management.system.core.task.factory.domain.TaskUseCaseFactoryDomain;
import com.globitel.task.management.system.core.task.identity.TaskIdentity;
import com.globitel.task.management.system.core.user.factory.domain.UserUseCaseFactoryDomain;
import com.globitel.task.management.system.core.user.identity.UserIdentity;
import jakarta.validation.Validator;

public class CreateInitCommentByAdminUseCase extends ValidatorFunctionalUseCase<CommentResponse, CreateInitCommentRequest> {


    private final TMSSecurityContext tmsSecurityContext;
    private final CommentDao commentDao;
    private final TaskUseCaseFactoryDomain taskUseCaseFactoryDomain;
    private final UserUseCaseFactoryDomain userUseCaseFactoryDomain;
    private final NotificationUseCaseFactoryDomain notificationUseCaseFactoryDomain;
    private final TMSWebSocket tmsWebSocket;
    public CreateInitCommentByAdminUseCase(Validator validator, TMSSecurityContext tmsSecurityContext, CommentDao commentDao, TaskUseCaseFactoryDomain taskUseCaseFactoryDomain, UserUseCaseFactoryDomain userUseCaseFactoryDomain, NotificationUseCaseFactoryDomain notificationUseCaseFactoryDomain, TMSWebSocket tmsWebSocket) {
        super(validator);
        this.tmsSecurityContext = tmsSecurityContext;
        this.commentDao = commentDao;
        this.taskUseCaseFactoryDomain = taskUseCaseFactoryDomain;
        this.userUseCaseFactoryDomain = userUseCaseFactoryDomain;
        this.notificationUseCaseFactoryDomain = notificationUseCaseFactoryDomain;
        this.tmsWebSocket = tmsWebSocket;
    }

    @Override
    protected CommentResponse doExecute(CreateInitCommentRequest request) {
        String userName = tmsSecurityContext.getCurrentUserUserName();
        UserIdentity userIdentity = userUseCaseFactoryDomain.findUserByUsernameUseCase().execute(userName);

        TaskIdentity taskIdentity = taskUseCaseFactoryDomain.findTaskByUuidUseCase().execute(request.taskUuid());

        CommentIdentity newComment = commentDao.creator()
                .task(taskIdentity)
                .user(userIdentity)
                .content(request.content())
                .create();

        CommentIdentity savedComment = commentDao.save(newComment);

        NotificationIdentity notificationIdentity=notificationUseCaseFactoryDomain
                .createNotificationUseCase().execute(
                        new CreateNotificationRequest(
                                savedComment.getTask().getUser(),
                                "admin comment on your "+savedComment.getTask().getTitle() + "task",
                                "new comment in "+savedComment.getTask().getTitle() + "task",
                                " check it "
                        )
                );

        tmsWebSocket.sendMessage(savedComment.getTask().getUser().getUserName(),
                new NotificationResponse(
                        notificationIdentity.getUuid(),
                        notificationIdentity.getTitle(),
                        notificationIdentity.getMessage(),
                        notificationIdentity.getAction(),
                        notificationIdentity.getCreatedAt(),
                        notificationIdentity.isRead()
                ));

        return new CommentResponse(
                newComment.getUuid(),
                newComment.getUser().getName(),
                newComment.getContent(),
                newComment.getUser().getUserRole(),
                newComment.getTask().getUuid(),
                null
        );
    }
}
