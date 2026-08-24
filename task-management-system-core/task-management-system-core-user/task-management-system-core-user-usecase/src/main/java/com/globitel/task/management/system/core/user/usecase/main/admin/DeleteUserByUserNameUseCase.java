package com.globitel.task.management.system.core.user.usecase.main.admin;

import com.globitel.task.management.system.core.activitylog.factory.domain.ActivityLogUseCaseFactoryDomain;
import com.globitel.task.management.system.core.comment.factory.domain.CommentUseCaseFactoryDomain;
import com.globitel.task.management.system.core.notification.factory.domain.NotificationUseCaseFactoryDomain;
import com.globitel.task.management.system.core.shared.exception.BadRequestException;
import com.globitel.task.management.system.core.shared.technologies.TransactionExecutor;
import com.globitel.task.management.system.core.shared.usecase.ValidatorFunctionalUseCase;
import com.globitel.task.management.system.core.task.factory.domain.TaskUseCaseFactoryDomain;
import com.globitel.task.management.system.core.token.factory.domain.TokenUseCaseFactoryDomain;
import com.globitel.task.management.system.core.user.dao.UserDao;
import com.globitel.task.management.system.core.user.factory.request.DeleteUserByUserNameRequest;
import com.globitel.task.management.system.core.user.factory.response.DeleteUserByUserNameResponse;
import com.globitel.task.management.system.core.user.identity.UserIdentity;
import com.globitel.task.management.system.core.user.usecase.error.UserError;
import jakarta.validation.Validator;

public class DeleteUserByUserNameUseCase extends ValidatorFunctionalUseCase<DeleteUserByUserNameResponse, DeleteUserByUserNameRequest> {

    private final UserDao userDao;
    private final TaskUseCaseFactoryDomain taskUseCaseFactoryDomain;
    private final NotificationUseCaseFactoryDomain notificationUseCaseFactoryDomain;
    private final TokenUseCaseFactoryDomain tokenUseCaseFactoryDomain;
    private final CommentUseCaseFactoryDomain commentUseCaseFactoryDomain;
    private final ActivityLogUseCaseFactoryDomain activityLogUseCaseFactoryDomain;
    private final TransactionExecutor transactionExecutor;

    public DeleteUserByUserNameUseCase(Validator validator, UserDao userDao, TaskUseCaseFactoryDomain taskUseCaseFactoryDomain, NotificationUseCaseFactoryDomain notificationUseCaseFactoryDomain, TokenUseCaseFactoryDomain tokenUseCaseFactoryDomain, CommentUseCaseFactoryDomain commentUseCaseFactoryDomain, ActivityLogUseCaseFactoryDomain activityLogUseCaseFactoryDomain, TransactionExecutor transactionExecutor) {
        super(validator);
        this.userDao = userDao;
        this.taskUseCaseFactoryDomain = taskUseCaseFactoryDomain;
        this.notificationUseCaseFactoryDomain = notificationUseCaseFactoryDomain;
        this.tokenUseCaseFactoryDomain = tokenUseCaseFactoryDomain;
        this.commentUseCaseFactoryDomain = commentUseCaseFactoryDomain;
        this.activityLogUseCaseFactoryDomain = activityLogUseCaseFactoryDomain;
        this.transactionExecutor = transactionExecutor;
    }

    @Override
    protected DeleteUserByUserNameResponse doExecute(DeleteUserByUserNameRequest request) {
        return transactionExecutor.executeWithResult(() -> {
            UserIdentity userIdentity = userDao.findByUsername(request.userName())
                    .orElseThrow(() -> new BadRequestException(UserError.USER_NOT_FOUND));
            taskUseCaseFactoryDomain.makeUserTaskNullUseCase().execute(userIdentity);
            int numberOfDeletedNotifications = notificationUseCaseFactoryDomain.deleteNotificationByUser().execute(userIdentity);
            int numberOfDeletedComments = commentUseCaseFactoryDomain.deleteCommentsByUserIdentity().execute(userIdentity);
            int numberOfDeletedTokens = tokenUseCaseFactoryDomain.deleteTokenByUserUseCase().execute(userIdentity);
            int numberOfDeletedActivityLogs = activityLogUseCaseFactoryDomain.deleteActivityLogByUserUseCase().execute(userIdentity);
            return new DeleteUserByUserNameResponse(userDao.deleteByUserName(request.userName()), numberOfDeletedComments, numberOfDeletedTokens, numberOfDeletedActivityLogs, numberOfDeletedNotifications);
        });
    }
}
