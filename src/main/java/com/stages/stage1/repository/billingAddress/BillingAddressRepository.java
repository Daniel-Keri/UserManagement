package com.stages.stage1.repository.billingAddress;

import com.stages.stage1.entity.BillingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface BillingAddressRepository extends JpaRepository<BillingAddress, UUID> {

    @Query(value = "SELECT ba FROM BillingAddress ba " +
            "WHERE " +
            "ba.websiteUser.id = :id")
    public List<BillingAddress> findAllByWebsiteUserId(@Param(value = "id") UUID id);
}
