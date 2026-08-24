package com.globitel.task.management.system.core.user.usecase;

import com.globitel.task.management.system.core.activitylog.factory.domain.ActivityLogUseCaseFactoryDomain;
import com.globitel.task.management.system.core.comment.factory.domain.CommentUseCaseFactoryDomain;
import com.globitel.task.management.system.core.notification.factory.domain.NotificationUseCaseFactoryDomain;
import com.globitel.task.management.system.core.shared.security.context.TMSSecurityContext;
import com.globitel.task.management.system.core.shared.socket.TMSWebSocket;
import com.globitel.task.management.system.core.shared.technologies.TransactionExecutor;
import com.globitel.task.management.system.core.shared.usecase.ValidatorFunctionalUseCase;
import com.globitel.task.management.system.core.shared.usecase.ValidatorSupplierUseCase;
import com.globitel.task.management.system.core.task.factory.domain.TaskUseCaseFactoryDomain;
import com.globitel.task.management.system.core.token.factory.domain.TokenUseCaseFactoryDomain;
import com.globitel.task.management.system.core.user.dao.UserDao;
import com.globitel.task.management.system.core.user.factory.request.*;
import com.globitel.task.management.system.core.user.factory.response.*;
import com.globitel.task.management.system.core.user.factory.UserUseCaseFactory;
import com.globitel.task.management.system.core.user.function.TMSPasswordEncoder;
import com.globitel.task.management.system.core.user.usecase.main.*;
import com.globitel.task.management.system.core.user.usecase.main.admin.*;
import com.globitel.task.management.system.core.user.usecase.main.user.EditDataForUserUseCase;
import com.globitel.task.management.system.core.user.usecase.main.user.ViewUserDataUseCase;
import jakarta.validation.Validator;

import java.util.List;

public class UserUseCaseFactoryImpl implements UserUseCaseFactory {
    private final CreateUserUseCase createUserUseCase;
    private final FindAllUsersUseCase findAllUsersUseCase;
    private final DeleteUserByUserNameUseCase deleteUserByUserNameUseCase;
    private final FindAllUsersByRoleUseCase findAllUsersByUserRoleUseCase;
    private final FindAllUsersByStatusUseCase findAllUsersByUserStatusUseCase;
    private final FindAllUsersByNameUseCase findAllUsersByNameUseCase;
    private final EditUserDetailsUseCase editUserDetailsUseCase;
    private final FindUserNameByUserUuidUseCase findUserNameByUserUuidUseCase;
    private final FindUserByUserNameForSecurityUseCase findUserByUserNameForSecurityUseCase;
    private final ViewUserDataUseCase viewUserDataUseCase;
    private final EditDataForUserUseCase editUserDataForUserUseCase;
    private final GetCurrentUserUseCase getCurrentUserUseCase;
    private final FindUserByIdUseCase findUserByIdUseCase;
    private final ReturnUserDataAfterAuthenticatedUseCase returnUserDataAfterAuthenticatedUseCase;

