package com.globitel.task.management.system.core.notification.usecase.main;

import com.globitel.task.management.system.core.notification.dao.NotificationDao;
import com.globitel.task.management.system.core.notification.factory.respose.NotificationResponse;
import com.globitel.task.management.system.core.shared.security.context.TMSSecurityContext;
import com.globitel.task.management.system.core.shared.usecase.ValidatorSupplierUseCase;
import com.globitel.task.management.system.core.user.factory.domain.UserUseCaseFactoryDomain;
import com.globitel.task.management.system.core.user.identity.UserIdentity;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FindAllUserNotificationUseCase extends ValidatorSupplierUseCase<List<NotificationResponse>> {

    private final NotificationDao  notificationDao;
    private final TMSSecurityContext tmsSecurityContext;
    private  final UserUseCaseFactoryDomain  userUseCaseFactoryDomain;

    @Override
    protected List<NotificationResponse> doExecute() {
        String userName= tmsSecurityContext.getCurrentUserUserName();
        UserIdentity userIdentity=userUseCaseFactoryDomain.findUserByUsernameUseCase().execute(userName);

        return notificationDao.findAllByUser(userIdentity)
                .stream()
                .map(notificationIdentity -> new NotificationResponse(
                        notificationIdentity.getUuid(),
                        notificationIdentity.getTitle(),
                        notificationIdentity.getMessage(),
                        notificationIdentity.getAction(),
                        notificationIdentity.getCreatedAt(),
                        notificationIdentity.isRead()
                )).toList();
    }
}
