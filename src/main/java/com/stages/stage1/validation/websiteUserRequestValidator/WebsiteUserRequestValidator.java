package com.stages.stage1.validation.websiteUserRequestValidator;

import com.stages.stage1.dto.websiteUser.WebsiteUserRequest;
import com.stages.stage1.exc.UserAlreadyExistsException;
import com.stages.stage1.repository.websiteUser.WebsiteUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.stages.stage1.validation.constants.ValidatorConstants.EMAIL_PATTERN;

@RequiredArgsConstructor
@Component
public class WebsiteUserRequestValidator implements Validator {

    private final WebsiteUserRepository websiteUserRepository;

    @Override
    public boolean supports(Class<?> clazz){ return WebsiteUserRequest.class.equals(clazz); }

    @Override
    public void validate(Object object, Errors errors){

        WebsiteUserRequest websiteUserRequest=(WebsiteUserRequest) object;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email", "REQUIRED","the email must not be null or empty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password", "REQUIRED","the password must not be null or empty");

        String email = websiteUserRequest.getEmail();
        if(email != null){
            if(isValidEmail(email)){
                errors.rejectValue("email", "REQUIRED", "invalid email");
            }

            if(websiteUserRepository.findByEmail(email).isPresent()) {
                errors.rejectValue("email", "ALREADY_EXISTS", "EMAIL ALREADY EXISTS");
            }
        }
    }

    private boolean isValidEmail(String email) {
        return !EMAIL_PATTERN.matcher(email).matches();
    }
}
