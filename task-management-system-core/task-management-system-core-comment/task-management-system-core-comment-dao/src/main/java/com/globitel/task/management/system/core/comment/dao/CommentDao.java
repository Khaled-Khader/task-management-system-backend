package com.globitel.task.management.system.core.comment.dao;

import com.globitel.task.management.system.core.comment.identity.CommentIdentity;
import com.globitel.task.management.system.core.shared.dao.Dao;
import com.globitel.task.management.system.core.task.identity.TaskIdentity;
import com.globitel.task.management.system.core.user.identity.UserIdentity;

import java.util.List;

public interface CommentDao extends Dao<CommentIdentity,CommentCreator,CommentUpdater> {
    void makeReplayedNullByTask(TaskIdentity task);
    int deleteByTask(TaskIdentity task);
    List<CommentIdentity> findCommentsByTaskIdentity(TaskIdentity taskIdentity);

    void makeReplayedNullByUser(UserIdentity userIdentity);
    int deleteByUser(UserIdentity userIdentity);
}
