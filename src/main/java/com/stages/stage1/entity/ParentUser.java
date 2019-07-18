package com.stages.stage1.entity;

import com.stages.stage1.enums.AccessRight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Data
@DiscriminatorColumn(name = "user_type")
public abstract class ParentUser extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "accessRight")
    @Enumerated(EnumType.STRING)
    private AccessRight accessRight;

}
