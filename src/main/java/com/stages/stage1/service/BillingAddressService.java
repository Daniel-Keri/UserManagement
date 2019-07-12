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
        WebsiteUser user = websiteUserService.getWebsiteUser(request.getUserId());
        BillingAddress ba = toBillingAddress(user, request);
        billingAddressRepository.save(ba);

        //BillingAddress ba = billingAddressRepository.findById(request.getUserId()).orElse(null);
        return findInvoice(ba.getId());
    }

    public List<BillingAddressResponse> findAllBillingAddress(UUID id) {
        List<UUID> ids = new ArrayList<>();
        ids.add(id);

        List<BillingAddress> baList = billingAddressRepository.findAllById(ids);
        List<BillingAddressResponse> barList = new ArrayList<>();

        billingAddressRepository.findAllById(ids).forEach(ba ->
        {
            Integer i = ba.getInvoiceNumber();
            BillingAddressResponse bar = toResponse(ba.getWebsiteUser());
            bar.setInvoiceNumber(i);
            barList.add(bar);
        });

        return barList;
    }

    public BillingAddressResponse findInvoice(UUID id) {
        BillingAddress ba = billingAddressRepository.findById(id).orElse(null);

        return new BillingAddressResponse()
                .setInvoiceNumber(ba.getInvoiceNumber())
                .setUserId(ba.getWebsiteUser().getId())
                .setAddress(ba.getAddress());
    }



    //
    private BillingAddress toBillingAddress(WebsiteUser user, BillingAddressRequest request) {

        return (BillingAddress)new BillingAddress()
                .setAddress(request.getAddress())
                .setInvoiceNumber(request.getInvoiceNumber())
                .setWebsiteUser(user)
                .setId(request.getUserId())
                .setCreationDate(user.getCreationDate());
    }

    private BillingAddressResponse toResponse(BillingAddress billingAddress) {
/*
        List<UUID> ids = new ArrayList<>();
        ids.add(billingAddress.getId());
*/
        return (new BillingAddressResponse())
                .setAddress(billingAddress.getAddress())
                .setUserId(billingAddress.getId())
                .setInvoiceNumber(billingAddress.getInvoiceNumber());
    }

    private BillingAddressResponse toResponse(WebsiteUser user) {

        List<UUID> ids = new ArrayList<>();
        ids.add(user.getId());

        return new BillingAddressResponse()


                .setAddress(user.getAddress())
                .setUserId(user.getId())
                .setInvoiceNumber(billingAddressRepository.findAllById(ids).size());
    }
}
