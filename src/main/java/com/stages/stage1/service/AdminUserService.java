package com.stages.stage1.service;

import com.stages.stage1.converter.AdminUserConverter;
import com.stages.stage1.dto.adminUser.AdminUserRequest;
import com.stages.stage1.dto.adminUser.AdminUserResponse;
import com.stages.stage1.dto.adminUser.AdminUserWithTypeResponse;
import com.stages.stage1.entity.AdminUser;
import com.stages.stage1.enums.Type;
import com.stages.stage1.exc.AdminUserNotFoundException;
import com.stages.stage1.repository.admin.AdminUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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

    public List<AdminUserWithTypeResponse> findByType(Type type) {
        return adminUserRepository.findByType(type).stream()
                .map(adminUser -> new AdminUserWithTypeResponse()
                        .setType(type)
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
                .setType(adminUserRequest.getType())
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

