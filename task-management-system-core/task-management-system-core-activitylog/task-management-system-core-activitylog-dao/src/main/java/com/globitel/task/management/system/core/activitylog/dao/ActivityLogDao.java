package com.globitel.task.management.system.core.activitylog.dao;

import com.globitel.task.management.system.core.activitylog.identity.ActivityLogIdentity;
import com.globitel.task.management.system.core.shared.dao.Dao;

import java.util.List;

public interface ActivityLogDao extends Dao<ActivityLogIdentity,ActivityLogCreator,ActivityLogUpdater> {

    List<ActivityLogIdentity> findAllActivityLogByOrderCreatedAtDesc();
}
