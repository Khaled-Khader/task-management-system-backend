package com.globitel.task.management.system.core.task.dao;

import com.globitel.task.management.system.core.shared.dao.Dao;
import com.globitel.task.management.system.core.task.identity.TaskIdentity;
import com.globitel.task.management.system.core.user.identity.UserIdentity;

import java.util.List;

public interface TaskDao extends Dao<TaskIdentity,TaskCreator,TaskUpdater> {
    int deleteTask(String uuid);
    List<TaskIdentity> findTasksByUser(UserIdentity userIdentity);
    void makeUserNull(UserIdentity userIdentity);
}
