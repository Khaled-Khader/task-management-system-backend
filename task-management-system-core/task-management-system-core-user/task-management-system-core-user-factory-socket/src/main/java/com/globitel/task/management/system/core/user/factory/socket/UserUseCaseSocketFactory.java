package com.globitel.task.management.system.core.user.factory.socket;

import com.globitel.task.management.system.core.shared.usecase.ValidatorFunctionalUseCase;

public interface UserUseCaseSocketFactory {
    ValidatorFunctionalUseCase<String,String> findUserNameByUserUuidUseCase();
}
