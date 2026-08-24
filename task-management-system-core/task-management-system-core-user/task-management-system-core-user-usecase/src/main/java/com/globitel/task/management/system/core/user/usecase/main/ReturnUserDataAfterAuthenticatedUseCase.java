package com.globitel.task.management.system.core.user.usecase.main;

import com.globitel.task.management.system.core.shared.exception.BadRequestException;
import com.globitel.task.management.system.core.shared.exception.UnAuthorizedException;
import com.globitel.task.management.system.core.shared.usecase.ValidatorFunctionalUseCase;
import com.globitel.task.management.system.core.token.factory.domain.TokenUseCaseFactoryDomain;
import com.globitel.task.management.system.core.token.factory.domain.request.CreateTokenRequest;
import com.globitel.task.management.system.core.token.factory.domain.response.CreateTokenResponse;
import com.globitel.task.management.system.core.user.dao.UserDao;
import com.globitel.task.management.system.core.user.factory.response.LoginResponse;
import com.globitel.task.management.system.core.user.identity.UserIdentity;
import com.globitel.task.management.system.core.user.usecase.error.UserError;
import jakarta.validation.Validator;

public class ReturnUserDataAfterAuthenticatedUseCase extends ValidatorFunctionalUseCase<LoginResponse,String> {

    private final TokenUseCaseFactoryDomain tokenUseCaseFactoryDomain;
    private final UserDao userDao;
    public ReturnUserDataAfterAuthenticatedUseCase(Validator validator,TokenUseCaseFactoryDomain tokenUseCaseFactoryDomain,UserDao userDao) {
        super(validator);
        this.tokenUseCaseFactoryDomain=tokenUseCaseFactoryDomain;
        this.userDao=userDao;
    }

    @Override
    protected LoginResponse doExecute(String request) {

        UserIdentity userIdentity = userDao.findByUsername(request)
                .orElseThrow(() -> new BadRequestException(UserError.USER_NOT_FOUND));

        if(userIdentity.getUserStatus().name().equals("INACTIVE")){
            throw new UnAuthorizedException(UserError.USER_NOT_ACTIVE);
        }
        CreateTokenResponse createTokenResponse = tokenUseCaseFactoryDomain.createTokenUseCase().execute(new CreateTokenRequest(userIdentity));

        return new LoginResponse(
                createTokenResponse.token(),
                userIdentity.getUuid(),
                userIdentity.getName(),
                userIdentity.getUserName(),
                userIdentity.getEmail(),
                userIdentity.getUserStatus(),
                userIdentity.getUserRole()
        );

    }
}
