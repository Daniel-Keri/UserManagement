package com.stages.stage1.dto.adminUser;

import com.stages.stage1.enums.AccessRight;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class AdminUserWithRoleResponse {

    private AccessRight accessRight;

    private String name;
}
