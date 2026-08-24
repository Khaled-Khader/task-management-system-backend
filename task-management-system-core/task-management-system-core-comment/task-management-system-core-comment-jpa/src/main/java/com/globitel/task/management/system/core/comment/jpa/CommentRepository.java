package com.globitel.task.management.system.core.comment.jpa;

import com.globitel.task.management.system.core.comment.entity.CommentEntity;
import com.globitel.task.management.system.core.comment.identity.CommentIdentity;
import com.globitel.task.management.system.core.task.entity.TaskEntity;
import com.globitel.task.management.system.core.user.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<CommentEntity, String> {
    Optional<CommentIdentity> findByUuid(String uuid);

    List<CommentEntity> findByTask(TaskEntity task);

    @Modifying
    @Transactional
    @Query("""
    DELETE FROM CommentEntity c
    WHERE c.task = :task
""")
    int deleteByTask(@Param("task") TaskEntity task);


    @Modifying
    @Transactional
    @Query("""

UPDATE  CommentEntity c
SET c.replayedComment=null
where c.task=:task
""")
    void makeReplayedNullByTask(@Param("task") TaskEntity task);

    @Modifying
    @Query("""
    DELETE FROM CommentEntity c
    WHERE c.user=:user
""")
    int deleteByUser(@Param("user") UserEntity userEntity);


    @Modifying
    @Query("""

UPDATE  CommentEntity c
SET c.replayedComment=null
where c.user=:user
""")
    void makeReplayedNullByUser(@Param("user")UserEntity userEntity);
}
