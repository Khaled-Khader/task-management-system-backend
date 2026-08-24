package com.globitel.task.management.system.core.user.factory.response;

import com.globitel.task.management.system.core.user.entity.embedded.UserRole;
import com.globitel.task.management.system.core.user.entity.embedded.UserStatus;

public record LoginResponse(
        String token,
        String uuid,
        String name,
        String username,
        String email,
        UserStatus userStatus,
        UserRole userRole
) {
}
