package com.stages.stage1.controller.adminuser;

import com.stages.stage1.entity.AdminUser;
import com.stages.stage1.repository.admin.AdminUserRepository;
import com.stages.stage1.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("adminUsers")
@RequiredArgsConstructor
public  class AdminUserController {

    private final AdminUserService adminUserService;

    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public AdminUser getAdminUser(@PathVariable(value ="id")UUID uuid){
        return adminUserService.getAdminUser(uuid);
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(OK)
    public AdminUser saveAdminUser(@RequestBody AdminUser adminUser) {
        return adminUserService.saveAdminUser(adminUser);
    }
    @PutMapping("/{id}")
    @ResponseBody
    @ResponseStatus(OK)
    public AdminUser updateAdminUser(@PathVariable(value = "id")UUID id, @RequestBody AdminUser adminUser){
        return adminUserService.updateAdminUser(adminUser,id);
    }

    @DeleteMapping("/{id}")
    public void deleteAdminUser(@PathVariable(value = "id") UUID id)
    {
          adminUserService.hardDeleteAdminUser(id);
    }

    @GetMapping("findByEmail/{email}")
    public AdminUser findByEmail(String email){
        return adminUserService.findbyEmailAddress(email);
    }

}
