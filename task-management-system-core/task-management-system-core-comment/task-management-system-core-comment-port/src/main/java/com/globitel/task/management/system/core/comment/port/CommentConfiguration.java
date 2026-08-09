package com.globitel.task.management.system.core.comment.port;

import com.globitel.task.management.system.core.comment.dao.CommentDao;
import com.globitel.task.management.system.core.comment.factory.CommentUseCaseFactory;
import com.globitel.task.management.system.core.comment.factory.domain.CommentUseCaseFactoryDomain;
import com.globitel.task.management.system.core.comment.jpa.CommentJpa;
import com.globitel.task.management.system.core.comment.jpa.CommentRepository;
import com.globitel.task.management.system.core.comment.usecase.CommentUseCaseFactoryDomainImpl;
import com.globitel.task.management.system.core.comment.usecase.CommentUseCaseFactoryImpl;
import com.globitel.task.management.system.core.notification.factory.domain.NotificationUseCaseFactoryDomain;
import com.globitel.task.management.system.core.shared.security.context.TMSSecurityContext;
import com.globitel.task.management.system.core.shared.socket.TMSWebSocket;
import com.globitel.task.management.system.core.task.factory.domain.TaskUseCaseFactoryDomain;
import com.globitel.task.management.system.core.user.factory.domain.UserUseCaseFactoryDomain;
import jakarta.validation.Validator;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.globitel.task.management.system.core.comment.entity")
@EnableJpaRepositories(basePackages = "com.globitel.task.management.system.core.comment.jpa")
public class CommentConfiguration {

    @Bean
    public CommentDao  commentDao(CommentRepository  commentRepository) {
        return new CommentJpa(commentRepository);
    }

    @Bean
    public CommentUseCaseFactory  commentUseCaseFactory(Validator validator, TMSSecurityContext  tmsSecurityContext, CommentDao  commentDao, UserUseCaseFactoryDomain  userUseCaseFactoryDomain, TaskUseCaseFactoryDomain   taskUseCaseFactoryDomain, NotificationUseCaseFactoryDomain notificationUseCaseFactoryDomain, TMSWebSocket tmsWebSocket) {
        return new CommentUseCaseFactoryImpl(commentDao,validator,tmsSecurityContext,userUseCaseFactoryDomain,taskUseCaseFactoryDomain,notificationUseCaseFactoryDomain,tmsWebSocket);
    }

    @Bean
    public CommentUseCaseFactoryDomain commentUseCaseFactoryDomain(Validator validator,CommentDao commentDao){
        return new CommentUseCaseFactoryDomainImpl(validator,commentDao);
    }
}
