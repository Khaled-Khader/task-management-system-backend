package com.globitel.task.management.system.core.shared.technologies.impl;

import com.globitel.task.management.system.core.shared.technologies.TransactionExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class SpringTransactionExecutor implements TransactionExecutor {
    private final TransactionTemplate transactionTemplate;

    @Override
    public <T> T executeWithResult(Supplier<T> action) {
        return transactionTemplate.execute(status -> action.get());
    }

    @Override
    public void execute(Runnable action) {
        transactionTemplate.executeWithoutResult(status -> action.run());
    }
}
