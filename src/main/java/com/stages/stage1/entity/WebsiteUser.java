package com.stages.stage1.entity;

import com.stages.stage1.entity.Gender;
import com.stages.stage1.entity.ParentUser;
import com.stages.stage1.entity.Plan;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.time.LocalDateTime;

import static javax.persistence.EnumType.STRING;

@Entity
@Table(name = "website_user")
@Data
public class WebsiteUser extends ParentUser {

    @Column(name = "birthday")
    @CreationTimestamp
    private LocalDateTime birthday;

    @Column(name = "gender")
    @Enumerated(STRING)
    private Gender gender;

    @Column(name = "address")
    private String address;

    @Column(name = "plan")
    @Enumerated(STRING)
    private Plan plan;

}
