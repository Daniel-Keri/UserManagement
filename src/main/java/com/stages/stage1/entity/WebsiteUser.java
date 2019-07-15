package com.stages.stage1.entity;
import com.stages.stage1.enums.Gender;
import com.stages.stage1.enums.Plan;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.time.Instant;

import static javax.persistence.EnumType.STRING;

@Entity
@Table(name = "website_user")
@Data
@Accessors(chain = true)
public class WebsiteUser extends ParentUser {

    @Column(name = "birthday")
    @CreationTimestamp
    private Instant birthday;

    @Column(name = "gender")
    @Enumerated(STRING)
    private Gender gender;

    @Column(name = "plan")
    @Enumerated(STRING)
    private Plan plan;

    @Column(name = "display_name")
    private String displayName;
}
