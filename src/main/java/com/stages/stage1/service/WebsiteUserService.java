package com.stages.stage1.service;

import com.stages.stage1.entity.WebsiteUser;
import com.stages.stage1.repository.user.WebsiteUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WebsiteUserService {
    private final WebsiteUserRepository websiteUserRepository;

    public WebsiteUser getWebsiteUser(UUID uuid) {

        Optional<WebsiteUser> websiteUser = findWebsiteUser(uuid);
        if (checkIfUserIsPresent(websiteUser)) {
            return websiteUser.get();
        }
        System.out.println("user not found");
        return null;
    }

    @Transactional
    public WebsiteUser updateWebsiteUser(WebsiteUser websiteUserRequest, UUID uuid) {
        Optional<WebsiteUser> aUser = findWebsiteUser(uuid);
        if (aUser.isPresent()){
            WebsiteUser savedWebsiteUser = aUser.get();
            savedWebsiteUser.setEmail(websiteUserRequest.getEmail())
                    .setFirstName(websiteUserRequest.getFirstName())
                    .setMiddleName(websiteUserRequest.getMiddleName())
                    .setLastName(websiteUserRequest.getLastName())
                    .setPassword(websiteUserRequest.getPassword());
        }
        return aUser.get();
    }
    public WebsiteUser saveUser(WebsiteUser websiteUser) {

        return websiteUserRepository.save(websiteUser);
    }

    public void hardDeleteAdminUser(UUID uuid){
        Optional<WebsiteUser> adminUser=findWebsiteUser(uuid);
        websiteUserRepository.delete(adminUser.orElse(null));
    }

    public List<WebsiteUser> getAll(){
        return websiteUserRepository.findAll();
    }


    private boolean checkIfUserIsPresent(Optional<WebsiteUser> websiteUser) {
        return websiteUser.isPresent();
    }

    private Optional<WebsiteUser> findWebsiteUser(UUID uuid) {
        return websiteUserRepository.findById(uuid);
    }


    public WebsiteUser findbyEmailAddress(String email) {
        return websiteUserRepository.findByEmail(email);
    }
}
