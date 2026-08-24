package com.globitel.task.management.system.core.notification.dao;

import com.globitel.task.management.system.core.notification.identity.NotificationIdentity;
import com.globitel.task.management.system.core.shared.dao.Dao;
import com.globitel.task.management.system.core.user.identity.UserIdentity;

import java.util.List;

public interface NotificationDao extends Dao<NotificationIdentity,NotificationCreator,NotificationUpdater> {

    int deleteByUser(UserIdentity userIdentity);
    List<NotificationIdentity> findAllByUser(UserIdentity userIdentity);
}
