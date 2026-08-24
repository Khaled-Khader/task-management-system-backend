package com.globitel.task.management.system.core.token.usecase;

import com.globitel.task.management.system.core.shared.usecase.ValidatorConsumerUseCase;
import com.globitel.task.management.system.core.shared.usecase.ValidatorFunctionalUseCase;
import com.globitel.task.management.system.core.token.dao.TokenDao;
import com.globitel.task.management.system.core.token.factory.domain.TokenUseCaseFactoryDomain;
import com.globitel.task.management.system.core.token.factory.domain.request.CreateTokenRequest;
import com.globitel.task.management.system.core.token.factory.domain.response.CreateTokenResponse;
import com.globitel.task.management.system.core.token.function.TokenManager;
import com.globitel.task.management.system.core.token.usecase.domain.CreateTokenUseCase;
import com.globitel.task.management.system.core.token.usecase.domain.DeleteTokenByUserUseCase;
import com.globitel.task.management.system.core.token.usecase.domain.DeniedTokensByUserUseCase;
import com.globitel.task.management.system.core.user.identity.UserIdentity;
import jakarta.validation.Validator;


public class TokenUseCaseFactoryDomainImpl implements TokenUseCaseFactoryDomain {

    private final CreateTokenUseCase createTokenUseCase;
    private final DeniedTokensByUserUseCase deniedTokensByUserUseCase;
    private final DeleteTokenByUserUseCase deleteTokenByUserUseCase;
    public TokenUseCaseFactoryDomainImpl(TokenDao tokenDao, Validator validator, TokenManager tokenManager){
        this.createTokenUseCase=new CreateTokenUseCase(validator,tokenDao,tokenManager);
        this.deniedTokensByUserUseCase =new DeniedTokensByUserUseCase(validator,tokenDao);
        this.deleteTokenByUserUseCase=new DeleteTokenByUserUseCase(validator,tokenDao);
    }

    @Override
    public ValidatorFunctionalUseCase< CreateTokenResponse,CreateTokenRequest> createTokenUseCase() {
        return createTokenUseCase;
    }

    @Override
    public ValidatorConsumerUseCase<UserIdentity> deniedTokensByUserUseCase() {
        return deniedTokensByUserUseCase;
    }

    @Override
    public ValidatorFunctionalUseCase<Integer, UserIdentity> deleteTokenByUserUseCase() {
        return deleteTokenByUserUseCase;
    }
}
