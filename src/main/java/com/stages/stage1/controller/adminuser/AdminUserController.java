package com.stages.stage1.controller.adminuser;

import com.stages.stage1.dto.adminUser.AdminUserRequest;
import com.stages.stage1.dto.adminUser.AdminUserResponse;
import com.stages.stage1.entity.AdminUser;
import com.stages.stage1.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("adminUsers")
@RequiredArgsConstructor
public  class AdminUserController {

    private final AdminUserService adminUserService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AdminUserResponse getAdminUser(@PathVariable(value ="id")UUID uuid){
        return adminUserService.findById(uuid);
    }

    @PostMapping
    @ResponseStatus(OK)
    public AdminUserResponse saveAdminUser(@RequestBody AdminUserRequest adminUserRequest) {
        return adminUserService.save(adminUserRequest);
    }
    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public AdminUserResponse updateAdminUser(@PathVariable(value = "id")UUID id, @RequestBody AdminUserRequest adminUserRequest){
        return adminUserService.update(id,adminUserRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteAdminUser(@PathVariable(value = "id") UUID id)
    {
          adminUserService.hardDeleteAdminUser(id);
    }

    @GetMapping("findByEmail/{email}")
    public AdminUser findByEmail(String email){
        return adminUserService.findByEmail(email);
    }

    @GetMapping
    @ResponseStatus(OK)
    public List<AdminUserResponse> getAll(){return adminUserService.findAll();}
}
