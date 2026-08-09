package com.globitel.task.management.system.core.user.factory.security;

import com.globitel.task.management.system.core.shared.usecase.ValidatorFunctionalUseCase;
import com.globitel.task.management.system.core.user.factory.security.request.LoginRequest;
import com.globitel.task.management.system.core.user.factory.security.response.LoginResponse;

public interface UserUseCaseSecurityFactory {

    ValidatorFunctionalUseCase<LoginResponse, LoginRequest> loginUseCase();
}
