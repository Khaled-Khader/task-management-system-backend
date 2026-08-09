package com.globitel.task.management.system.core.notification.factory;

import com.globitel.task.management.system.core.notification.factory.respose.NotificationResponse;
import com.globitel.task.management.system.core.shared.usecase.ValidatorConsumerUseCase;
import com.globitel.task.management.system.core.shared.usecase.ValidatorSupplierUseCase;

import java.util.List;

public interface NotificationUseCaseFactory {
    ValidatorConsumerUseCase<String> markNotificationAsReadUseCase();
    ValidatorConsumerUseCase<String> markAllAsReadUseCase();
    ValidatorSupplierUseCase<List<NotificationResponse>> findAllUserNotificationUseCase();
}
