package com.globitel.task.management.system.core.activitylog.usecase.domain;

import com.globitel.task.management.system.core.activitylog.dao.ActivityLogDao;
import com.globitel.task.management.system.core.shared.usecase.ValidatorFunctionalUseCase;
import com.globitel.task.management.system.core.user.identity.UserIdentity;
import jakarta.validation.Validator;

public class DeleteActivityLogByUserUseCase extends ValidatorFunctionalUseCase<Integer, UserIdentity> {
   private final ActivityLogDao activityLogDao;

    public DeleteActivityLogByUserUseCase(Validator validator,ActivityLogDao activityLogDao) {
        super(validator);
        this.activityLogDao=activityLogDao;
    }

    @Override
    protected Integer doExecute(UserIdentity request) {
        return activityLogDao.deleteByUser(request);
    }
}
