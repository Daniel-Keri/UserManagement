package com.stages.stage1.service;

import com.stages.stage1.converter.WebsiteUserConverter;
import com.stages.stage1.dto.websiteUser.WebsiteUserResponse;
import com.stages.stage1.entity.WebsiteUser;
import com.stages.stage1.exc.WebsiteUserNotFoundException;
import com.stages.stage1.repository.user.WebsiteUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WebsiteUserService {

    private final WebsiteUserRepository websiteUserRepository;
    private final WebsiteUserConverter websiteUserConverter;

    // GET
    public List<WebsiteUserResponse> findAll(){
        List<WebsiteUserResponse> websiteUserResponses = new ArrayList<>();
        websiteUserRepository.findAll()
                .forEach(websiteUser -> websiteUserResponses.add(websiteUserConverter.toResponce(websiteUser)));
        return websiteUserResponses;
    }

    public WebsiteUserResponse findById(UUID id) {

        return websiteUserConverter.toResponce(websiteUserRepository.findById(id).orElseThrow(WebsiteUserNotFoundException::new));
    }

    public WebsiteUserResponse findByEmail(String email) {

        return websiteUserConverter.toResponce(websiteUserRepository.findByEmail(email).orElseThrow(WebsiteUserNotFoundException::new));
    }

    // SAVE
    public WebsiteUserResponse saveUser(WebsiteUser websiteUser) {

        return websiteUserConverter.toResponce(websiteUserRepository.save(websiteUser));
    }

    // UPDATE
    @Transactional
    public WebsiteUserResponse update(WebsiteUser websiteUser, UUID id) {

        return websiteUserConverter.toResponce(
            (WebsiteUser) websiteUserRepository.findById(id)
            .orElseThrow(WebsiteUserNotFoundException::new)

            .setBirthday(websiteUser.getBirthday())
            .setDisplayName(websiteUser.getDisplayName())
            .setGender(websiteUser.getGender())
            .setPlan(websiteUser.getPlan())
            .setFirstName(websiteUser.getFirstName())
            .setMiddleName(websiteUser.getMiddleName())
            .setLastName(websiteUser.getLastName())
            .setEmail(websiteUser.getEmail())
            .setPassword(websiteUser.getPassword())
        );
    }

    // DELETE
    @Transactional
    public WebsiteUserResponse softDelete(UUID id) {
        return websiteUserConverter.toResponce(
            (WebsiteUser) websiteUserRepository.findById(id)
            .orElseThrow(WebsiteUserNotFoundException::new)

            .setBirthday(null)
            .setDisplayName("--DELETED--")
            //.setGender()
            //.setPlan()
            .setFirstName("--DELETED--")
            .setMiddleName("--DELETED--")
            .setLastName("--DELETED--")
            .setEmail(UUID.randomUUID().toString() + "@random.replacement")
            .setPassword("--DELETED--")
            //.setCreationDate()
            //.setId()
        );
    }

    public void hardDelete(UUID id){

        websiteUserRepository.delete(websiteUserRepository.findById(id).orElseThrow(WebsiteUserNotFoundException::new));
    }
}
