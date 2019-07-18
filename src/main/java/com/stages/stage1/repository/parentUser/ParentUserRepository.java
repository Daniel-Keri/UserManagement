package com.stages.stage1.repository.parentUser;

import com.stages.stage1.entity.ParentUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface ParentUserRepository extends JpaRepository<ParentUser, UUID> {

    @Query("SELECT pu FROM com.stages.stage1.entity.ParentUser pu WHERE pu.email = ?1")
    Optional<ParentUser> findByEmail(String email);
}
