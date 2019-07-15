package com.stages.stage1.service;

import com.stages.stage1.entity.AdminUser;
import com.stages.stage1.entity.WebsiteUser;
import com.stages.stage1.repository.admin.AdminUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminUserService {

    private final AdminUserRepository adminUserRepository;

    public AdminUser getAdminUser(UUID uuid) {
        Optional<AdminUser> adminUser = getById(uuid);
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
        Optional<AdminUser> aUser = getById(uuid);
        if (aUser.isPresent()){
            AdminUser savedAdminUser = aUser.get();
            savedAdminUser.setType(adminUserRequest.getType());
            savedAdminUser.setEmail(adminUserRequest.getEmail())
                    .setFirstName(adminUserRequest.getFirstName())
                    .setMiddleName(adminUserRequest.getMiddleName())
                    .setLastName(adminUserRequest.getLastName())
                    .setPassword(adminUserRequest.getPassword());
            return aUser.get();
        }
        return null;
    }

    public void hardDeleteAdminUser(UUID uuid){
        Optional<AdminUser> adminUser=getById(uuid);
        adminUserRepository.delete(adminUser.orElse(null));
    }

    public List<AdminUser> getAll(){
        return adminUserRepository.findAll();
    }
    public AdminUser findbyEmailAddress(String email){
        return adminUserRepository.findByEmail(email);
    }

    private Optional<AdminUser> getById(UUID uuid) {
        return adminUserRepository.findById(uuid);
    }

    private Optional<AdminUser> findAdminById(UUID uuid) {
        return adminUserRepository.findById(uuid);
    }

    private boolean checkIfUserIsPresent(Optional<AdminUser> adminUser) {
        return adminUser.isPresent();
    }

}

