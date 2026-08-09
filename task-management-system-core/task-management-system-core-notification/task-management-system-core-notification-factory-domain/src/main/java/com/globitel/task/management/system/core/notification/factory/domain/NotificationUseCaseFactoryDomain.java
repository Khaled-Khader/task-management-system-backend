package com.globitel.task.management.system.core.notification.factory.domain;

import com.globitel.task.management.system.core.notification.factory.domain.request.CreateNotificationRequest;
import com.globitel.task.management.system.core.notification.identity.NotificationIdentity;
import com.globitel.task.management.system.core.shared.usecase.ValidatorFunctionalUseCase;

public interface NotificationUseCaseFactoryDomain {

    ValidatorFunctionalUseCase<NotificationIdentity, CreateNotificationRequest> createNotificationUseCase();
}
