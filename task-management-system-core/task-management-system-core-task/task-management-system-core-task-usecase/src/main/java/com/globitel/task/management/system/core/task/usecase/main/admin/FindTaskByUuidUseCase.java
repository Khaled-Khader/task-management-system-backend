package com.globitel.task.management.system.core.task.usecase.main.admin;

import com.globitel.task.management.system.core.shared.exception.BadRequestException;
import com.globitel.task.management.system.core.shared.usecase.ValidatorFunctionalUseCase;
import com.globitel.task.management.system.core.task.dao.TaskDao;
import com.globitel.task.management.system.core.task.factory.response.TaskResponse;
import com.globitel.task.management.system.core.task.identity.TaskIdentity;
import com.globitel.task.management.system.core.task.usecase.error.TaskError;
import jakarta.validation.Validator;

public class FindTaskByUuidUseCase extends ValidatorFunctionalUseCase<TaskResponse, String> {

    private final TaskDao taskDao;

    public FindTaskByUuidUseCase(Validator validator,TaskDao taskDao) {
        super(validator);
        this.taskDao = taskDao;
    }

    @Override
    protected TaskResponse doExecute(String request) {
        TaskIdentity taskIdentity=taskDao.find(request)
                .orElseThrow(()->new BadRequestException(TaskError.TASK_NOT_FOUND));

        return new TaskResponse(
                taskIdentity.getUuid(),
                taskIdentity.getTitle(),
                taskIdentity.getDescription(),
                taskIdentity.getUser().getUuid(),
                taskIdentity.getUser().getUserName(),
                taskIdentity.getDueDate(),
                taskIdentity.getTaskStatus()
        );
    }
}
