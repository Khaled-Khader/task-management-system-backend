package com.globitel.task.management.system.core.comment.usecase;

import com.globitel.task.management.system.core.comment.dao.CommentDao;
import com.globitel.task.management.system.core.comment.factory.CommentUseCaseFactory;
import com.globitel.task.management.system.core.comment.factory.request.CreateInitCommentRequest;
import com.globitel.task.management.system.core.comment.factory.request.CreateReplyCommentRequest;
import com.globitel.task.management.system.core.comment.factory.response.CommentResponse;
import com.globitel.task.management.system.core.comment.usecase.main.admin.CreateInitCommentByAdminUseCase;
import com.globitel.task.management.system.core.comment.usecase.main.admin.CreateReplyCommentByAdminUseCase;
import com.globitel.task.management.system.core.comment.usecase.main.user.CreateInitCommentByUserUseCase;
import com.globitel.task.management.system.core.comment.usecase.main.user.CreateReplyCommentByUserUseCase;
import com.globitel.task.management.system.core.notification.factory.domain.NotificationUseCaseFactoryDomain;
import com.globitel.task.management.system.core.shared.security.context.TMSSecurityContext;
import com.globitel.task.management.system.core.shared.socket.TMSWebSocket;
import com.globitel.task.management.system.core.shared.usecase.ValidatorFunctionalUseCase;
import com.globitel.task.management.system.core.task.factory.domain.TaskUseCaseFactoryDomain;
import com.globitel.task.management.system.core.user.factory.domain.UserUseCaseFactoryDomain;
import jakarta.validation.Validator;

public class CommentUseCaseFactoryImpl implements CommentUseCaseFactory {

    private final CreateInitCommentByAdminUseCase createInitCommentByAdminUseCase;
    private final CreateReplyCommentByAdminUseCase  createReplyCommentByAdminUseCase;
    private final CreateInitCommentByUserUseCase  createInitCommentByUserUseCase;
    private final CreateReplyCommentByUserUseCase createReplyCommentByUserUseCase;
    public CommentUseCaseFactoryImpl(CommentDao  commentDao, Validator validator, TMSSecurityContext tmsSecurityContext, UserUseCaseFactoryDomain userUseCaseFactoryDomain, TaskUseCaseFactoryDomain  taskUseCaseFactoryDomain, NotificationUseCaseFactoryDomain notificationUseCaseFactoryDomain, TMSWebSocket tmsWebSocket) {
        this.createInitCommentByAdminUseCase =new CreateInitCommentByAdminUseCase(validator,tmsSecurityContext,commentDao,taskUseCaseFactoryDomain,userUseCaseFactoryDomain,notificationUseCaseFactoryDomain,tmsWebSocket);
        this.createReplyCommentByAdminUseCase=new CreateReplyCommentByAdminUseCase(validator,tmsSecurityContext,commentDao,taskUseCaseFactoryDomain,userUseCaseFactoryDomain,notificationUseCaseFactoryDomain,tmsWebSocket);
        this.createInitCommentByUserUseCase=new CreateInitCommentByUserUseCase(validator,tmsSecurityContext,commentDao,taskUseCaseFactoryDomain,userUseCaseFactoryDomain);
        this.createReplyCommentByUserUseCase=new CreateReplyCommentByUserUseCase(validator,tmsSecurityContext,commentDao,taskUseCaseFactoryDomain,userUseCaseFactoryDomain);
    }

    @Override
    public ValidatorFunctionalUseCase<CommentResponse, CreateInitCommentRequest> createInitCommentByAdminUseCase() {
        return createInitCommentByAdminUseCase;
    }

    @Override
    public ValidatorFunctionalUseCase<CommentResponse, CreateReplyCommentRequest> createReplyCommentByAdminUseCase() {
        return createReplyCommentByAdminUseCase;
    }

    @Override
    public ValidatorFunctionalUseCase<CommentResponse, CreateInitCommentRequest> createInitCommentByUserUseCase() {
        return createInitCommentByUserUseCase;
    }

    @Override
    public ValidatorFunctionalUseCase<CommentResponse, CreateReplyCommentRequest> createReplyCommentByUserUseCase() {
        return createReplyCommentByUserUseCase;
    }
}
