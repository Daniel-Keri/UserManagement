package com.stages.stage1.controller.websiteuser;

import com.stages.stage1.entity.WebsiteUser;
import com.stages.stage1.service.WebsiteUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("websiteUsers")
@RequiredArgsConstructor
public class WebsiteUserController {

    private final WebsiteUserService websiteUserService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public WebsiteUser getUser(@PathVariable(value = "id")UUID uuid){
        return websiteUserService.getWebsiteUser(uuid);
    }

    @PostMapping
    @ResponseStatus(OK)
    public WebsiteUser saveUser(@RequestBody WebsiteUser websiteUser) {
        return websiteUserService.saveUser(websiteUser);
    }
    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public WebsiteUser updateWebsiteUser(@PathVariable(value = "id")UUID id, @RequestBody WebsiteUser websiteUser){
        return websiteUserService.updateWebsiteUser(websiteUser,id);
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    public WebsiteUser softDelete(@PathVariable("id") UUID id) {
        return websiteUserService.softDelete(id);
    }

    @DeleteMapping
    public void deleteWebsiteUser(@PathVariable(value = "id") UUID id) {
        websiteUserService.hardDeleteWebsiteUser(id);
    }

    @GetMapping("findByEmail/{email}")
    public WebsiteUser findByEmail(String email){
        return websiteUserService.findByEmailAddress(email);
    }

    @GetMapping
    @ResponseStatus(OK)
    public List<WebsiteUser> getAll(){
       return websiteUserService.getAll();
    }
}
