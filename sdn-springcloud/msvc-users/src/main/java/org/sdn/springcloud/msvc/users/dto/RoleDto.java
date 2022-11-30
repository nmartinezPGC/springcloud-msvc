package org.sdn.springcloud.msvc.users.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto implements Serializable {
    private Long id;
    private UUID code;
    private String description;

    // Audit
    private Date createAt;
    private Date updateAt;
    private Boolean isDeleted;
}
