package com.stages.stage1.service;

import com.stages.stage1.converter.WebsiteUserConverter;
import com.stages.stage1.dto.websiteUser.WebsiteUserRequest;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WebsiteUserService {

    private final WebsiteUserRepository websiteUserRepository;
    private final WebsiteUserConverter websiteUserConverter;

    // GET
    public List<WebsiteUserResponse> findAll() {

        return websiteUserRepository.findAll().stream()
                .map(websiteUserConverter::toResponse)
                .collect(Collectors.toList());
    }

    public WebsiteUserResponse findById(UUID id) throws WebsiteUserNotFoundException {

        return websiteUserConverter.toResponse(websiteUserRepository.findById(id).orElseThrow(WebsiteUserNotFoundException::new));
    }

    public WebsiteUserResponse findByEmail(String email) throws WebsiteUserNotFoundException {

        return websiteUserConverter.toResponse(websiteUserRepository.findByEmail(email).orElseThrow(WebsiteUserNotFoundException::new));
    }

    public List<WebsiteUserResponse> findByName(String firstName, String middleName, String lastName) {

        return websiteUserRepository.findByName(firstName, middleName, lastName).stream()
                .map(websiteUserConverter::toResponse)
                .collect(Collectors.toList());
    }

    // SAVE
    public WebsiteUserResponse save(WebsiteUserRequest websiteUserRequest) {

        return websiteUserConverter.toResponse(websiteUserRepository.save(websiteUserConverter.toWebsiteUser(websiteUserRequest)));
    }

    // UPDATE
    @Transactional
    public WebsiteUserResponse update(UUID id, WebsiteUserRequest websiteUserRequest) throws WebsiteUserNotFoundException {

        return websiteUserConverter.toResponse(
            (WebsiteUser) websiteUserRepository.findById(id)
            .orElseThrow(WebsiteUserNotFoundException::new)

            .setBirthday(websiteUserRequest.getBirthday())
            .setDisplayName(websiteUserRequest.getDisplayName())
            .setGender(websiteUserRequest.getGender())
            .setPlan(websiteUserRequest.getPlan())
            .setFirstName(websiteUserRequest.getFirstName())
            .setMiddleName(websiteUserRequest.getMiddleName())
            .setLastName(websiteUserRequest.getLastName())
            .setEmail(websiteUserRequest.getEmail())
            .setPassword(websiteUserRequest.getPassword())
        );
    }

    // DELETE
    public WebsiteUserResponse softDelete(UUID id) throws WebsiteUserNotFoundException {
        return websiteUserConverter.toResponse(
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

    public void hardDelete(UUID id) throws WebsiteUserNotFoundException {

        websiteUserRepository.delete(websiteUserRepository.findById(id).orElseThrow(WebsiteUserNotFoundException::new));
    }



}
