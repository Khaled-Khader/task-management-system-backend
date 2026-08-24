package com.globitel.task.management.system.core.activitylog.factory.domain;

import com.globitel.task.management.system.core.shared.usecase.ValidatorFunctionalUseCase;
import com.globitel.task.management.system.core.user.identity.UserIdentity;

public interface ActivityLogUseCaseFactoryDomain {
    ValidatorFunctionalUseCase<Integer, UserIdentity> deleteActivityLogByUserUseCase();
}
