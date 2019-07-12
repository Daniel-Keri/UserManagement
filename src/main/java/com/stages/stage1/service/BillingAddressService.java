package com.stages.stage1.service;

import com.stages.stage1.dto.request.BillingAddressRequest;
import com.stages.stage1.dto.response.BillingAddressResponse;
import com.stages.stage1.entity.BillingAddress;
import com.stages.stage1.entity.WebsiteUser;
import com.stages.stage1.repository.billingAddress.BillingAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BillingAddressService {

    private final WebsiteUserService websiteUserService;
    private final BillingAddressRepository billingAddressRepository;

    public BillingAddressResponse saveBillingAddress(BillingAddressRequest request) {
        WebsiteUser websiteUser = websiteUserService.getWebsiteUser(request.getUserId());
        return toResponse(websiteUser);
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
   private BillingAddressResponse toResponse(WebsiteUser websiteUser) {

        List<UUID> ids = new ArrayList<>();
        ids.add(websiteUser.getId());

       return (new BillingAddressResponse())
                .setUserId(websiteUser.getId())
                .setAddress(websiteUser.getAddress())
                .setInvoiceNumber(billingAddressRepository.findAllById(ids).size());
   }
}
