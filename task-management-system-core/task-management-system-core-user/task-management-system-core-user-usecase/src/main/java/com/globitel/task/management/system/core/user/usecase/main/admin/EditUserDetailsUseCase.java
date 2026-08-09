package com.globitel.task.management.system.core.user.usecase.main.admin;

import com.globitel.task.management.system.core.notification.factory.NotificationUseCaseFactory;
import com.globitel.task.management.system.core.notification.factory.domain.NotificationUseCaseFactoryDomain;
import com.globitel.task.management.system.core.notification.factory.domain.request.CreateNotificationRequest;
import com.globitel.task.management.system.core.notification.factory.respose.NotificationResponse;
import com.globitel.task.management.system.core.notification.identity.NotificationIdentity;
import com.globitel.task.management.system.core.shared.exception.BadRequestException;
import com.globitel.task.management.system.core.shared.socket.TMSWebSocket;
import com.globitel.task.management.system.core.shared.usecase.ValidatorFunctionalUseCase;
import com.globitel.task.management.system.core.user.dao.UserDao;
import com.globitel.task.management.system.core.user.factory.request.EditUserDetailsRequest;
import com.globitel.task.management.system.core.user.factory.response.UserResponse;
import com.globitel.task.management.system.core.user.identity.UserIdentity;
import com.globitel.task.management.system.core.user.usecase.error.UserError;
import jakarta.validation.Validator;

public class EditUserDetailsUseCase extends ValidatorFunctionalUseCase<UserResponse, EditUserDetailsRequest> {

    private final UserDao userDao;
    private final TMSWebSocket tmsWebSocket;
    private final NotificationUseCaseFactoryDomain userNotificationUseCaseFactoryDomain;


    public EditUserDetailsUseCase(Validator  validator, UserDao userDao,TMSWebSocket tmsWebSocket,NotificationUseCaseFactoryDomain notificationUseCaseFactoryDomain) {
        super(validator);
        this.userDao = userDao;
        this.tmsWebSocket = tmsWebSocket;
        this.userNotificationUseCaseFactoryDomain = notificationUseCaseFactoryDomain;
    }

    @Override
    protected UserResponse doExecute(EditUserDetailsRequest request) {

        if(request.userName()!=null && userDao.existByUsername(request.userName())){
            throw new BadRequestException(UserError.USER_NAME_EXISTS);
        }

        if(request.email()!=null && userDao.existByEmail(request.email())){
            throw new BadRequestException(UserError.USER_EMAIL_EXISTS);
        }

        UserIdentity userIdentity=userDao.find(request.uuid())
                .orElseThrow(()->new BadRequestException(UserError.USER_NOT_FOUND));

        UserIdentity updatedUser=userDao.updater(userIdentity)
                .userName(request.userName())
                .name(request.name())
                .email(request.email())
                .userRole(request.role())
                .userStatus(request.status())
                .update();

        UserIdentity  savedUser=userDao.save(updatedUser);

        NotificationIdentity notificationIdentity=userNotificationUseCaseFactoryDomain
                .createNotificationUseCase().execute(
                        new CreateNotificationRequest(
                                savedUser,
                                "Your details has been updated by admin ",
                                "Edit details occurs",
                                "i have edit your details"
                        )
                );

        tmsWebSocket.sendMessage(updatedUser.getUserName(),
                new NotificationResponse(
                        notificationIdentity.getUuid(),
                        notificationIdentity.getTitle(),
                        notificationIdentity.getMessage(),
                        notificationIdentity.getAction(),
                        notificationIdentity.getCreatedAt(),
                        notificationIdentity.isRead()
                ));

        return new UserResponse(
                savedUser.getUuid(),
                savedUser.getName(),
                savedUser.getUserName(),
                savedUser.getEmail(),
                savedUser.getUserStatus(),
                savedUser.getUserRole()
        );
    }
}
