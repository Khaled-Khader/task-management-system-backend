package com.globitel.task.management.system.core.task.factory.response;

public record DeleteTaskResponse(
        int deletedTasksNumber,
        int deletedComments
) {
}
