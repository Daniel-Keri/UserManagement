package com.stages.stage1.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Entity
@Table(name = "admin_user")
@Accessors(chain = true)
public class AdminUser extends ParentUser {


}
