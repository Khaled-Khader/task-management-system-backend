package com.globitel.task.management.system.core.activitylog.dao;

import com.globitel.task.management.system.core.activitylog.identity.ActivityLogIdentity;
import com.globitel.task.management.system.core.shared.dao.Dao;
import com.globitel.task.management.system.core.user.identity.UserIdentity;

import java.util.List;

public interface ActivityLogDao extends Dao<ActivityLogIdentity,ActivityLogCreator,ActivityLogUpdater> {

    int deleteByUser(UserIdentity userIdentity);
    List<ActivityLogIdentity> findAllActivityLogByOrderCreatedAtDesc();
}
