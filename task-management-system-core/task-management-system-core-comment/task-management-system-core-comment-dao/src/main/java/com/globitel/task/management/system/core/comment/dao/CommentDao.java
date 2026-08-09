package com.globitel.task.management.system.core.comment.dao;

import com.globitel.task.management.system.core.comment.identity.CommentIdentity;
import com.globitel.task.management.system.core.shared.dao.Dao;
import com.globitel.task.management.system.core.task.identity.TaskIdentity;

import java.util.List;

public interface CommentDao extends Dao<CommentIdentity,CommentCreator,CommentUpdater> {
    void deleteByTask(TaskIdentity task);
    List<CommentIdentity> findCommentsByTaskIdentity(TaskIdentity taskIdentity);
}
