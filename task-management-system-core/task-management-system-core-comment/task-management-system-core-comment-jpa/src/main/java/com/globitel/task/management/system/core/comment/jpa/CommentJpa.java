package com.globitel.task.management.system.core.comment.jpa;

import com.globitel.task.management.system.core.comment.dao.CommentCreator;
import com.globitel.task.management.system.core.comment.dao.CommentDao;
import com.globitel.task.management.system.core.comment.dao.CommentUpdater;
import com.globitel.task.management.system.core.comment.entity.CommentEntity;
import com.globitel.task.management.system.core.comment.identity.CommentIdentity;
import com.globitel.task.management.system.core.task.entity.TaskEntity;
import com.globitel.task.management.system.core.task.identity.TaskIdentity;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CommentJpa implements CommentDao {

    private final CommentRepository commentRepository;

    @Override
    public CommentIdentity save(CommentIdentity entity) {
        return commentRepository.save((CommentEntity) entity);
    }

    @Override
    public Optional<CommentIdentity> find(String uuid) {
        return commentRepository.findByUuid(uuid) ;
    }

    @Override
    public List<CommentIdentity> findAll() {
        return List.copyOf(commentRepository.findAll());
    }

    @Override
    public CommentCreator creator() {
        return new CommentCreatorImpl();
    }

    @Override
    public CommentUpdater updater(CommentIdentity entity) {
        return null;
    }

    @Override
    public void deleteByTask(TaskIdentity task) {
        commentRepository.deleteRepliesByTask((TaskEntity) task);
        commentRepository.deleteParentCommentsByTask((TaskEntity)task);
    }

    @Override
    public List<CommentIdentity> findCommentsByTaskIdentity(TaskIdentity taskIdentity) {
        return List.copyOf(commentRepository.findByTask((TaskEntity) taskIdentity));
    }
}
