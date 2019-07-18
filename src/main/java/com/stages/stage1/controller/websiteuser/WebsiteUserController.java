package com.stages.stage1.controller.websiteuser;

import com.stages.stage1.dto.websiteUser.WebsiteUserRequest;
import com.stages.stage1.dto.websiteUser.WebsiteUserResponse;

import com.stages.stage1.exc.WebsiteUserNotFoundException;
import com.stages.stage1.service.WebsiteUserService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;



@RestController
@RequestMapping("websiteUsers")
@RequiredArgsConstructor
public class WebsiteUserController {

    private final WebsiteUserService websiteUserService;

    // GET
    // http://localhost:8080/websiteUsers
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<WebsiteUserResponse> findAll() {
        return websiteUserService.findAll();
    }

    // http://localhost:8080/websiteUsers/{id}
    @GetMapping("/{id}")
    public WebsiteUserResponse findById(@PathVariable(value = "id") UUID id) throws WebsiteUserNotFoundException {
        return websiteUserService.findById(id);
    }


    // http://localhost:8080/websiteUsers/findByEmail/{email}
    @GetMapping("/findByEmail/{email}")
    public WebsiteUserResponse findByEmail(@PathVariable(value = "email") String email) throws WebsiteUserNotFoundException {
        return websiteUserService.findByEmail(email);
    }

    // http://localhost:8080/websiteUsers/findByName
    @GetMapping("/findByName")
    public List<WebsiteUserResponse> findByName(
            @RequestParam(name = "f", required = false) String firstName,
            @RequestParam(name = "m", required = false) String middleName,
            @RequestParam(name = "l", required = false) String lastName ) {
        return websiteUserService.findByName(firstName, middleName, lastName);
    }

    // SAVE
    @PostMapping
    public WebsiteUserResponse save(@RequestBody WebsiteUserRequest websiteUserRequest) {
        return websiteUserService.save(websiteUserRequest);
    }

    // UPDATE
    // http://localhost:8080/websiteUsers/{id}
    @PutMapping("/{id}")
    public WebsiteUserResponse update(@PathVariable(value = "id") UUID id, @RequestBody WebsiteUserRequest websiteUserRequest) throws WebsiteUserNotFoundException {
        return websiteUserService.update(id, websiteUserRequest);
    }

    // DELETE
    // http://localhost:8080/websiteUsers/{id}
    @ResponseBody
    @DeleteMapping("/{id}")
    public WebsiteUserResponse softDelete(@PathVariable("id") UUID id) throws WebsiteUserNotFoundException {
        return websiteUserService.softDelete(id);
    }

    // http://localhost:8080/websiteUsers/{id}
    @DeleteMapping("/delete/{id}")
    public void hardDelete(@PathVariable(value = "id") UUID id) throws WebsiteUserNotFoundException {
        websiteUserService.hardDelete(id);
    }



}
