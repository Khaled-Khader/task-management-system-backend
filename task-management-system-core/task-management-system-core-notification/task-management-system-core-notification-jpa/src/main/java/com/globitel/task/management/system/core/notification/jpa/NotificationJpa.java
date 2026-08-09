package com.globitel.task.management.system.core.notification.jpa;

import com.globitel.task.management.system.core.notification.dao.NotificationCreator;
import com.globitel.task.management.system.core.notification.dao.NotificationDao;
import com.globitel.task.management.system.core.notification.dao.NotificationUpdater;
import com.globitel.task.management.system.core.notification.entity.NotificationEntity;
import com.globitel.task.management.system.core.notification.identity.NotificationIdentity;
import com.globitel.task.management.system.core.user.entity.UserEntity;
import com.globitel.task.management.system.core.user.identity.UserIdentity;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RequiredArgsConstructor
public class NotificationJpa implements NotificationDao {

    private final NotificationRepository   notificationRepository;

    @Override
    public NotificationIdentity save(NotificationIdentity entity) {
        return notificationRepository.save((NotificationEntity) entity);
    }

    @Override
    public Optional<NotificationIdentity> find(String uuid) {
        return notificationRepository.findById(uuid).map(Function.identity());
    }

    @Override
    public List<NotificationIdentity> findAll() {
        return List.copyOf(notificationRepository.findAll());
    }

    @Override
    public NotificationCreator creator() {
        return new NotificationCreatorImpl();
    }

    @Override
    public NotificationUpdater updater(NotificationIdentity entity) {
        return new NotificationUpdaterImpl((NotificationEntity) entity);
    }

    @Override
    public List<NotificationIdentity> findAllByUser(UserIdentity userIdentity) {
        return List.copyOf(notificationRepository.findAllByUser((UserEntity) userIdentity));
    }
}
