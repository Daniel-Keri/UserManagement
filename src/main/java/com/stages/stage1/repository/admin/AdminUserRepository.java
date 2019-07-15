package com.stages.stage1.repository.admin;

import com.stages.stage1.entity.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;


public interface AdminUserRepository extends JpaRepository<AdminUser, UUID> {
    AdminUser findByEmail(String email);

}
