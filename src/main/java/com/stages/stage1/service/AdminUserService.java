package com.stages.stage1.service;

import com.stages.stage1.converter.AdminUserConverter;
import com.stages.stage1.dto.adminUser.AdminUserRequest;
import com.stages.stage1.dto.adminUser.AdminUserResponse;
import com.stages.stage1.dto.adminUser.AdminUserWithRoleResponse;
import com.stages.stage1.entity.AdminUser;
import com.stages.stage1.enums.AccessRight;
import com.stages.stage1.exc.AdminUserNotFoundException;
import com.stages.stage1.repository.adminUser.AdminUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminUserService {

    private final AdminUserRepository adminUserRepository;
    private final AdminUserConverter adminUserConverter;

    //GET
    public List<AdminUserResponse> findAll(){

        return adminUserRepository.findAll().stream()
                .map(adminUserConverter::toResponse)
                .collect(Collectors.toList());
    }
    public AdminUserResponse findById(UUID uuid) {
        return adminUserConverter.toResponse(adminUserRepository.findById(uuid).orElseThrow(AdminUserNotFoundException::new));
    }
    public List<AdminUserResponse> findByName(String firstName, String middleName, String lastName) {

        return adminUserRepository.findByName(firstName, middleName, lastName).stream()
                .map(adminUserConverter::toResponse)
                .collect(Collectors.toList());
    }

    public List<AdminUserWithRoleResponse> findByAccessRight(AccessRight accessRight) {
        return adminUserRepository.findByAccessRight(accessRight).stream()
                .map(adminUser -> new AdminUserWithRoleResponse()
                        .setAccessRight(accessRight)
                        .setName(adminUser.getFirstName()+" "+adminUser.getLastName()))
                .collect(Collectors.toList());
    }

    public AdminUser findByEmail(String email){
        return adminUserRepository.findByEmail(email);
    }
    //SAVE
    
    public AdminUserResponse save(AdminUserRequest adminUserRequest)  {

        return adminUserConverter.toResponse(adminUserRepository.save(adminUserConverter.toAdminUser(adminUserRequest)));
    }
    //UPDATE

    @Transactional
    public AdminUserResponse update(UUID id, AdminUserRequest adminUserRequest) throws AdminUserNotFoundException {
        return adminUserConverter.toResponse(
                (AdminUser) adminUserRepository.findById(id)
                        .orElseThrow(AdminUserNotFoundException::new)
                .setAccessRight(adminUserRequest.getAccessRight())
                .setMiddleName(adminUserRequest.getMiddleName())
                .setPassword(adminUserRequest.getPassword())
                .setLastName(adminUserRequest.getLastName())
                .setFirstName(adminUserRequest.getFirstName())
                .setEmail(adminUserRequest.getEmail()));
    }
    public void hardDeleteAdminUser(UUID uuid) throws AdminUserNotFoundException{
        adminUserRepository.delete(adminUserRepository.findById(uuid).orElseThrow(AdminUserNotFoundException::new));
    }
}

