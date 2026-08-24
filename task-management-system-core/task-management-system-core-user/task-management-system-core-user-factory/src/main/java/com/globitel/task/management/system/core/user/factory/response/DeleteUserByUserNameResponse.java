package com.globitel.task.management.system.core.user.factory.response;

public record DeleteUserByUserNameResponse(
        int userDeletedNumber,
        int numberOfDeletedComments,
        int numberOfDeletedTokens,
        int numberOfDeletedActivityLogs,
        int numberOfDeletedNotifications
) {
}
