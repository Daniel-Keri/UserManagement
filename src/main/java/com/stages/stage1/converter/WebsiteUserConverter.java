package com.stages.stage1.converter;

import com.stages.stage1.dto.websiteUser.WebsiteUserRequest;
import com.stages.stage1.dto.websiteUser.WebsiteUserResponse;
import com.stages.stage1.entity.WebsiteUser;
import com.stages.stage1.enums.AccessRight;
import com.stages.stage1.service.PasswordEncoderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.stages.stage1.enums.AccessRight.ROLE_USER;

@Service
@RequiredArgsConstructor
public class WebsiteUserConverter {

    private final PasswordEncoderService passwordEncoderService;

    public WebsiteUser toWebsiteUser(WebsiteUserRequest websiteUserRequest) {

        return (WebsiteUser)new WebsiteUser()
                .setDisplayName(websiteUserRequest.getDisplayName())
                .setBirthday(websiteUserRequest.getBirthday())
                .setGender(websiteUserRequest.getGender())
                .setPlan(websiteUserRequest.getPlan())
                .setFirstName(websiteUserRequest.getFirstName())
                .setMiddleName(websiteUserRequest.getMiddleName())
                .setLastName(websiteUserRequest.getLastName())
                .setEmail(websiteUserRequest.getEmail())
                .setPassword(passwordEncoderService.encode(websiteUserRequest.getPassword()))
                .setAccessRight(ROLE_USER)
                .setId(websiteUserRequest.getId())
                .setCreationDate(websiteUserRequest.getCreationDate());
    }

    public WebsiteUserResponse toResponse(WebsiteUser websiteUser) {

        return new WebsiteUserResponse()
                .setDisplayName(websiteUser.getDisplayName())
                .setBirthday(websiteUser.getBirthday())
                .setGender(websiteUser.getGender())
                .setPlan(websiteUser.getPlan())
                .setFirstName(websiteUser.getFirstName())
                .setMiddleName(websiteUser.getMiddleName())
                .setLastName(websiteUser.getLastName())
                .setEmail(websiteUser.getEmail())
                //.setPassword(websiteUser.getPassword())
                .setId(websiteUser.getId())
                .setCreationDate(websiteUser.getCreationDate());
    }

}
