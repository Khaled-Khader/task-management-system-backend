package com.globitel.task.management.system.core.notification.jpa;

import com.globitel.task.management.system.core.notification.dao.NotificationCreator;
import com.globitel.task.management.system.core.notification.entity.NotificationEntity;
import com.globitel.task.management.system.core.notification.identity.NotificationIdentity;
import com.globitel.task.management.system.core.user.entity.UserEntity;
import com.globitel.task.management.system.core.user.identity.UserIdentity;

import java.time.Instant;
import java.util.UUID;

public class NotificationCreatorImpl implements NotificationCreator {

    private String uuid= UUID.randomUUID().toString();
    private String message;
    private String action;
    private String title;
    private UserEntity user;
    private boolean isRead=false;
    @Override
    public NotificationCreator uuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    @Override
    public NotificationCreator action(String action) {
        this.action=action;
        return this;
    }

    @Override
    public NotificationCreator message(String message) {
        this.message=message;
        return this;
    }

    @Override
    public NotificationCreator title(String title) {
        this.title=title;
        return this;
    }

    @Override
    public NotificationCreator user(UserIdentity user) {
        this.user=(UserEntity)user;
        return this;
    }

    @Override
    public NotificationCreator isRead(boolean isRead) {
        this.isRead=isRead;
        return this;
    }

    @Override
    public NotificationIdentity create() {
        return new NotificationEntity(
                uuid,
                Instant.now(),
                title,
                message,
                isRead,
                user,
                action
        );
    }
}
