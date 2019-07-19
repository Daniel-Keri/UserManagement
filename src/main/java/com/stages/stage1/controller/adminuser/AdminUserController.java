package com.stages.stage1.controller.adminuser;

import com.stages.stage1.dto.adminUser.AdminUserRequest;
import com.stages.stage1.dto.adminUser.AdminUserResponse;
import com.stages.stage1.dto.adminUser.AdminUserWithRoleResponse;
import com.stages.stage1.entity.AdminUser;
import com.stages.stage1.enums.AccessRight;
import com.stages.stage1.exc.AdminUserNotFoundException;
import com.stages.stage1.service.AdminUserService;
import com.stages.stage1.validation.requestValidators.AdminUserRequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("adminUsers")
@RequiredArgsConstructor
public  class AdminUserController {

    private final AdminUserService adminUserService;
    private final AdminUserRequestValidator adminUserRequestValidator;

    @InitBinder("adminUserRequest")
    protected void initAdminUserRequestValidatorBinder(WebDataBinder binder) {
        binder.addValidators(adminUserRequestValidator);
    }

    @GetMapping
    @ResponseStatus(OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<AdminUserResponse> getAll() {
        return adminUserService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AdminUserResponse getAdminUser(@PathVariable(value = "id") UUID uuid) {
        return adminUserService.findById(uuid);
    }

    @GetMapping("findByEmail/{email}")
    public AdminUser findByEmail(String email) {

        try {
            return adminUserService.findByEmail(email);
        } catch (AdminUserNotFoundException e) {
            throw new AdminUserNotFoundException();
        }
    }


    // http://localhost:8080/adminUsers/findByName
    @GetMapping("/findByName")
    public List<AdminUserResponse> findByName(
            @RequestParam(name = "f", required = false) String firstName,
            @RequestParam(name = "m", required = false) String middleName,
            @RequestParam(name = "l", required = false) String lastName) {
        return adminUserService.findByName(firstName, middleName, lastName);
    }
    @GetMapping("/findByAccessRight")
    public  List<AdminUserWithRoleResponse> findByType(@RequestParam(name = "accessRights") AccessRight type) throws IllegalArgumentException {

        return adminUserService.findByAccessRight(type);
    }


    @PostMapping
    @ResponseStatus(OK)
    public AdminUserResponse saveAdminUser(@Validated @RequestBody AdminUserRequest adminUserRequest) {
        return adminUserService.save(adminUserRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public AdminUserResponse updateAdminUser(@PathVariable(value = "id") UUID id, @RequestBody AdminUserRequest adminUserRequest) {
        return adminUserService.update(id, adminUserRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteAdminUser(@PathVariable(value = "id") UUID id) {
        adminUserService.hardDeleteAdminUser(id);
    }
}

