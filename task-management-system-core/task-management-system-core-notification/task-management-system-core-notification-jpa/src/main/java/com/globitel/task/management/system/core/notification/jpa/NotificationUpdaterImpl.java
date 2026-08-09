package com.globitel.task.management.system.core.notification.jpa;

import com.globitel.task.management.system.core.notification.dao.NotificationUpdater;
import com.globitel.task.management.system.core.notification.entity.NotificationEntity;
import com.globitel.task.management.system.core.notification.identity.NotificationIdentity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotificationUpdaterImpl implements NotificationUpdater {

    private final NotificationEntity notificationEntity;

    @Override
    public NotificationUpdater isRead(boolean isRead) {
        this.notificationEntity.setRead(isRead);
        return this;
    }

    @Override
    public NotificationIdentity update() {
        return this.notificationEntity;
    }


}
