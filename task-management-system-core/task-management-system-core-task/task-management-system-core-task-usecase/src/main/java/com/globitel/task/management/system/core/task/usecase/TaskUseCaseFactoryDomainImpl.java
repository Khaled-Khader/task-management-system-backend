package com.globitel.task.management.system.core.task.usecase;

import com.globitel.task.management.system.core.shared.usecase.ValidatorConsumerUseCase;
import com.globitel.task.management.system.core.shared.usecase.ValidatorFunctionalUseCase;
import com.globitel.task.management.system.core.task.dao.TaskDao;
import com.globitel.task.management.system.core.task.factory.domain.TaskUseCaseFactoryDomain;
import com.globitel.task.management.system.core.task.identity.TaskIdentity;
import com.globitel.task.management.system.core.task.usecase.domain.FindTaskByUuidUseCase;
import com.globitel.task.management.system.core.task.usecase.domain.MakeUserTaskNullUseCase;
import com.globitel.task.management.system.core.user.identity.UserIdentity;
import jakarta.validation.Validator;

public class TaskUseCaseFactoryDomainImpl implements TaskUseCaseFactoryDomain {

    private final FindTaskByUuidUseCase  findTaskByUuidUseCase;
    private final MakeUserTaskNullUseCase makeUserTaskNullUseCase;
    public TaskUseCaseFactoryDomainImpl(Validator validator, TaskDao  taskDao) {
        this.findTaskByUuidUseCase=new  FindTaskByUuidUseCase(validator,taskDao);
        this.makeUserTaskNullUseCase=new MakeUserTaskNullUseCase(validator,taskDao);
    }

    @Override
    public ValidatorConsumerUseCase<UserIdentity> makeUserTaskNullUseCase() {
        return makeUserTaskNullUseCase;
    }

    @Override
    public ValidatorFunctionalUseCase<TaskIdentity, String> findTaskByUuidUseCase() {
        return findTaskByUuidUseCase;
    }
}
