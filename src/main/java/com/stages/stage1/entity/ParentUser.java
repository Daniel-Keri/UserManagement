package com.stages.stage1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

@MappedSuperclass
@Data
@Accessors(chain = true)
public abstract class ParentUser extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "password")
    private String password;


    public ParentUser setPassword(String password){
        this.password = new BCryptPasswordEncoder().encode(password);
        return this;
    }
    //replace to the service

}
