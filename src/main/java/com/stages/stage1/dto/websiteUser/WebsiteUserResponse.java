package com.stages.stage1.dto.websiteUser;

import com.stages.stage1.enums.Gender;
import com.stages.stage1.enums.Plan;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.UUID;

@Data
@Accessors(chain=true)
public class WebsiteUserResponse {

    // <BaseEntity>
    private UUID id;

    private Instant creationDate;
    // </BaseEntity>

    // <ParentUser>
    private String firstName;

    private String middleName;

    private String lastName;

    private String email;

    //private String password;
    // </ParentUser>

    // <WebsiteUser>
    private Instant birthday;

    private Gender gender;

    private Plan plan;

    private String displayName;
    // </WebsiteUser>
}
