package com.globitel.task.management.system.core.comment.factory.domain;

import com.globitel.task.management.system.core.comment.identity.CommentIdentity;
import com.globitel.task.management.system.core.shared.usecase.ValidatorFunctionalUseCase;
import com.globitel.task.management.system.core.task.identity.TaskIdentity;
import com.globitel.task.management.system.core.user.identity.UserIdentity;

import java.util.List;

public interface CommentUseCaseFactoryDomain {
    ValidatorFunctionalUseCase<Integer,TaskIdentity> deleteCommentsByTaskIdentity();
    ValidatorFunctionalUseCase<Integer, UserIdentity>  deleteCommentsByUserIdentity();
    ValidatorFunctionalUseCase<List<CommentIdentity>, TaskIdentity> returnTaskCommentsUseCase();
}
