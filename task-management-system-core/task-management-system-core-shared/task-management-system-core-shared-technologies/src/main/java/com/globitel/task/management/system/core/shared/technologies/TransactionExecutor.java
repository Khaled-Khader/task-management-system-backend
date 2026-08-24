package com.globitel.task.management.system.core.shared.technologies;

import java.util.function.Supplier;

public interface TransactionExecutor {
    <T> T executeWithResult(Supplier<T> action);

    void execute(Runnable action);
}
