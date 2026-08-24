package com.globitel.task.management.system.core.notification.jpa;

import com.globitel.task.management.system.core.notification.entity.NotificationEntity;
import com.globitel.task.management.system.core.user.entity.UserEntity;
import com.globitel.task.management.system.core.user.identity.UserIdentity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository  extends JpaRepository<NotificationEntity,String> {


    int deleteByUser(UserEntity userEntity);

    List<NotificationEntity> findAllByUser(UserEntity user);
}
