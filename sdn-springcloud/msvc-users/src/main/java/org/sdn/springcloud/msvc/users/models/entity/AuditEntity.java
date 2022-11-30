package org.sdn.springcloud.msvc.users.models.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Data
@MappedSuperclass
public abstract class AuditEntity {
    // Audit field
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private Date createAt;

    @LastModifiedDate
    @Column(name = "update_at")
    private Date updateAt;

    @Column(name = "is_deleted", columnDefinition = "boolean default false")
    private Boolean isDeleted = false;

    // Persist data
    @PrePersist
    protected void prePersist(){
        this.createAt = new Date();
    }

    @PreUpdate
    protected void preUpdate(){
        this.updateAt = new Date();
    }

    @PreRemove
    protected void preRemove(){
        this.isDeleted = true;
    }
}
