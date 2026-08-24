package com.globitel.task.management.system.core.token.factory.domain;


import com.globitel.task.management.system.core.shared.usecase.ValidatorConsumerUseCase;
import com.globitel.task.management.system.core.shared.usecase.ValidatorFunctionalUseCase;
import com.globitel.task.management.system.core.token.factory.domain.request.CreateTokenRequest;
import com.globitel.task.management.system.core.token.factory.domain.response.CreateTokenResponse;
import com.globitel.task.management.system.core.user.identity.UserIdentity;

public interface TokenUseCaseFactoryDomain {
    ValidatorFunctionalUseCase< CreateTokenResponse,CreateTokenRequest> createTokenUseCase();
    ValidatorConsumerUseCase<UserIdentity> deniedTokensByUserUseCase();
    ValidatorFunctionalUseCase<Integer, UserIdentity> deleteTokenByUserUseCase();
}
