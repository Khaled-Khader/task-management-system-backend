package com.globitel.task.management.system.core.notification.usecase;


import com.globitel.task.management.system.core.notification.dao.NotificationDao;
import com.globitel.task.management.system.core.notification.factory.NotificationUseCaseFactory;
import com.globitel.task.management.system.core.notification.factory.respose.NotificationResponse;
import com.globitel.task.management.system.core.notification.usecase.main.FindAllUserNotificationUseCase;
import com.globitel.task.management.system.core.notification.usecase.main.MarkAllAsReadUseCase;
import com.globitel.task.management.system.core.notification.usecase.main.MarkNotificationAsReadUseCase;
import com.globitel.task.management.system.core.shared.security.context.TMSSecurityContext;
import com.globitel.task.management.system.core.shared.usecase.ValidatorConsumerUseCase;
import com.globitel.task.management.system.core.shared.usecase.ValidatorSupplierUseCase;
import com.globitel.task.management.system.core.user.factory.domain.UserUseCaseFactoryDomain;
import jakarta.validation.Validator;

import java.util.List;

public class NotificationUseCaseFactoryImpl implements NotificationUseCaseFactory {

    private final MarkAllAsReadUseCase markAllAsReadUseCase;
    private final FindAllUserNotificationUseCase findAllUserNotificationUseCase;
    private final MarkNotificationAsReadUseCase  markNotificationAsReadUseCase;

    public NotificationUseCaseFactoryImpl(NotificationDao  notificationDao, Validator validator, TMSSecurityContext  tmssecurityContext, UserUseCaseFactoryDomain  userUseCaseFactoryDomain) {
        this.markNotificationAsReadUseCase=new MarkNotificationAsReadUseCase(validator,notificationDao);
        this.markAllAsReadUseCase=new MarkAllAsReadUseCase(validator,notificationDao,userUseCaseFactoryDomain);
        this.findAllUserNotificationUseCase=new FindAllUserNotificationUseCase(notificationDao,tmssecurityContext,userUseCaseFactoryDomain);
    }

    @Override
    public ValidatorConsumerUseCase<String> markNotificationAsReadUseCase() {
        return markNotificationAsReadUseCase;
    }

    @Override
    public ValidatorConsumerUseCase<String> markAllAsReadUseCase() {
        return markAllAsReadUseCase;
    }

    @Override
    public ValidatorSupplierUseCase<List<NotificationResponse>> findAllUserNotificationUseCase() {
        return findAllUserNotificationUseCase;
    }
}
