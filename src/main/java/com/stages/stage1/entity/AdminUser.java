package com.stages.stage1.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "admin_user")
public class AdminUser extends ParentUser {

    @Column(name = "type")
    @Enumerated
    private Type type;
}
