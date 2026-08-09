package com.globitel.task.management.system.core.notification.usecase.domain;

import com.globitel.task.management.system.core.notification.dao.NotificationDao;
import com.globitel.task.management.system.core.notification.factory.domain.request.CreateNotificationRequest;
import com.globitel.task.management.system.core.notification.identity.NotificationIdentity;
import com.globitel.task.management.system.core.shared.usecase.ValidatorFunctionalUseCase;
import jakarta.validation.Validator;

public class CreateNotificationUseCase extends ValidatorFunctionalUseCase<NotificationIdentity, CreateNotificationRequest> {

    private final NotificationDao notificationDao;

    public CreateNotificationUseCase(Validator validator, NotificationDao notificationDao) {
        super(validator);
        this.notificationDao = notificationDao;
    }

    @Override
    protected NotificationIdentity doExecute(CreateNotificationRequest request) {
        NotificationIdentity notificationIdentity=notificationDao.creator()
                .title(request.title())
                .action(request.action())
                .user(request.user())
                .message(request.message())
                .create();

        return notificationDao.save(notificationIdentity);
    }
}
