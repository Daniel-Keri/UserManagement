package com.stages.stage1.dto.adminUser;


import com.stages.stage1.enums.Type;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.UUID;

@Data
@Accessors(chain=true)
public class AdminUserResponse {
    // <BaseEntity>
    private UUID id;

    private Instant creationDate;
    // </BaseEntity>

    // <ParentUser>
    private String firstName;

    private String middleName;

    private String lastName;

    private String email;

   //private String password;

    // </AdminUser>
    private Type type;

}
