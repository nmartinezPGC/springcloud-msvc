package org.sdn.springcloud.msvc.users.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_users")
public class UserEntity extends AuditEntity {
    /*@Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "uuid", columnDefinition = "BINARY(16)")
    private Long Id;*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "code", columnDefinition = "uuid default gen_random_uuid()")
    private UUID code = UUID.randomUUID();

    @NotBlank
    @Column(name = "user_identification", unique = true, length = 150)
    @NotEmpty
    private String userIdentification;

    @Column(name = "full_user_name", length = 150)
    private String fullUserName;

    @Column(name = "user_address", length = 250)
    private String userAddress;

    @Column(name = "phone_number", columnDefinition = "integer default 0")
    private int phoneNumber = 0;

    @Column(name = "is_enabled")
    private Boolean isEnabled = true;

    @NotNull
    @Column(name = "email", unique = true, length = 180)
    @NotEmpty
    @Email
    private String email;

    @NotBlank
    @Column(name = "user_name", unique = true, length = 100)
    @NotEmpty
    private String userName;

    @NotNull
    @Column(name = "password", length = 200)
    @NotEmpty
    private String password;
}
