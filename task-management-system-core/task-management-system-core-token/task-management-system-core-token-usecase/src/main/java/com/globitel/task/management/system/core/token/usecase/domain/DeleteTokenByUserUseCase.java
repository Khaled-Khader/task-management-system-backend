package com.globitel.task.management.system.core.token.usecase.domain;

import com.globitel.task.management.system.core.shared.usecase.ValidatorFunctionalUseCase;
import com.globitel.task.management.system.core.token.dao.TokenDao;
import com.globitel.task.management.system.core.user.identity.UserIdentity;
import jakarta.validation.Validator;

public class DeleteTokenByUserUseCase extends ValidatorFunctionalUseCase<Integer, UserIdentity> {

    private final TokenDao tokenDao;

    public DeleteTokenByUserUseCase(Validator validator,TokenDao tokenDao) {
        super(validator);
        this.tokenDao=tokenDao;
    }

    @Override
    protected Integer doExecute(UserIdentity request) {
       return tokenDao.deleteTokensByUserEntity(request);
    }
}
