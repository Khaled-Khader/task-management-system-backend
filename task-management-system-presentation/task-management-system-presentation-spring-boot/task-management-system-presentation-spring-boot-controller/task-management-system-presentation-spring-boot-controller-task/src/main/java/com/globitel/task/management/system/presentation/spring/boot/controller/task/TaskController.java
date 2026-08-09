package com.globitel.task.management.system.presentation.spring.boot.controller.task;

import com.globitel.task.management.system.core.task.factory.TaskUseCaseFactory;
import com.globitel.task.management.system.core.task.factory.request.*;
import com.globitel.task.management.system.core.task.factory.response.DeleteTaskResponse;
import com.globitel.task.management.system.core.task.factory.response.TaskDataResponse;
import com.globitel.task.management.system.core.task.factory.response.TaskResponse;
import com.globitel.task.management.system.presentation.spring.boot.aop.ActivityLog;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskUseCaseFactory taskUseCaseFactory;

    @ActivityLog("CREATE TASK")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public TaskResponse createTask(@RequestBody CreateTaskRequest createTaskRequest) {
        return taskUseCaseFactory.createTaskUseCase().execute(createTaskRequest);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<TaskResponse> findAllTasks() {
        return taskUseCaseFactory.findAllTasksUseCase().execute();
    }

    @ActivityLog("DELETE TASK")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    public DeleteTaskResponse deleteTask(@RequestBody DeleteTaskRequest deleteTaskRequest) {
        return taskUseCaseFactory.deleteTaskUseCase().execute(deleteTaskRequest);
    }

    @ActivityLog("ASSIGN TASK TO USER")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("assign")
    public TaskResponse assignTaskToUser(@RequestBody AssignTaskToUserRequest assignTaskToUserRequest) {
        return taskUseCaseFactory.assignTaskToUserUseCase().execute(assignTaskToUserRequest);
    }

    @ActivityLog("EDIT USER DETAILS")
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping
    public TaskResponse editTaskDetails(@RequestBody EditTaskDetailsRequest editTaskDetailsRequest) {
        return taskUseCaseFactory.editTaskDetailsUseCase().execute(editTaskDetailsRequest);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public List<TaskResponse> findTasksForUser() {
        return taskUseCaseFactory.findTasksForUserUseCase().execute();
    }

    @ActivityLog("UPDATE TASK STATUS")
    @PreAuthorize("hasRole('USER')")
    @PutMapping("update-status")
    public TaskResponse updateTaskStatusForUser(@RequestBody UpdateTaskForUserRequest  updateTaskForUserRequest) {
        return taskUseCaseFactory.updateTaskStatusForUserUseCase().execute(updateTaskForUserRequest);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{uuid}")
    public TaskResponse findTaskByUuid(
            @PathVariable String uuid
    ) {
        return taskUseCaseFactory
                .findTaskByUuidUseCase()
                .execute(uuid);
    }



    @PreAuthorize("hasRole('USER')")
    @GetMapping("user/task-data/")
    public TaskDataResponse viewTaskData(@RequestParam(name = "taskUuid") String taskUuid ) {
        return taskUseCaseFactory.viewTaskDataUseCase().execute(new ViewTaskDataRequest(taskUuid));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("admin/task-data/")
    public TaskDataResponse viewTaskDataBuAdmin(@RequestParam(name = "taskUuid") String taskUuid ) {
        return taskUseCaseFactory.viewTaskDataByAdminUseCase().execute(new ViewTaskDataRequest(taskUuid));
    }
}
