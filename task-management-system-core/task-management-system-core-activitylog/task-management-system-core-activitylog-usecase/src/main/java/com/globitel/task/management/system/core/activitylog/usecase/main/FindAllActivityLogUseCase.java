package com.globitel.task.management.system.core.activitylog.usecase.main;

import com.globitel.task.management.system.core.activitylog.dao.ActivityLogDao;
import com.globitel.task.management.system.core.activitylog.factory.response.ActivityLogResponse;
import com.globitel.task.management.system.core.shared.usecase.ValidatorSupplierUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FindAllActivityLogUseCase extends ValidatorSupplierUseCase<List<ActivityLogResponse>> {

    private final ActivityLogDao activityLogDao;

    @Override
    protected List<ActivityLogResponse> doExecute() {
        return activityLogDao.findAllActivityLogByOrderCreatedAtDesc().stream()
                .map(activityLogIdentity -> new ActivityLogResponse(
                        activityLogIdentity.getUuid(),
                        activityLogIdentity.getAction(),
                        activityLogIdentity.getCreatedAt(),
                        activityLogIdentity.getUser().getUserName()
                )).toList();
    }
}
