package com.globitel.task.management.system.core.task.jpa;

import com.globitel.task.management.system.core.task.entity.TaskEntity;
import com.globitel.task.management.system.core.task.identity.TaskIdentity;
import com.globitel.task.management.system.core.user.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, String> {


    @Transactional
    int deleteTaskByUuid(String uuid);

    List<TaskIdentity> findByUser(UserEntity user);

    @Modifying
    @Query("""
        update TaskEntity t 
        set t.user=null 
        where t.user=:user

            """)
    void makeUserNull(@Param("user") UserEntity user);

}
