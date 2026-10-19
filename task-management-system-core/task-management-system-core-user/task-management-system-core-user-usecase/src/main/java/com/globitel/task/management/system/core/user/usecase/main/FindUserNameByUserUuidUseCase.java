package com.globitel.task.management.system.core.user.usecase.main;

import com.globitel.task.management.system.core.shared.exception.BadRequestException;
import com.globitel.task.management.system.core.shared.technologies.TMSCache;
import com.globitel.task.management.system.core.shared.usecase.ValidatorFunctionalUseCase;
import com.globitel.task.management.system.core.user.dao.UserDao;
import com.globitel.task.management.system.core.user.usecase.error.UserError;
import jakarta.validation.Validator;

import java.util.Optional;



public class FindUserNameByUserUuidUseCase extends ValidatorFunctionalUseCase<String,String> {

    private final UserDao userDao;
    private final TMSCache tmsCache;

    public FindUserNameByUserUuidUseCase(Validator validator, UserDao userDao, TMSCache tmsCache) {
        super(validator);
        this.userDao = userDao;
        this.tmsCache = tmsCache;
    }

    @Override
    protected String doExecute(String request) {

       String cachedValue= Optional.ofNullable(tmsCache.get(request)).orElse(
                userDao.find(request)
                        .orElseThrow(()->new BadRequestException(UserError.USER_NOT_FOUND))
                        .getUserName()
               );
        tmsCache.put(request,cachedValue);
        return cachedValue;
    }
}
