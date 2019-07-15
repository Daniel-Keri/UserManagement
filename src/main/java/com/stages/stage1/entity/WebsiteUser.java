package com.stages.stage1.entity;
import lombok.Data;
import lombok.experimental.Accessors;
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
@Accessors(chain = true)
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

    @Column(name = "display_name")
    private String displayName;
}
