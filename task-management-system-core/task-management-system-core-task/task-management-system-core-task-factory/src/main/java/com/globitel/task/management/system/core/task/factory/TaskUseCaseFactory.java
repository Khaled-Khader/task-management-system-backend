package com.globitel.task.management.system.core.task.factory;

import com.globitel.task.management.system.core.shared.usecase.ValidatorFunctionalUseCase;
import com.globitel.task.management.system.core.shared.usecase.ValidatorSupplierUseCase;
import com.globitel.task.management.system.core.task.factory.request.*;
import com.globitel.task.management.system.core.task.factory.response.DeleteTaskResponse;
import com.globitel.task.management.system.core.task.factory.response.TaskDataResponse;
import com.globitel.task.management.system.core.task.factory.response.TaskResponse;

import java.util.List;

public interface TaskUseCaseFactory {

    ValidatorFunctionalUseCase<TaskResponse, CreateTaskRequest> createTaskUseCase();

    ValidatorSupplierUseCase<List<TaskResponse>> findAllTasksUseCase();

    ValidatorFunctionalUseCase<DeleteTaskResponse, DeleteTaskRequest> deleteTaskUseCase();

    ValidatorFunctionalUseCase<TaskResponse, AssignTaskToUserRequest> assignTaskToUserUseCase();

    ValidatorSupplierUseCase<List<TaskResponse>> findTasksForUserUseCase();

    ValidatorFunctionalUseCase<TaskResponse, UpdateTaskForUserRequest> updateTaskStatusForUserUseCase();

    ValidatorFunctionalUseCase<TaskResponse, EditTaskDetailsRequest> editTaskDetailsUseCase();

    ValidatorFunctionalUseCase<TaskDataResponse, ViewTaskDataRequest> viewTaskDataUseCase();

    ValidatorFunctionalUseCase<TaskResponse, String>  findTaskByUuidUseCase();

    ValidatorFunctionalUseCase<TaskDataResponse, ViewTaskDataRequest> viewTaskDataByAdminUseCase();
}
