package com.globitel.task.management.system.core.task.factory.domain;

import com.globitel.task.management.system.core.shared.usecase.ValidatorConsumerUseCase;
import com.globitel.task.management.system.core.shared.usecase.ValidatorFunctionalUseCase;
import com.globitel.task.management.system.core.task.identity.TaskIdentity;
import com.globitel.task.management.system.core.user.identity.UserIdentity;

public interface TaskUseCaseFactoryDomain {
    ValidatorConsumerUseCase<UserIdentity> makeUserTaskNullUseCase();
    ValidatorFunctionalUseCase<TaskIdentity,String> findTaskByUuidUseCase();
}
