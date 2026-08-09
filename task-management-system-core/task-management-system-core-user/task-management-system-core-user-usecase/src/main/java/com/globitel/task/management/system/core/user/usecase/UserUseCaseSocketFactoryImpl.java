package com.globitel.task.management.system.core.user.usecase;

import com.globitel.task.management.system.core.shared.usecase.ValidatorFunctionalUseCase;
import com.globitel.task.management.system.core.token.factory.domain.TokenUseCaseFactoryDomain;
import com.globitel.task.management.system.core.user.dao.UserDao;
import com.globitel.task.management.system.core.user.factory.socket.UserUseCaseSocketFactory;
import com.globitel.task.management.system.core.user.function.TMSAuthenticator;
import com.globitel.task.management.system.core.user.usecase.main.FindUserNameByUserUuidUseCase;
import com.globitel.task.management.system.core.user.usecase.main.LoginUseCase;
import jakarta.validation.Validator;

public class UserUseCaseSocketFactoryImpl implements UserUseCaseSocketFactory {

    private final FindUserNameByUserUuidUseCase findUserNameByUserUuidUseCase;
    public UserUseCaseSocketFactoryImpl(Validator validator,  UserDao userDao) {
        this.findUserNameByUserUuidUseCase= new FindUserNameByUserUuidUseCase(validator, userDao);
    }
    @Override
    public ValidatorFunctionalUseCase<String, String> findUserNameByUserUuidUseCase() {
        return findUserNameByUserUuidUseCase;
    }
}
