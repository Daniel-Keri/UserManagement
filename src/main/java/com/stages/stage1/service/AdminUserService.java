package com.stages.stage1.service;

import com.stages.stage1.converter.AdminUserConverter;
import com.stages.stage1.dto.adminUser.AdminUserResponse;
import com.stages.stage1.entity.AdminUser;
import com.stages.stage1.repository.admin.AdminUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminUserService {


    private final AdminUserRepository adminUserRepository;
    private final AdminUserConverter adminUserConverter;

    public List<AdminUserResponse> findAll(){
        List<AdminUserResponse> adminUserResponses = new ArrayList<>();
        adminUserRepository.findAll()
                .forEach(adminUser -> adminUserResponses.add(adminUserConverter.toResponse(adminUser)));
        return adminUserResponses;
    }
    public Optional<AdminUser> findById(UUID uuid) {
        return adminUserRepository.findById(uuid);
    }

    public AdminUser getAdminUser(UUID uuid) {
        Optional<AdminUser> adminUser = findById(uuid);
        if (checkIfUserIsPresent(adminUser)) {
            return adminUser.get();
        }
        System.out.println("Admin user is not exist");
        return null;
    }

    public AdminUser saveAdminUser(AdminUser adminUser) {
        return adminUserRepository.save(adminUser);
    }

    @Transactional
    public AdminUser updateAdminUser(AdminUser adminUserRequest, UUID uuid) {
        Optional<AdminUser> adminUser = findById(uuid);
        if (adminUser.isPresent()){
            AdminUser savedAdminUser = adminUser.get();
            savedAdminUser.setType(adminUserRequest.getType());
            savedAdminUser.setEmail(adminUserRequest.getEmail())
                    .setFirstName(adminUserRequest.getFirstName())
                    .setMiddleName(adminUserRequest.getMiddleName())
                    .setLastName(adminUserRequest.getLastName())
                    .setPassword(adminUserRequest.getPassword());
            return adminUser.get();
        }
        return null;
    }

    public void hardDeleteAdminUser(UUID uuid){
        Optional<AdminUser> adminUser= findById(uuid);
        adminUserRepository.delete(adminUser.orElse(null));//!!!!!!!
    }



    public AdminUser findByEmailAddress(String email){
        return adminUserRepository.findByEmail(email);
    }



    private boolean checkIfUserIsPresent(Optional<AdminUser> adminUser) {
        return adminUser.isPresent();
    }


}

