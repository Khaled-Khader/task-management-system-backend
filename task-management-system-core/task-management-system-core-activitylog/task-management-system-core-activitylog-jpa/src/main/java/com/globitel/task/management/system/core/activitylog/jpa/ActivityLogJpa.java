package com.globitel.task.management.system.core.activitylog.jpa;

import com.globitel.task.management.system.core.activitylog.dao.ActivityLogCreator;
import com.globitel.task.management.system.core.activitylog.dao.ActivityLogDao;
import com.globitel.task.management.system.core.activitylog.dao.ActivityLogUpdater;
import com.globitel.task.management.system.core.activitylog.entity.ActivityLogEntity;
import com.globitel.task.management.system.core.activitylog.identity.ActivityLogIdentity;
import com.globitel.task.management.system.core.user.entity.UserEntity;
import com.globitel.task.management.system.core.user.identity.UserIdentity;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ActivityLogJpa implements ActivityLogDao {

    private final ActivityLogRepository   activityLogRepository;

    @Override
    public ActivityLogIdentity save(ActivityLogIdentity entity) {
        return activityLogRepository.save((ActivityLogEntity) entity);
    }

    @Override
    public Optional<ActivityLogIdentity> find(String uuid) {
        return Optional.empty();
    }

    @Override
    public List<ActivityLogIdentity> findAll() {
        return List.copyOf(activityLogRepository.findAll());
    }

    @Override
    public ActivityLogCreator creator() {
        return new ActivityLogCreatorImpl();
    }

    @Override
    public ActivityLogUpdater updater(ActivityLogIdentity entity) {
        return null;
    }

    @Override
    public int deleteByUser(UserIdentity userIdentity) {
        return activityLogRepository.deleteByUser((UserEntity) userIdentity);
    }

    @Override
    public List<ActivityLogIdentity> findAllActivityLogByOrderCreatedAtDesc() {
        return List.copyOf(activityLogRepository.findAllByOrderByCreatedAtDesc());
    }
}
