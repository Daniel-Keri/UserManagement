package com.stages.stage1.dto.adminUser;

import com.stages.stage1.enums.Type;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class AdminUserWithTypeResponse {

    private Type type;

    private String name;
}
