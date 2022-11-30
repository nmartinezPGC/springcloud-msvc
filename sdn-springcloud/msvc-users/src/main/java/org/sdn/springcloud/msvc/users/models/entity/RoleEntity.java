package org.sdn.springcloud.msvc.users.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_roles")
public class RoleEntity extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "code", columnDefinition = "uuid default gen_random_uuid()")
    private UUID code = UUID.randomUUID();

    @Column(name = "sequence", length = 15)
    private String sequence;

    @Column(name = "description", length = 150)
    @NotNull
    @NotBlank
    private String description;
}
