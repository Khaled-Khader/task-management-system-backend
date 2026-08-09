package com.globitel.task.management.system.core.comment.usecase;

import com.globitel.task.management.system.core.comment.dao.CommentDao;
import com.globitel.task.management.system.core.comment.factory.CommentUseCaseFactory;
import com.globitel.task.management.system.core.comment.factory.domain.CommentUseCaseFactoryDomain;
import com.globitel.task.management.system.core.comment.identity.CommentIdentity;
import com.globitel.task.management.system.core.comment.usecase.domain.DeleteCommentsByTaskUseCase;
import com.globitel.task.management.system.core.comment.usecase.domain.ReturnTaskCommentsUseCase;
import com.globitel.task.management.system.core.shared.usecase.ValidatorConsumerUseCase;
import com.globitel.task.management.system.core.shared.usecase.ValidatorFunctionalUseCase;
import com.globitel.task.management.system.core.task.identity.TaskIdentity;
import jakarta.validation.Validator;

import java.util.List;

public class CommentUseCaseFactoryDomainImpl implements CommentUseCaseFactoryDomain {

    private final DeleteCommentsByTaskUseCase deleteCommentsByTaskUseCase;
    private final ReturnTaskCommentsUseCase returnTaskCommentsUseCase;

    public CommentUseCaseFactoryDomainImpl(Validator validator, CommentDao  commentDao) {
        this.returnTaskCommentsUseCase=new ReturnTaskCommentsUseCase(validator,commentDao);
        this.deleteCommentsByTaskUseCase=new DeleteCommentsByTaskUseCase(validator,commentDao);
    }

    @Override
    public ValidatorConsumerUseCase<TaskIdentity> deleteCommentsByTaskIdentity(TaskIdentity taskIdentity) {
        return deleteCommentsByTaskUseCase;
    }

    @Override
    public ValidatorFunctionalUseCase<List<CommentIdentity>, TaskIdentity> returnTaskCommentsUseCase() {
        return returnTaskCommentsUseCase;
    }
}
