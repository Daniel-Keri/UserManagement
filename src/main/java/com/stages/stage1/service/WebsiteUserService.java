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

    public Optional<WebsiteUser> findWebsiteUserById(UUID uuid) {return websiteUserRepository.findById(uuid);
    }

    public WebsiteUser getWebsiteUser(UUID uuid) {
        Optional<WebsiteUser> websiteUser = findWebsiteUserById(uuid);
        if (checkIfUserIsPresent(websiteUser)) {
            return websiteUser.get();
        }
        System.out.println("user not found");
        return null;
    }

    @Transactional
    public WebsiteUser updateWebsiteUser(WebsiteUser websiteUser, UUID id) {
        Optional<WebsiteUser> user = websiteUserRepository.findById(id);
        user.ifPresent(u -> u
                .setBirthday(websiteUser.getBirthday())
                .setAddress(websiteUser.getAddress())
                .setDisplayName(websiteUser.getDisplayName())
                .setGender(websiteUser.getGender())
                .setPlan(websiteUser.getPlan())
                .setFirstName(websiteUser.getFirstName())
                .setMiddleName(websiteUser.getMiddleName())
                .setLastName(websiteUser.getLastName())
                .setEmail(websiteUser.getEmail())
                .setPassword(websiteUser.getPassword())
        );
        return user.orElse(null);
    }

    public WebsiteUser saveUser(WebsiteUser websiteUser) {return websiteUserRepository.save(websiteUser);
    }

    @Transactional
    public WebsiteUser softDelete(UUID id) {
        Optional<WebsiteUser> user = websiteUserRepository.findById(id);
        user.ifPresent(u -> {u
            .setBirthday(null)
            .setAddress("--DELETED--")
            .setDisplayName("--DELETED--")
            .setPassword("--DELETED--")
            .setFirstName("--DELETED--")
            .setMiddleName("--DELETED--")
            .setLastName("--DELETED--")
            .setEmail(UUID.randomUUID().toString() + "@random.replacement");
        });
        return user.orElse(null);
    }

    public void hardDeleteWebsiteUser(UUID uuid){
        Optional<WebsiteUser> websiteUser=findWebsiteUserById(uuid);
        websiteUserRepository.delete(websiteUser.orElse(null));
    }

    public List<WebsiteUser> getAll(){
        return websiteUserRepository.findAll();
    }

    public WebsiteUser findbyEmailAddress(String email) {return websiteUserRepository.findByEmail(email);
    }

    private boolean checkIfUserIsPresent(Optional<WebsiteUser> websiteUser) {return websiteUser.isPresent();
    }





}
