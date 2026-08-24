package com.globitel.task.management.system.core.user.jpa;

import com.globitel.task.management.system.core.user.entity.UserEntity;
import com.globitel.task.management.system.core.user.entity.embedded.UserRole;
import com.globitel.task.management.system.core.user.entity.embedded.UserStatus;
import com.globitel.task.management.system.core.user.identity.UserIdentity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    boolean existsByUserName(String username);

    boolean existsByEmail(String email);


    int deleteByUserName(String username);

    List<UserEntity> findAllByName(String name);
    List<UserEntity> findAllByUserRole(UserRole userRole);

    List<UserEntity> findAllByUserStatus(UserStatus userStatus);

    Optional<UserEntity> findByUserName(String username);
}
