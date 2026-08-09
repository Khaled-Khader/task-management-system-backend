package com.globitel.task.management.system.core.activitylog.factory.response;

import java.time.Instant;

public record ActivityLogResponse(
        String uuid,
        String action,
        Instant createdAt,
        String userName
) {
}
