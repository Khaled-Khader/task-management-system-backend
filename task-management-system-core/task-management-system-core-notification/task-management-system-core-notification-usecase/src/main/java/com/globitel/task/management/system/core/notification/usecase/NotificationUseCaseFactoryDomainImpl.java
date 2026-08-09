package com.globitel.task.management.system.core.notification.usecase;

import com.globitel.task.management.system.core.notification.dao.NotificationDao;
import com.globitel.task.management.system.core.notification.factory.domain.NotificationUseCaseFactoryDomain;
import com.globitel.task.management.system.core.notification.factory.domain.request.CreateNotificationRequest;
import com.globitel.task.management.system.core.notification.identity.NotificationIdentity;
import com.globitel.task.management.system.core.notification.usecase.domain.CreateNotificationUseCase;
import com.globitel.task.management.system.core.shared.usecase.ValidatorFunctionalUseCase;
import jakarta.validation.Validator;

public class NotificationUseCaseFactoryDomainImpl implements NotificationUseCaseFactoryDomain {

    private final CreateNotificationUseCase createNotificationUseCase;

    public NotificationUseCaseFactoryDomainImpl(Validator validator, NotificationDao notificationDao) {
        this.createNotificationUseCase = new CreateNotificationUseCase(validator, notificationDao);
    }

    @Override
    public ValidatorFunctionalUseCase<NotificationIdentity, CreateNotificationRequest> createNotificationUseCase() {
        return createNotificationUseCase;
    }
}
