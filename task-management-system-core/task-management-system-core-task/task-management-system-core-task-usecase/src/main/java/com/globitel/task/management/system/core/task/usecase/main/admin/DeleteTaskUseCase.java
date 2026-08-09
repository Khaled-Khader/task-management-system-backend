package com.globitel.task.management.system.core.task.usecase.main.admin;

import com.globitel.task.management.system.core.comment.factory.domain.CommentUseCaseFactoryDomain;
import com.globitel.task.management.system.core.shared.exception.BadRequestException;
import com.globitel.task.management.system.core.shared.usecase.ValidatorFunctionalUseCase;
import com.globitel.task.management.system.core.task.dao.TaskDao;
import com.globitel.task.management.system.core.task.factory.request.DeleteTaskRequest;
import com.globitel.task.management.system.core.task.factory.response.DeleteTaskResponse;
import com.globitel.task.management.system.core.task.identity.TaskIdentity;
import com.globitel.task.management.system.core.task.usecase.error.TaskError;
import jakarta.validation.Validator;

public class DeleteTaskUseCase extends ValidatorFunctionalUseCase<DeleteTaskResponse, DeleteTaskRequest> {

    private final TaskDao taskDao;
    private final CommentUseCaseFactoryDomain commentUseCaseFactoryDomain;

    public DeleteTaskUseCase(Validator validator,TaskDao taskDao,CommentUseCaseFactoryDomain commentUseCaseFactoryDomain) {
        super(validator);
        this.taskDao = taskDao;
        this.commentUseCaseFactoryDomain = commentUseCaseFactoryDomain;
    }

    @Override
    protected DeleteTaskResponse doExecute(DeleteTaskRequest request) {

        TaskIdentity taskIdentity = taskDao.find(request.uuid())
                .orElseThrow(() ->
                        new BadRequestException(TaskError.TASK_NOT_FOUND));


        commentUseCaseFactoryDomain
                .deleteCommentsByTaskIdentity(taskIdentity);


        int count = taskDao.deleteTask(request.uuid());

        return new DeleteTaskResponse(count);
    }
}
