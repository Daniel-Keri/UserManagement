package com.stages.stage1.dto.converter;

import com.stages.stage1.dto.request.BillingAddressRequest;
import com.stages.stage1.dto.response.BillingAddressResponse;
import com.stages.stage1.entity.BillingAddress;
import com.stages.stage1.entity.WebsiteUser;
import com.stages.stage1.repository.billingAddress.BillingAddressRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class BillingAddressConverter {

    private final BillingAddressRepository billingAddressRepository;

    public BillingAddress toBillingAddress(WebsiteUser websiteUser, BillingAddressRequest request) {
        BillingAddress billingAddress = (BillingAddress) new BillingAddress()
                .setAddress(request.getAddress())
                .setInvoiceNumber(request.getInvoiceNumber())
                .setWebsiteUser(websiteUser)
                .setId(request.getUserId())
                .setCreationDate(websiteUser.getCreationDate());

        return billingAddress;
    }

    public BillingAddressResponse toResponse(WebsiteUser websiteUser) {
        List<UUID> ids = new ArrayList<>();
        ids.add(websiteUser.getId());

        BillingAddressResponse response = new BillingAddressResponse()
        .setAddress(websiteUser.getAddress())
        .setUserId(websiteUser.getId())
        .setInvoiceNumber(billingAddressRepository.findAllById(ids).size());

        return response;
    }
}