package com.globitel.task.management.system.core.comment.factory.response;

import com.globitel.task.management.system.core.user.entity.embedded.UserRole;

public record CommentResponse(
        String commentUuid,
        String name,
        String content,
        UserRole userRole,
        String taskUuid,
        String parentCommentUuid
) {
}
