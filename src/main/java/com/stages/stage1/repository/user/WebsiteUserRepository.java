package com.stages.stage1.repository.user;

import com.stages.stage1.entity.WebsiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface WebsiteUserRepository extends JpaRepository<WebsiteUser, UUID> {
    Optional<WebsiteUser> findByEmail(String email);
}
