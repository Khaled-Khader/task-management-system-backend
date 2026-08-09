package com.globitel.task.management.system.core.notification.port;

import com.globitel.task.management.system.core.notification.dao.NotificationDao;
import com.globitel.task.management.system.core.notification.factory.NotificationUseCaseFactory;
import com.globitel.task.management.system.core.notification.factory.domain.NotificationUseCaseFactoryDomain;
import com.globitel.task.management.system.core.notification.jpa.NotificationJpa;
import com.globitel.task.management.system.core.notification.jpa.NotificationRepository;
import com.globitel.task.management.system.core.notification.usecase.NotificationUseCaseFactoryDomainImpl;
import com.globitel.task.management.system.core.notification.usecase.NotificationUseCaseFactoryImpl;
import com.globitel.task.management.system.core.shared.security.context.TMSSecurityContext;
import com.globitel.task.management.system.core.user.factory.domain.UserUseCaseFactoryDomain;
import jakarta.validation.Validator;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.globitel.task.management.system.core.notification.entity")
@EnableJpaRepositories(basePackages = "com.globitel.task.management.system.core.notification.jpa")
public class NotificationConfiguration {

    @Bean
    public NotificationDao notificationDao(NotificationRepository notificationRepository) {
        return new NotificationJpa(notificationRepository);
    }

    @Bean
    public NotificationUseCaseFactory notificationUseCaseFactory(Validator validator,NotificationDao notificationDao, TMSSecurityContext tmsSecurityContext, UserUseCaseFactoryDomain userUseCaseFactoryDomain) {
        return new NotificationUseCaseFactoryImpl(notificationDao,validator, tmsSecurityContext, userUseCaseFactoryDomain);
    }

    @Bean
    public NotificationUseCaseFactoryDomain notificationUseCaseFactoryDomain(Validator validator, NotificationDao notificationDao) {
        return new NotificationUseCaseFactoryDomainImpl(validator, notificationDao);

    }


}
