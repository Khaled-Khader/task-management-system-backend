package com.globitel.task.management.system.core.task.usecase.main.user;

import com.globitel.task.management.system.core.shared.security.context.TMSSecurityContext;
import com.globitel.task.management.system.core.shared.usecase.ValidatorSupplierUseCase;
import com.globitel.task.management.system.core.task.dao.TaskDao;
import com.globitel.task.management.system.core.task.factory.response.TaskResponse;
import com.globitel.task.management.system.core.task.identity.TaskIdentity;
import com.globitel.task.management.system.core.user.factory.domain.UserUseCaseFactoryDomain;
import com.globitel.task.management.system.core.user.identity.UserIdentity;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FindTasksForUserUseCase extends ValidatorSupplierUseCase<List<TaskResponse>> {

    private final TaskDao taskDao;
    private final TMSSecurityContext tmSecurityContext;
    private final UserUseCaseFactoryDomain userUseCaseFactoryDomain;

    @Override
    protected List<TaskResponse> doExecute() {
        String userName=tmSecurityContext.getCurrentUserUserName();
        UserIdentity currentUser=userUseCaseFactoryDomain.findUserByUsernameUseCase().execute(userName);
        List<TaskIdentity> tasks=taskDao.findTasksByUser(currentUser);
        return tasks.stream().map(
                task -> new TaskResponse(task.getUuid(), task.getTitle(), task.getDescription(), task.getUser().getUuid(),task.getUser()!=null?task.getUser().getName():null, task.getDueDate(), task.getTaskStatus())
        ).toList();
    }
}
