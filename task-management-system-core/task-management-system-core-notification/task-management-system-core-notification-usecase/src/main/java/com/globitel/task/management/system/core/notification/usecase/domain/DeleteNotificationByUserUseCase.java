package com.globitel.task.management.system.core.notification.usecase.domain;

import com.globitel.task.management.system.core.notification.dao.NotificationDao;
import com.globitel.task.management.system.core.shared.usecase.ValidatorConsumerUseCase;
import com.globitel.task.management.system.core.shared.usecase.ValidatorFunctionalUseCase;
import com.globitel.task.management.system.core.user.identity.UserIdentity;
import jakarta.validation.Validator;

public class DeleteNotificationByUserUseCase extends ValidatorFunctionalUseCase<Integer,UserIdentity> {

    private final NotificationDao notificationDao;

    public DeleteNotificationByUserUseCase(Validator validator, NotificationDao notificationDao) {
        super(validator);
        this.notificationDao=notificationDao;
    }

    @Override
    protected Integer doExecute(UserIdentity request) {
        return notificationDao.deleteByUser(request);
    }
}
