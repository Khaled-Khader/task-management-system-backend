package com.globitel.task.management.system.core.task.usecase.main.user;

import com.globitel.task.management.system.core.comment.factory.domain.CommentUseCaseFactoryDomain;
import com.globitel.task.management.system.core.comment.factory.response.CommentResponse;
import com.globitel.task.management.system.core.comment.identity.CommentIdentity;
import com.globitel.task.management.system.core.shared.exception.BadRequestException;
import com.globitel.task.management.system.core.shared.exception.data.DefaultErrorMessage;
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

public class ViewTaskDataByUserUseCase extends ValidatorFunctionalUseCase<TaskDataResponse, ViewTaskDataRequest> {

    private final TaskDao taskDao;
    private final CommentUseCaseFactoryDomain commentUseCaseFactoryDomain;
    private final TMSSecurityContext tmsSecurityContext;


        public ViewTaskDataByUserUseCase(Validator validator,TaskDao taskDao,CommentUseCaseFactoryDomain commentUseCaseFactoryDomain,TMSSecurityContext tmsSecurityContext) {
            super(validator);
        this.taskDao=taskDao;
        this.commentUseCaseFactoryDomain=commentUseCaseFactoryDomain;
        this.tmsSecurityContext=tmsSecurityContext;

    }

    @Override
    protected TaskDataResponse doExecute(ViewTaskDataRequest request) {

        TaskIdentity taskIdentity=taskDao.find(request.taskUuid())
                .orElseThrow(()->new BadRequestException(TaskError.TASK_NOT_FOUND));

        String userName= tmsSecurityContext.getCurrentUserUserName();

        if(!taskIdentity.getUser().getUserName().equals(userName)){
            throw new BadRequestException(DefaultErrorMessage.of("This task for this user","TMS-TSK-UC-DF-4000"));
        }

        List<CommentIdentity>commentIdentities=commentUseCaseFactoryDomain.returnTaskCommentsUseCase().execute(taskIdentity);

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
