package com.globitel.task.management.system.core.task.usecase.main.admin;

import com.globitel.task.management.system.core.comment.factory.domain.CommentUseCaseFactoryDomain;
import com.globitel.task.management.system.core.comment.factory.response.CommentResponse;
import com.globitel.task.management.system.core.comment.identity.CommentIdentity;
import com.globitel.task.management.system.core.shared.exception.BadRequestException;
import com.globitel.task.management.system.core.shared.security.context.TMSSecurityContext;
import com.globitel.task.management.system.core.shared.usecase.ValidatorFunctionalUseCase;
import com.globitel.task.management.system.core.task.dao.TaskDao;
import com.globitel.task.management.system.core.task.factory.request.ViewTaskDataRequest;
import com.globitel.task.management.system.core.task.factory.response.TaskDataResponse;
import com.globitel.task.management.system.core.task.factory.response.TaskResponse;
import com.globitel.task.management.system.core.task.identity.TaskIdentity;
import com.globitel.task.management.system.core.task.usecase.error.TaskError;
import jakarta.validation.Validator;

import java.util.List;

public class ViewTaskDataByAdminUseCase extends ValidatorFunctionalUseCase<TaskDataResponse, ViewTaskDataRequest> {
    private final TaskDao taskDao;
    private final CommentUseCaseFactoryDomain commentUseCaseFactoryDomain;


    public ViewTaskDataByAdminUseCase(Validator validator, TaskDao taskDao, CommentUseCaseFactoryDomain commentUseCaseFactoryDomain) {
        super(validator);
        this.taskDao=taskDao;
        this.commentUseCaseFactoryDomain=commentUseCaseFactoryDomain;

    }

    @Override
    protected TaskDataResponse doExecute(ViewTaskDataRequest request) {

        TaskIdentity taskIdentity=taskDao.find(request.taskUuid())
                .orElseThrow(()->new BadRequestException(TaskError.TASK_NOT_FOUND));

        List<CommentIdentity> commentIdentities=commentUseCaseFactoryDomain.returnTaskCommentsUseCase().execute(taskIdentity);

        List<CommentResponse> comments = commentIdentities.stream()
                .map(comment -> new CommentResponse(
                        comment.getUuid(),
                        comment.getUser().getName(),
                        comment.getContent(),
                        comment.getUser().getUserRole(),
                        comment.getTask().getUuid(),
                        comment.getReplayedComment() != null
                                ? comment.getReplayedComment().getUuid()
                                : null
                ))
                .toList();
        return new TaskDataResponse(
                new TaskResponse(
                        taskIdentity.getUuid(),
                        taskIdentity.getTitle(),
                        taskIdentity.getDescription(),
                        taskIdentity.getUser().getUuid(),
                        taskIdentity.getUser().getUserName(),
                        taskIdentity.getDueDate(),
                        taskIdentity.getTaskStatus()
                ),
                comments
        );
    }
}
