package com.stages.stage1.dto.adminUser;

import com.stages.stage1.enums.Role;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class AdminUserWithRoleResponse {

    private Role role;

    private String name;
}
