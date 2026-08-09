package com.globitel.task.management.system.core.notification.usecase.main;

import com.globitel.task.management.system.core.notification.dao.NotificationDao;
import com.globitel.task.management.system.core.notification.identity.NotificationIdentity;
import com.globitel.task.management.system.core.notification.usecase.error.NotificationError;
import com.globitel.task.management.system.core.shared.exception.BadRequestException;
import com.globitel.task.management.system.core.shared.usecase.ValidatorConsumerUseCase;
import com.globitel.task.management.system.core.user.factory.domain.UserUseCaseFactoryDomain;
import com.globitel.task.management.system.core.user.identity.UserIdentity;
import jakarta.validation.Validator;

import java.util.List;

public class MarkAllAsReadUseCase extends ValidatorConsumerUseCase<String> {

    private final NotificationDao notificationDao;
    private final UserUseCaseFactoryDomain userUseCaseFactoryDomain;

    public MarkAllAsReadUseCase(Validator validator, NotificationDao notificationDao, UserUseCaseFactoryDomain userUseCaseFactoryDomain) {
        super(validator);
        this.notificationDao = notificationDao;
        this.userUseCaseFactoryDomain = userUseCaseFactoryDomain;
    }

    @Override
    protected void doExecute(String request) {

        UserIdentity userIdentity = userUseCaseFactoryDomain.findUserByIdUseCase().execute(request);
        List<NotificationIdentity> notificationIdentities = notificationDao.findAllByUser(userIdentity);

        notificationIdentities.forEach(notification -> {
            notificationDao.save(notificationDao.updater(notification)
                    .isRead(true)
                    .update());
        });
    }
}
