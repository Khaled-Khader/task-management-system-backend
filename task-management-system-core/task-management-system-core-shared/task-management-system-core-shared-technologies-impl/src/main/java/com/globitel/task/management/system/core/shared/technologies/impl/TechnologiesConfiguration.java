package com.globitel.task.management.system.core.shared.technologies.impl;

import com.globitel.task.management.system.core.shared.technologies.TransactionExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
public class TechnologiesConfiguration {

    @Bean
    public TransactionExecutor transactionExecutor(TransactionTemplate transactionTemplate) {
        return new SpringTransactionExecutor(transactionTemplate);
    }
}
