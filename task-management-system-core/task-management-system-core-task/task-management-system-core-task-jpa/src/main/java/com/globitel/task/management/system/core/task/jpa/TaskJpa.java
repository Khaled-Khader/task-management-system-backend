package com.globitel.task.management.system.core.task.jpa;

import com.globitel.task.management.system.core.task.dao.TaskCreator;
import com.globitel.task.management.system.core.task.dao.TaskDao;
import com.globitel.task.management.system.core.task.dao.TaskUpdater;
import com.globitel.task.management.system.core.task.entity.TaskEntity;
import com.globitel.task.management.system.core.task.identity.TaskIdentity;
import com.globitel.task.management.system.core.user.entity.UserEntity;
import com.globitel.task.management.system.core.user.identity.UserIdentity;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RequiredArgsConstructor
public class TaskJpa implements TaskDao {

    private final TaskRepository taskRepository;

    @Override
    public TaskIdentity save(TaskIdentity entity) {
        return taskRepository.save((TaskEntity) entity);
    }

    @Override
    public Optional<TaskIdentity> find(String uuid) {
        return taskRepository.findById(uuid).map(Function.identity());
    }

    @Override
    public List<TaskIdentity> findAll() {
        return List.copyOf(taskRepository.findAll());
    }

    @Override
    public TaskCreator creator() {
        return new TaskCreatorImpl();
    }

    @Override
    public TaskUpdater updater(TaskIdentity entity) {
        return new TaskUpdaterImpl((TaskEntity) entity);
    }

    @Override
    public int deleteTask(String uuid) {
        return taskRepository.deleteTaskByUuid(uuid);
    }


    @Override
    public List<TaskIdentity> findTasksByUser(UserIdentity userIdentity) {
        return taskRepository.findByUser((UserEntity) userIdentity);
    }

    @Override
    public void makeUserNull(UserIdentity userIdentity) {
        taskRepository.makeUserNull((UserEntity) userIdentity);
    }
}
