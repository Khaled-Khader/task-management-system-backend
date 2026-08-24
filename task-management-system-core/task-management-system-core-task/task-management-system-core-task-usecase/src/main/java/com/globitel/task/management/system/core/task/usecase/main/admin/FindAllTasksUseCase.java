package com.globitel.task.management.system.core.task.usecase.main.admin;

import com.globitel.task.management.system.core.shared.usecase.ValidatorSupplierUseCase;
import com.globitel.task.management.system.core.task.dao.TaskDao;
import com.globitel.task.management.system.core.task.factory.response.TaskResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FindAllTasksUseCase extends ValidatorSupplierUseCase<List<TaskResponse>> {

    private final TaskDao  taskDao;

    @Override
    protected List<TaskResponse> doExecute() {
        return taskDao.findAll().stream().map(taskIdentity -> new TaskResponse(
                taskIdentity.getUuid(),
                taskIdentity.getTitle(),
                taskIdentity.getDescription(),
                taskIdentity.getUser()!=null?taskIdentity.getUser().getUuid():null,
                taskIdentity.getUser()!=null?taskIdentity.getUser().getName():null,
                taskIdentity.getDueDate(),
                taskIdentity.getTaskStatus()
        )).toList();
    }
}
