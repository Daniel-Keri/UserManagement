package com.stages.stage1.service;

import com.stages.stage1.dto.request.BillingAddressRequest;
import com.stages.stage1.dto.response.BillingAddressResponse;
import com.stages.stage1.entity.BillingAddress;
import com.stages.stage1.entity.WebsiteUser;
import com.stages.stage1.repository.billingAddress.BillingAddressRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BillingAddressService {

    private final WebsiteUserService websiteUserService;
    private final BillingAddressRepository billingAddressRepository;

    //private final BillingAddressEntityConverter billingAddressEntityConverter;
    public BillingAddressResponse saveBillingAddress(BillingAddressRequest request) {
        WebsiteUser user = websiteUserService.getById(request.getUserId()).orElseThrow(IllegalArgumentException::new);
        BillingAddress ba = toBillingAddress(user, request.setInvoiceNumber(findAllBillingAddressByUserId(user.getId()).size()));
        billingAddressRepository.save(ba);

        return findByInvoiceId(ba.getId());
    }

    public List<BillingAddressResponse> findAllBillingAddressByUserId(UUID user_id) {
        List<BillingAddressResponse> barList = new ArrayList<>();
        billingAddressRepository.findAll().forEach( ba -> { if (ba.getWebsiteUser().getId().equals(user_id)) barList.add(toResponse(ba)); } );

        return barList;
    }

    public BillingAddressResponse findByInvoiceId(UUID id) {
        BillingAddress ba = billingAddressRepository.findById(id).orElse(null);

        return toResponse(ba);
    }



    //
    private BillingAddress toBillingAddress(WebsiteUser user, BillingAddressRequest request) {

        return (BillingAddress)new BillingAddress()
                .setAddress(request.getAddress())
                .setInvoiceNumber(request.getInvoiceNumber())
                .setWebsiteUser(user)
                .setCreationDate(user.getCreationDate());
    }

    private BillingAddressResponse toResponse(BillingAddress billingAddress) {

        return (new BillingAddressResponse())
                .setAddress(billingAddress.getAddress())
                .setUserId(billingAddress.getWebsiteUser().getId())
                .setInvoiceNumber(billingAddress.getInvoiceNumber());
    }

}
