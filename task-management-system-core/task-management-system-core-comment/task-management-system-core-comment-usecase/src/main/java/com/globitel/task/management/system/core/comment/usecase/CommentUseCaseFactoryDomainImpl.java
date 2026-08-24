package com.globitel.task.management.system.core.comment.usecase;

import com.globitel.task.management.system.core.comment.dao.CommentDao;
import com.globitel.task.management.system.core.comment.factory.domain.CommentUseCaseFactoryDomain;
import com.globitel.task.management.system.core.comment.identity.CommentIdentity;
import com.globitel.task.management.system.core.comment.usecase.domain.DeleteCommentsByTaskUseCase;
import com.globitel.task.management.system.core.comment.usecase.domain.DeleteCommentsByUserUseCase;
import com.globitel.task.management.system.core.comment.usecase.domain.ReturnTaskCommentsUseCase;
import com.globitel.task.management.system.core.shared.usecase.ValidatorFunctionalUseCase;
import com.globitel.task.management.system.core.task.identity.TaskIdentity;
import com.globitel.task.management.system.core.user.identity.UserIdentity;
import jakarta.validation.Validator;

import java.util.List;

public class CommentUseCaseFactoryDomainImpl implements CommentUseCaseFactoryDomain {

    private final DeleteCommentsByUserUseCase  deleteCommentsByUserUseCase;
    private final DeleteCommentsByTaskUseCase deleteCommentsByTaskUseCase;
    private final ReturnTaskCommentsUseCase returnTaskCommentsUseCase;

    public CommentUseCaseFactoryDomainImpl(Validator validator, CommentDao  commentDao) {
        this.returnTaskCommentsUseCase=new ReturnTaskCommentsUseCase(validator,commentDao);
        this.deleteCommentsByUserUseCase=new DeleteCommentsByUserUseCase(validator,commentDao);
        this.deleteCommentsByTaskUseCase=new DeleteCommentsByTaskUseCase(validator,commentDao);
    }

    @Override
    public ValidatorFunctionalUseCase<Integer, TaskIdentity> deleteCommentsByTaskIdentity() {
        return deleteCommentsByTaskUseCase;
    }

    @Override
    public ValidatorFunctionalUseCase<Integer, UserIdentity> deleteCommentsByUserIdentity() {
        return deleteCommentsByUserUseCase;
    }

    @Override
    public ValidatorFunctionalUseCase<List<CommentIdentity>, TaskIdentity> returnTaskCommentsUseCase() {
        return returnTaskCommentsUseCase;
    }
}
