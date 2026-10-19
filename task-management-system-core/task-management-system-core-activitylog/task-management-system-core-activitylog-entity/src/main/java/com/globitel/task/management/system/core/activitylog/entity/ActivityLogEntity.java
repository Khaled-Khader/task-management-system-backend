package com.globitel.task.management.system.core.activitylog.entity;

import com.globitel.task.management.system.core.activitylog.identity.ActivityLogIdentity;
import com.globitel.task.management.system.core.shared.entity.BaseEntity;
import com.globitel.task.management.system.core.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;



@Data
@Entity
@Table(name = "activity_logs")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ActivityLogEntity extends BaseEntity implements ActivityLogIdentity {

    @Column
    private String action;
    @Column
    private Instant timestamp;
    @ManyToOne
    @JoinColumn(name = "user_Uuid", nullable = false)
    private UserEntity user;

    public ActivityLogEntity(String uuid, Instant createdAt, String action, Instant timestamp, UserEntity user) {
        super(uuid, createdAt);
        this.action = action;
        this.timestamp = timestamp;
        this.user = user;
    }
}
