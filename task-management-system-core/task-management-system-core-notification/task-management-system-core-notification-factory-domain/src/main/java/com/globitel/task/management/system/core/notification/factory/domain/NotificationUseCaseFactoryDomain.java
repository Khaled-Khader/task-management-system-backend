package com.globitel.task.management.system.core.notification.factory.domain;

import com.globitel.task.management.system.core.notification.factory.domain.request.CreateNotificationRequest;
import com.globitel.task.management.system.core.notification.identity.NotificationIdentity;
import com.globitel.task.management.system.core.shared.usecase.ValidatorConsumerUseCase;
import com.globitel.task.management.system.core.shared.usecase.ValidatorFunctionalUseCase;
import com.globitel.task.management.system.core.user.identity.UserIdentity;

public interface NotificationUseCaseFactoryDomain {

    ValidatorFunctionalUseCase<Integer,UserIdentity> deleteNotificationByUser();
    ValidatorFunctionalUseCase<NotificationIdentity, CreateNotificationRequest> createNotificationUseCase();
}
