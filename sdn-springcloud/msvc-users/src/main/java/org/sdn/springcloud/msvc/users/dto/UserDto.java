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
public class UserDto implements Serializable {
    private Long id;
    private String userIdentification;
    private String email;
    private String userName;
    private String password;
    private String fullUserName;
    private String userAddress;
    private boolean isEnabled;
    private UUID code;

    // Audit
    private Date createAt;
    private Date updateAt;
    private Boolean isDeleted;
}
