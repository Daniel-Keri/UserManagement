package com.stages.stage1.converter;

import com.stages.stage1.dto.adminUser.AdminUserRequest;
import com.stages.stage1.dto.adminUser.AdminUserResponse;
import com.stages.stage1.entity.AdminUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminUserConverter {

    public AdminUser toAdminUser(AdminUserRequest adminUserRequest)
    {
        return (AdminUser) new AdminUser()
                .setType(adminUserRequest.getType())
                .setFirstName(adminUserRequest.getFirstName())
                .setMiddleName(adminUserRequest.getMiddleName())
                .setLastName(adminUserRequest.getLastName())
                .setEmail(adminUserRequest.getEmail())
                .setPassword(adminUserRequest.getPassword())
                .setId(adminUserRequest.getId())
                .setCreationDate(adminUserRequest.getCreationDate())
                .setId(adminUserRequest.getId());
    }
    public AdminUserResponse toResponse(AdminUser adminUser)
    {
        return new AdminUserResponse()
                .setType(adminUser.getType())
                .setFirstName(adminUser.getFirstName())
                .setMiddleName(adminUser.getMiddleName())
                .setLastName(adminUser.getLastName())
                .setEmail(adminUser.getEmail())
                //.setPassword(adminUser.getPassword())
                .setId(adminUser.getId())
                .setCreationDate(adminUser.getCreationDate())
                .setId(adminUser.getId());
        //password
    }
}
