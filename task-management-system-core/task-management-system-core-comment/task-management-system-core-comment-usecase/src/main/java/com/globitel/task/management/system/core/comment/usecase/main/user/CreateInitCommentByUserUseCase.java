package com.globitel.task.management.system.core.comment.usecase.main.user;

import com.globitel.task.management.system.core.comment.dao.CommentDao;
import com.globitel.task.management.system.core.comment.factory.request.CreateInitCommentRequest;
import com.globitel.task.management.system.core.comment.factory.response.CommentResponse;
import com.globitel.task.management.system.core.comment.identity.CommentIdentity;
import com.globitel.task.management.system.core.comment.usecase.error.CommentError;
import com.globitel.task.management.system.core.shared.exception.BadRequestException;
import com.globitel.task.management.system.core.shared.security.context.TMSSecurityContext;
import com.globitel.task.management.system.core.shared.usecase.ValidatorFunctionalUseCase;
import com.globitel.task.management.system.core.task.factory.domain.TaskUseCaseFactoryDomain;
import com.globitel.task.management.system.core.task.identity.TaskIdentity;
import com.globitel.task.management.system.core.user.factory.domain.UserUseCaseFactoryDomain;
import com.globitel.task.management.system.core.user.identity.UserIdentity;
import jakarta.validation.Validator;

public class CreateInitCommentByUserUseCase extends ValidatorFunctionalUseCase<CommentResponse, CreateInitCommentRequest> {


    private final TMSSecurityContext tmsSecurityContext;
    private final CommentDao commentDao;
    private final TaskUseCaseFactoryDomain taskUseCaseFactoryDomain;
    private final UserUseCaseFactoryDomain userUseCaseFactoryDomain;

    public CreateInitCommentByUserUseCase(Validator validator, TMSSecurityContext tmsSecurityContext, CommentDao commentDao, TaskUseCaseFactoryDomain taskUseCaseFactoryDomain, UserUseCaseFactoryDomain userUseCaseFactoryDomain) {
        super(validator);
        this.tmsSecurityContext = tmsSecurityContext;
        this.commentDao = commentDao;
        this.taskUseCaseFactoryDomain = taskUseCaseFactoryDomain;
        this.userUseCaseFactoryDomain = userUseCaseFactoryDomain;
    }

    @Override
    protected CommentResponse doExecute(CreateInitCommentRequest request) {
        String userName= tmsSecurityContext.getCurrentUserUserName();
        UserIdentity userIdentity=userUseCaseFactoryDomain.findUserByUsernameUseCase().execute(userName);

        TaskIdentity taskIdentity=taskUseCaseFactoryDomain.findTaskByUuidUseCase().execute(request.taskUuid());
        if(!taskIdentity.getUser().getUuid().equals(userIdentity.getUuid())){
            throw new BadRequestException(CommentError.TASK_NOT_OWNED_BY_USER);
        }
        CommentIdentity newComment=commentDao.creator()
                .task(taskIdentity)
                .user(userIdentity)
                .content(request.content())
                .create();

        CommentIdentity ignore=commentDao.save(newComment);

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