    public UserUseCaseFactoryImpl(Validator validator, UserDao userDao, TMSPasswordEncoder tmsPasswordEncoder, TMSSecurityContext tMSSecurityContext, TMSWebSocket tmsWebSocket, NotificationUseCaseFactoryDomain notificationUseCaseFactoryDomain, TaskUseCaseFactoryDomain taskUseCaseFactoryDomain, CommentUseCaseFactoryDomain commentUseCaseFactoryDomain, TokenUseCaseFactoryDomain tokenUseCaseFactoryDomain, ActivityLogUseCaseFactoryDomain activityLogUseCaseFactoryDomain, TransactionExecutor transactionExecutor) {
        this.createUserUseCase = new CreateUserUseCase(validator, userDao, tmsPasswordEncoder);
        this.findAllUsersUseCase = new FindAllUsersUseCase(userDao);
        this.deleteUserByUserNameUseCase = new DeleteUserByUserNameUseCase(validator, userDao,taskUseCaseFactoryDomain,notificationUseCaseFactoryDomain,tokenUseCaseFactoryDomain,commentUseCaseFactoryDomain,activityLogUseCaseFactoryDomain,transactionExecutor);
        this.findAllUsersByUserRoleUseCase = new FindAllUsersByRoleUseCase(validator, userDao);
        this.findAllUsersByUserStatusUseCase = new FindAllUsersByStatusUseCase(validator, userDao);
        this.findAllUsersByNameUseCase = new FindAllUsersByNameUseCase(validator, userDao);
        this.editUserDetailsUseCase = new EditUserDetailsUseCase(validator, userDao, tmsWebSocket,notificationUseCaseFactoryDomain);
        this.findUserNameByUserUuidUseCase = new FindUserNameByUserUuidUseCase(validator, userDao);
        this.findUserByUserNameForSecurityUseCase = new FindUserByUserNameForSecurityUseCase(validator, userDao);
        this.viewUserDataUseCase = new ViewUserDataUseCase(userDao, tMSSecurityContext);
        this.editUserDataForUserUseCase = new EditDataForUserUseCase(validator, userDao, tMSSecurityContext, tmsPasswordEncoder);
        this.getCurrentUserUseCase = new GetCurrentUserUseCase(tMSSecurityContext, userDao);
        this.findUserByIdUseCase = new FindUserByIdUseCase(validator, userDao);
        this.returnUserDataAfterAuthenticatedUseCase=new ReturnUserDataAfterAuthenticatedUseCase(validator,tokenUseCaseFactoryDomain,userDao);
    }

    @Override
    public ValidatorFunctionalUseCase<UserResponse, CreateUserRequest> createUserUseCase() {
        return createUserUseCase;
    }

    @Override
    public ValidatorSupplierUseCase<List<FindAllUsersResponse>> findAllUsersUseCase() {
        return findAllUsersUseCase;
    }

    @Override
    public ValidatorFunctionalUseCase<DeleteUserByUserNameResponse, DeleteUserByUserNameRequest> deleteUserByUserNameUseCase() {
        return deleteUserByUserNameUseCase;
    }

    @Override
    public ValidatorFunctionalUseCase<List<FindAllUsersResponse>, FindAllUsersByRequest> findAllUsersByNameUseCase() {
        return findAllUsersByNameUseCase;
    }

    @Override
    public ValidatorFunctionalUseCase<List<FindAllUsersResponse>, FindAllUsersByRequest> findAllUsersByUserRoleUseCase() {
        return findAllUsersByUserRoleUseCase;
    }

    @Override
    public ValidatorFunctionalUseCase<List<FindAllUsersResponse>, FindAllUsersByRequest> findAllUsersByUserStatusUseCase() {
        return findAllUsersByUserStatusUseCase;
    }

    @Override
    public ValidatorFunctionalUseCase<UserResponse, EditUserDetailsRequest> editUserDetailsUseCase() {
        return editUserDetailsUseCase;
    }

    @Override
    public ValidatorFunctionalUseCase<UserSecurityResponse, String> findUserByUserNameForSecurityUseCase() {
        return findUserByUserNameForSecurityUseCase;
    }

    @Override
    public ValidatorSupplierUseCase<ViewUserDataResponse> viewUserDataUseCase() {
        return viewUserDataUseCase;
    }

    @Override
    public ValidatorFunctionalUseCase<String, String> findUserNameByUserUuidUseCase() {
        return findUserNameByUserUuidUseCase;
    }

    @Override
    public ValidatorFunctionalUseCase<UserUpdatedResponse, EditDataForUserRequest> editUserDataForUserUseCase() {
        return editUserDataForUserUseCase;
    }

    @Override
    public ValidatorSupplierUseCase<UserResponse> getCurrentUserUseCase() {
        return getCurrentUserUseCase;
    }

    @Override
    public ValidatorFunctionalUseCase<UserResponse, String> findUserByUuidUseCase() {
        return findUserByIdUseCase;
    }

    @Override
    public ValidatorFunctionalUseCase<LoginResponse, String> returnUserDataAfterAuthenticatedUseCase() {
        return returnUserDataAfterAuthenticatedUseCase;
    }

}
