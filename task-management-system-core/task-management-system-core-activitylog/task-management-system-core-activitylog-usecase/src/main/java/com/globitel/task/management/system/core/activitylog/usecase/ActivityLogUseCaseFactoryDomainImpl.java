package com.globitel.task.management.system.core.activitylog.usecase;

import com.globitel.task.management.system.core.activitylog.dao.ActivityLogDao;
import com.globitel.task.management.system.core.activitylog.factory.ActivityLogUseCaseFactory;
import com.globitel.task.management.system.core.activitylog.factory.domain.ActivityLogUseCaseFactoryDomain;
import com.globitel.task.management.system.core.activitylog.usecase.domain.DeleteActivityLogByUserUseCase;
import com.globitel.task.management.system.core.shared.usecase.ValidatorFunctionalUseCase;
import com.globitel.task.management.system.core.user.identity.UserIdentity;
import jakarta.validation.Validator;

public class ActivityLogUseCaseFactoryDomainImpl implements ActivityLogUseCaseFactoryDomain {

    private final DeleteActivityLogByUserUseCase deleteActivityLogByUserUseCase;

    public ActivityLogUseCaseFactoryDomainImpl(Validator validator, ActivityLogDao activityLogDao) {
        this.deleteActivityLogByUserUseCase=new DeleteActivityLogByUserUseCase(validator,activityLogDao);
    }
    @Override
    public ValidatorFunctionalUseCase<Integer, UserIdentity> deleteActivityLogByUserUseCase() {
        return deleteActivityLogByUserUseCase;
    }
}
