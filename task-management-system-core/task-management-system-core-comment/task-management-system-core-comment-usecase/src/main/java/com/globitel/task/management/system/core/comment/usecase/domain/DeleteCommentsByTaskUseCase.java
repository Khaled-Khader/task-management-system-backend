package com.globitel.task.management.system.core.comment.usecase.domain;

import com.globitel.task.management.system.core.comment.dao.CommentDao;
import com.globitel.task.management.system.core.shared.usecase.ValidatorConsumerUseCase;
import com.globitel.task.management.system.core.shared.usecase.ValidatorFunctionalUseCase;
import com.globitel.task.management.system.core.task.identity.TaskIdentity;
import jakarta.validation.Validator;

public class DeleteCommentsByTaskUseCase extends ValidatorFunctionalUseCase<Integer,TaskIdentity> {

    private final CommentDao commentDao;

    public DeleteCommentsByTaskUseCase(Validator validator, CommentDao commentDao) {
        super(validator);
        this.commentDao = commentDao;
    }

    @Override
    protected Integer doExecute(TaskIdentity request) {
        commentDao.makeReplayedNullByTask(request);
        return commentDao.deleteByTask(request);
    }
}
