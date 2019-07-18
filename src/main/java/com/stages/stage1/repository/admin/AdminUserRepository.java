package com.stages.stage1.repository.admin;

import com.stages.stage1.entity.AdminUser;
import com.stages.stage1.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;


public interface AdminUserRepository extends JpaRepository<AdminUser, UUID> {
    AdminUser findByEmail(String email);


    @Query("SELECT au FROM AdminUser au " +
            "WHERE " +
            "(:firstName= NULL OR au.firstName=:firstName) AND " +
            "(:middleName= NULL OR au.middleName=:middleName) AND " +
            "(:lastName= NULL OR au.lastName=:lastName)")
    List<AdminUser> findByName(
            @Param(value = "firstName") String firstName,
            @Param(value = "middleName") String middleName,
            @Param(value = "lastName") String lastName);

    @Query("SELECT au FROM AdminUser au " +
            "WHERE " +
            "au.role = :role")
    List<AdminUser> findByRole(
            @Param(value = "role") Role role);
}
