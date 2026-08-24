package com.globitel.task.management.system.core.user.factory;

import com.globitel.task.management.system.core.shared.usecase.ValidatorFunctionalUseCase;
import com.globitel.task.management.system.core.shared.usecase.ValidatorSupplierUseCase;
import com.globitel.task.management.system.core.user.factory.request.*;
import com.globitel.task.management.system.core.user.factory.response.*;

import java.util.List;

public interface UserUseCaseFactory {
    ValidatorFunctionalUseCase<UserResponse, CreateUserRequest> createUserUseCase();

    ValidatorSupplierUseCase<List<FindAllUsersResponse>> findAllUsersUseCase();

    ValidatorFunctionalUseCase<DeleteUserByUserNameResponse, DeleteUserByUserNameRequest> deleteUserByUserNameUseCase();

    ValidatorFunctionalUseCase<List<FindAllUsersResponse>, FindAllUsersByRequest> findAllUsersByNameUseCase();

    ValidatorFunctionalUseCase<List<FindAllUsersResponse>, FindAllUsersByRequest> findAllUsersByUserRoleUseCase();

    ValidatorFunctionalUseCase<List<FindAllUsersResponse>, FindAllUsersByRequest> findAllUsersByUserStatusUseCase();

    ValidatorFunctionalUseCase<UserResponse, EditUserDetailsRequest> editUserDetailsUseCase();

    ValidatorFunctionalUseCase<UserSecurityResponse, String> findUserByUserNameForSecurityUseCase();

    ValidatorSupplierUseCase<ViewUserDataResponse> viewUserDataUseCase();

    ValidatorFunctionalUseCase<String, String> findUserNameByUserUuidUseCase();

    ValidatorFunctionalUseCase<UserUpdatedResponse, EditDataForUserRequest> editUserDataForUserUseCase();

    ValidatorSupplierUseCase<UserResponse> getCurrentUserUseCase();

    ValidatorFunctionalUseCase<UserResponse,String> findUserByUuidUseCase();

    ValidatorFunctionalUseCase<LoginResponse,String> returnUserDataAfterAuthenticatedUseCase();
}
