package com.globitel.task.management.system.core.task.port;


import com.globitel.task.management.system.core.comment.factory.domain.CommentUseCaseFactoryDomain;
import com.globitel.task.management.system.core.notification.factory.domain.NotificationUseCaseFactoryDomain;
import com.globitel.task.management.system.core.shared.security.context.TMSSecurityContext;
import com.globitel.task.management.system.core.shared.socket.TMSWebSocket;
import com.globitel.task.management.system.core.task.dao.TaskDao;
import com.globitel.task.management.system.core.task.factory.TaskUseCaseFactory;
import com.globitel.task.management.system.core.task.factory.domain.TaskUseCaseFactoryDomain;
import com.globitel.task.management.system.core.task.jpa.TaskJpa;
import com.globitel.task.management.system.core.task.jpa.TaskRepository;
import com.globitel.task.management.system.core.task.usecase.TaskUseCaseFactoryDomainImpl;
import com.globitel.task.management.system.core.task.usecase.TaskUseCaseFactoryImpl;
import com.globitel.task.management.system.core.user.factory.domain.UserUseCaseFactoryDomain;
import jakarta.validation.Validator;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.globitel.task.management.system.core.task.entity")
@EnableJpaRepositories(basePackages = "com.globitel.task.management.system.core.task.jpa")
public class TaskConfiguration {

    @Bean
    public TaskDao taskDao(TaskRepository  taskRepository) {
        return new TaskJpa(taskRepository);
    }

    @Bean
    public TaskUseCaseFactory taskUseCaseFactory(Validator validator, TaskDao  taskDao, UserUseCaseFactoryDomain  userUseCaseFactoryDomain, TMSSecurityContext tmsSecurityContext, CommentUseCaseFactoryDomain commentUseCaseFactoryDomain, NotificationUseCaseFactoryDomain notificationUseCaseFactoryDomain, TMSWebSocket tmsWebSocket) {
        return new TaskUseCaseFactoryImpl(validator, taskDao,userUseCaseFactoryDomain, tmsSecurityContext,commentUseCaseFactoryDomain,notificationUseCaseFactoryDomain,tmsWebSocket);
    }

    @Bean
    public TaskUseCaseFactoryDomain  taskUseCaseFactoryDomain(Validator validator,TaskDao taskDao) {
        return new TaskUseCaseFactoryDomainImpl(validator,taskDao);
    }
}
