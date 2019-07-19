package com.stages.stage1.validation.requestValidators;

import com.stages.stage1.dto.adminUser.AdminUserRequest;
import com.stages.stage1.dto.websiteUser.WebsiteUserRequest;
import com.stages.stage1.repository.adminUser.AdminUserRepository;
import com.stages.stage1.repository.websiteUser.WebsiteUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.stages.stage1.validation.constants.ValidatorConstants.EMAIL_PATTERN;

@RequiredArgsConstructor
@Component
public class AdminUserRequestValidator implements Validator {

    private final AdminUserRepository adminUserRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return AdminUserRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {

        AdminUserRequest adminUserRequest = (AdminUserRequest) object;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "REQUIRED", "the email must not be null or empty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "REQUIRED", "the password must not be null or empty");

        String email = adminUserRequest.getEmail();

        if (email != null) {
            if (!isValidEmail(email)) {
                errors.rejectValue("email", "REQUIRED", "invalid email");
            }

            if (adminUserRepository.findByEmail(email).isPresent()) {
                errors.rejectValue("email", "ALREADY_EXISTS", "EMAIL ALREADY EXISTS");
            }
        }
    }

    private boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
}
