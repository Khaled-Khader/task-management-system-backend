package com.globitel.task.management.system.core.comment.jpa;

import com.globitel.task.management.system.core.comment.dao.CommentCreator;
import com.globitel.task.management.system.core.comment.dao.CommentDao;
import com.globitel.task.management.system.core.comment.dao.CommentUpdater;
import com.globitel.task.management.system.core.comment.entity.CommentEntity;
import com.globitel.task.management.system.core.comment.identity.CommentIdentity;
import com.globitel.task.management.system.core.task.entity.TaskEntity;
import com.globitel.task.management.system.core.task.identity.TaskIdentity;
import com.globitel.task.management.system.core.user.entity.UserEntity;
import com.globitel.task.management.system.core.user.identity.UserIdentity;
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
    public void makeReplayedNullByTask(TaskIdentity task) {
        commentRepository.makeReplayedNullByTask((TaskEntity) task);
    }

    @Override
    public int deleteByTask(TaskIdentity task) {
        return commentRepository.deleteByTask((TaskEntity) task);
    }

    @Override
    public List<CommentIdentity> findCommentsByTaskIdentity(TaskIdentity taskIdentity) {
        return List.copyOf(commentRepository.findByTask((TaskEntity) taskIdentity));
    }

    @Override
    public void makeReplayedNullByUser(UserIdentity userEntity) {
            commentRepository.makeReplayedNullByUser((UserEntity)userEntity);
    }

    @Override
    public int deleteByUser(UserIdentity userEntity) {
        return commentRepository.deleteByUser((UserEntity) userEntity);
    }
}
