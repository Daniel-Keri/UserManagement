package com.stages.stage1.entity;

import com.stages.stage1.enums.Type;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "admin_user")
public class AdminUser extends ParentUser {

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;
}
