package com.globitel.task.management.system.core.user.usecase;

import com.globitel.task.management.system.core.shared.usecase.ValidatorFunctionalUseCase;
import com.globitel.task.management.system.core.token.factory.domain.TokenUseCaseFactoryDomain;
import com.globitel.task.management.system.core.user.dao.UserDao;
import com.globitel.task.management.system.core.user.factory.security.UserUseCaseSecurityFactory;
import com.globitel.task.management.system.core.user.factory.security.request.LoginRequest;
import com.globitel.task.management.system.core.user.factory.security.response.LoginResponse;
import com.globitel.task.management.system.core.user.function.TMSAuthenticator;
import com.globitel.task.management.system.core.user.usecase.main.FindUserNameByUserUuidUseCase;
import com.globitel.task.management.system.core.user.usecase.main.LoginUseCase;
import jakarta.validation.Validator;

public class UserUseCaseSecurityFactoryImpl implements UserUseCaseSecurityFactory {

    private final LoginUseCase loginUseCase;
    private final FindUserNameByUserUuidUseCase findUserNameByUserUuidUseCase;
    public UserUseCaseSecurityFactoryImpl(Validator  validator, TMSAuthenticator tmsAuthenticator, UserDao userDao, TokenUseCaseFactoryDomain tokenUseCaseFactoryDomain) {
        this.loginUseCase = new LoginUseCase(validator, tmsAuthenticator, userDao, tokenUseCaseFactoryDomain);
        this.findUserNameByUserUuidUseCase= new FindUserNameByUserUuidUseCase(validator, userDao);
    }




    @Override
    public ValidatorFunctionalUseCase<LoginResponse, LoginRequest> loginUseCase() {
        return loginUseCase;
    }
}
