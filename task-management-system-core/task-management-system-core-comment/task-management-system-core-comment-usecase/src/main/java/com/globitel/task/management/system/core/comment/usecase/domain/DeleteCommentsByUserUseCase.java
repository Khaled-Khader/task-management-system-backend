package com.globitel.task.management.system.core.comment.usecase.domain;

import com.globitel.task.management.system.core.comment.dao.CommentDao;
import com.globitel.task.management.system.core.shared.usecase.ValidatorFunctionalUseCase;
import com.globitel.task.management.system.core.user.identity.UserIdentity;
import jakarta.validation.Validator;

public class DeleteCommentsByUserUseCase extends ValidatorFunctionalUseCase<Integer, UserIdentity> {

    private final CommentDao commentDao;

    public DeleteCommentsByUserUseCase(Validator validator,CommentDao commentDao) {
        super(validator);
        this.commentDao=commentDao;
    }

    @Override
    protected Integer doExecute(UserIdentity request) {
        commentDao.makeReplayedNullByUser(request);
        return commentDao.deleteByUser(request);
    }
}
