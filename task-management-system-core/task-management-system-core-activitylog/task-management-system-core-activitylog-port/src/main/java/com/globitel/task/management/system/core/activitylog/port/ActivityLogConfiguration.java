package com.globitel.task.management.system.core.activitylog.port;

import com.globitel.task.management.system.core.activitylog.dao.ActivityLogDao;
import com.globitel.task.management.system.core.activitylog.factory.ActivityLogUseCaseFactory;
import com.globitel.task.management.system.core.activitylog.factory.domain.ActivityLogUseCaseFactoryDomain;
import com.globitel.task.management.system.core.activitylog.jpa.ActivityLogJpa;
import com.globitel.task.management.system.core.activitylog.jpa.ActivityLogRepository;
import com.globitel.task.management.system.core.activitylog.usecase.ActivityLogUseCaseFactoryDomainImpl;
import com.globitel.task.management.system.core.activitylog.usecase.ActivityLogUseCaseFactoryImpl;
import com.globitel.task.management.system.core.shared.security.context.TMSSecurityContext;
import com.globitel.task.management.system.core.user.factory.domain.UserUseCaseFactoryDomain;
import jakarta.validation.Validator;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.globitel.task.management.system.core.activitylog.entity")
@EnableJpaRepositories(basePackages = "com.globitel.task.management.system.core.activitylog.jpa")
public class ActivityLogConfiguration {

    @Bean
    public ActivityLogDao  activityLogDao(ActivityLogRepository  activityLogRepository) {
        return new ActivityLogJpa(activityLogRepository);
    }

    @Bean
    public ActivityLogUseCaseFactory activityLogUseCaseFactory(ActivityLogDao  activityLogDao, Validator validator, UserUseCaseFactoryDomain  userUseCaseFactoryDomain, TMSSecurityContext tmsSecurityContext ) {
        return new ActivityLogUseCaseFactoryImpl(validator,activityLogDao,tmsSecurityContext,userUseCaseFactoryDomain);
    }

    @Bean
    public ActivityLogUseCaseFactoryDomain activityLogUseCaseFactoryDomain(Validator validator , ActivityLogDao activityLogDao){
        return new ActivityLogUseCaseFactoryDomainImpl(validator,activityLogDao);
    }

}
