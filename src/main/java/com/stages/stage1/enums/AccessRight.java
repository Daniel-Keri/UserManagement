package com.stages.stage1.enums;

import org.springframework.security.core.GrantedAuthority;

public enum AccessRight implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN,
    ROLE_SUPER_ADMIN;


    @Override
    public String getAuthority() {
        return this.toString();
    }
}
