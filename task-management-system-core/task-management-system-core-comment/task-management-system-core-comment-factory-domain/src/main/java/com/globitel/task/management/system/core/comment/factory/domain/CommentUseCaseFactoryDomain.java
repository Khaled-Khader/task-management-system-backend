package com.globitel.task.management.system.core.comment.factory.domain;

import com.globitel.task.management.system.core.comment.identity.CommentIdentity;
import com.globitel.task.management.system.core.shared.usecase.ValidatorConsumerUseCase;
import com.globitel.task.management.system.core.shared.usecase.ValidatorFunctionalUseCase;
import com.globitel.task.management.system.core.task.identity.TaskIdentity;

import java.util.List;

public interface CommentUseCaseFactoryDomain {
    ValidatorConsumerUseCase<TaskIdentity> deleteCommentsByTaskIdentity(TaskIdentity taskIdentity);
    ValidatorFunctionalUseCase<List<CommentIdentity>, TaskIdentity> returnTaskCommentsUseCase();
}
