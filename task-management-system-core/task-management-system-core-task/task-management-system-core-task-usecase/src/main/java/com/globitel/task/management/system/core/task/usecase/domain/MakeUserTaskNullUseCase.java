package com.globitel.task.management.system.core.task.usecase.domain;

import com.globitel.task.management.system.core.shared.usecase.ValidatorConsumerUseCase;
import com.globitel.task.management.system.core.task.dao.TaskDao;
import com.globitel.task.management.system.core.task.identity.TaskIdentity;
import com.globitel.task.management.system.core.user.identity.UserIdentity;
import jakarta.validation.Validator;

import java.util.List;

public class MakeUserTaskNullUseCase extends ValidatorConsumerUseCase<UserIdentity> {

    private final TaskDao taskDao;

    public MakeUserTaskNullUseCase(Validator validator,TaskDao taskDao) {
        super(validator);
        this.taskDao = taskDao;
    }

    @Override
    protected void doExecute(UserIdentity userIdentity) {
        System.out.println("BEFORE MAKING USER TASK NULL USE CASE");
        taskDao.makeUserNull(userIdentity);
        System.out.println("AFTER MAKING USER TASK NULL USE CASE");
    }
}
