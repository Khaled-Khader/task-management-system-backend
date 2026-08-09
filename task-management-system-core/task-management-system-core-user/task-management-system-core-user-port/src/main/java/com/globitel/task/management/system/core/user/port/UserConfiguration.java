package com.globitel.task.management.system.core.user.port;

import com.globitel.task.management.system.core.notification.factory.domain.NotificationUseCaseFactoryDomain;
import com.globitel.task.management.system.core.shared.security.context.TMSSecurityContext;
import com.globitel.task.management.system.core.shared.socket.TMSWebSocket;
import com.globitel.task.management.system.core.token.factory.domain.TokenUseCaseFactoryDomain;
import com.globitel.task.management.system.core.user.dao.UserDao;
import com.globitel.task.management.system.core.user.factory.UserUseCaseFactory;
import com.globitel.task.management.system.core.user.factory.domain.UserUseCaseFactoryDomain;
import com.globitel.task.management.system.core.user.factory.security.UserUseCaseSecurityFactory;
import com.globitel.task.management.system.core.user.factory.socket.UserUseCaseSocketFactory;
import com.globitel.task.management.system.core.user.function.TMSAuthenticator;
import com.globitel.task.management.system.core.user.function.TMSPasswordEncoder;
import com.globitel.task.management.system.core.user.function.impl.PasswordEncoderImpl;
import com.globitel.task.management.system.core.user.function.impl.TMSAuthenticatorImpl;
import com.globitel.task.management.system.core.user.jpa.UserJpa;
import com.globitel.task.management.system.core.user.jpa.UserRepository;
import com.globitel.task.management.system.core.user.usecase.UserUseCaseFactoryDomainImpl;
import com.globitel.task.management.system.core.user.usecase.UserUseCaseFactoryImpl;
import com.globitel.task.management.system.core.user.usecase.UserUseCaseSecurityFactoryImpl;
import com.globitel.task.management.system.core.user.usecase.UserUseCaseSocketFactoryImpl;
import jakarta.validation.Validator;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EntityScan(basePackages = "com.globitel.task.management.system.core.user.entity")
@EnableJpaRepositories(basePackages = "com.globitel.task.management.system.core.user.jpa")
public class UserConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDao  userDao(UserRepository userRepository) {
        return new UserJpa(userRepository);
    }

    @Bean
    public TMSPasswordEncoder tmsPasswordEncoder(PasswordEncoder passwordEncoder) {
        return new PasswordEncoderImpl(passwordEncoder);
    }

    @Bean
    public UserUseCaseFactory  userUseCaseFactory(UserDao userDao, Validator validator, TMSPasswordEncoder tmsPasswordEncoder, TMSSecurityContext tMSSecurityContext, TMSWebSocket tmsWebSocket, NotificationUseCaseFactoryDomain notificationUseCaseFactoryDomain) {
        return new UserUseCaseFactoryImpl(validator,userDao,tmsPasswordEncoder,tMSSecurityContext,tmsWebSocket,notificationUseCaseFactoryDomain);
    }

    @Bean
    public UserUseCaseFactoryDomain userUseCaseFactoryDomain(UserDao userDao,Validator validator) {
        return new UserUseCaseFactoryDomainImpl(validator,userDao);
    }

    @Bean
    public TMSAuthenticator tmsAuthenticator(AuthenticationManager authenticationManager) {
        return new TMSAuthenticatorImpl(authenticationManager);

    }

    @Bean
    public UserUseCaseSecurityFactory userUseCaseSecurityFactory(Validator validator, TMSAuthenticator tmsAuthenticator,UserDao userDao, TokenUseCaseFactoryDomain tokenUseCaseFactoryDomain) {
        return new UserUseCaseSecurityFactoryImpl(validator, tmsAuthenticator, userDao, tokenUseCaseFactoryDomain);
    }

    @Bean
    public UserUseCaseSocketFactory userUseCaseSocketFactory(UserDao userDao,Validator validator ) {
        return new UserUseCaseSocketFactoryImpl(validator,userDao);
    }
}
