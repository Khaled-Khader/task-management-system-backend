package com.globitel.task.management.system.core.activitylog.jpa;

import com.globitel.task.management.system.core.activitylog.entity.ActivityLogEntity;
import com.globitel.task.management.system.core.user.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityLogRepository extends JpaRepository<ActivityLogEntity, String> {
    List<ActivityLogEntity> findAllByOrderByCreatedAtDesc();


    int deleteByUser(UserEntity user);
}
