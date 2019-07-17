package com.stages.stage1.repository.user;

import com.stages.stage1.entity.WebsiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface WebsiteUserRepository extends JpaRepository<WebsiteUser, UUID> {
    Optional<WebsiteUser> findByEmail(String email);

    @Query(value = "SELECT wu FROM WebsiteUser wu " +
            "WHERE " +
            "(:firstName = NULL OR wu.firstName = :firstName) AND " +
            "(:middleName = NULL OR wu.middleName = :middleName) AND " +
            "(:lastName = NULL OR wu.lastName = :lastName)")
    List<WebsiteUser> findByName(@Param(value = "firstName") String firstName,
                                 @Param(value = "middleName") String middleName,
                                 @Param(value = "lastName") String lastName);
}
