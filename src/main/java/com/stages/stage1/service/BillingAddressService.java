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

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BillingAddressService {

    private final WebsiteUserRepository websiteUserRepository;
    private final BillingAddressRepository billingAddressRepository;
    private final BillingAddressConverter billingAddressConverter;


    public BillingAddressResponse saveBillingAddress(BillingAddressRequest request) throws WebsiteUserNotFoundException {
        WebsiteUser websiteUser = websiteUserRepository.findById(request.getUserId()).orElseThrow(WebsiteUserNotFoundException::new);
        BillingAddress billingAddress = billingAddressConverter.toBillingAddress(websiteUser, request, findAllBillingAddressByUserId(websiteUser.getId()));
        billingAddressRepository.save(billingAddress);

        return findByInvoiceId(billingAddress.getId());
    }

    public List<BillingAddressResponse> findAllBillingAddressByUserId(UUID user_id) {

        return billingAddressRepository.findAllByWebsiteUserId(user_id)/*.findAll()*/.stream()
                //.filter(billingAddress -> billingAddress.getWebsiteUser().getId().equals(user_id))
                .map(billingAddressConverter::toResponse)
                .collect(Collectors.toList());
    }

    public BillingAddressResponse findByInvoiceId(UUID id) throws WebsiteUserNotFoundException {
        BillingAddress billingAddress = billingAddressRepository.findById(id).orElseThrow(WebsiteUserNotFoundException::new);

        return billingAddressConverter.toResponse(billingAddress);
    }
}
