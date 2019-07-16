package com.stages.stage1.service;

import com.stages.stage1.converter.BillingAddressConverter;
import com.stages.stage1.dto.billingAddress.BillingAddressRequest;
import com.stages.stage1.dto.billingAddress.BillingAddressResponse;
import com.stages.stage1.entity.BillingAddress;
import com.stages.stage1.entity.WebsiteUser;
import com.stages.stage1.exc.WebsiteUserNotFoundException;
import com.stages.stage1.repository.billingAddress.BillingAddressRepository;
import com.stages.stage1.repository.user.WebsiteUserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BillingAddressService {

    private final WebsiteUserRepository websiteUserRepository;
    private final BillingAddressRepository billingAddressRepository;
    private final BillingAddressConverter billingAddressConverter;


    public BillingAddressResponse saveBillingAddress(BillingAddressRequest request) {
        WebsiteUser user = websiteUserRepository.findById(request.getUserId()).orElseThrow(WebsiteUserNotFoundException::new);
        BillingAddress ba = billingAddressConverter.toBillingAddress(user, request, findAllBillingAddressByUserId(user.getId()));
        billingAddressRepository.save(ba);

        return findByInvoiceId(ba.getId());
    }

    public List<BillingAddressResponse> findAllBillingAddressByUserId(UUID user_id) {
        List<BillingAddressResponse> barList = new ArrayList<>();
        billingAddressRepository.findAll()
                .forEach( ba ->
                { if(ba.getWebsiteUser().getId().equals(user_id)){}

                    barList.add(billingAddressConverter.toResponse(ba)); } );

        return barList;
    }

    public BillingAddressResponse findByInvoiceId(UUID id) {
        BillingAddress billingAddress = billingAddressRepository.findById(id).orElse(null);

        return billingAddressConverter.toResponse(billingAddress);
    }
}
