package com.globitel.task.management.system.core.notification.usecase.main;

import com.globitel.task.management.system.core.notification.dao.NotificationDao;
import com.globitel.task.management.system.core.notification.identity.NotificationIdentity;
import com.globitel.task.management.system.core.notification.usecase.error.NotificationError;
import com.globitel.task.management.system.core.shared.exception.BadRequestException;
import com.globitel.task.management.system.core.shared.usecase.ValidatorConsumerUseCase;
import jakarta.validation.Validator;

public class MarkNotificationAsReadUseCase extends ValidatorConsumerUseCase<String> {

    private final NotificationDao notificationDao;

    public MarkNotificationAsReadUseCase(Validator validator, NotificationDao notificationDao) {
        super(validator);
        this.notificationDao = notificationDao;
    }

    @Override
    protected void doExecute(String request) {
        System.out.println(request);
        NotificationIdentity notificationIdentity = notificationDao.find(request)
                .orElseThrow(() -> new BadRequestException(NotificationError.NOTIFICATION_NOT_FOUND));

        NotificationIdentity updated = notificationDao.updater(notificationIdentity)
                .isRead(true)
                .update();

        notificationDao.save(updated);
    }
}
