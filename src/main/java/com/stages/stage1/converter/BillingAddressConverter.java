package com.stages.stage1.converter;

import com.stages.stage1.dto.billingAddress.BillingAddressRequest;
import com.stages.stage1.dto.billingAddress.BillingAddressResponse;
import com.stages.stage1.entity.BillingAddress;
import com.stages.stage1.entity.WebsiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillingAddressConverter {

    public BillingAddress toBillingAddress(WebsiteUser user, BillingAddressRequest request, List<BillingAddressResponse> billingAddressResponses) {
        return new BillingAddress()
                .setAddress(request.getAddress())
                .setInvoiceNumber(billingAddressResponses.size())
                .setWebsiteUser(user);
    }

    public BillingAddressResponse toResponse(BillingAddress billingAddress) {
        return new BillingAddressResponse()
                .setAddress(billingAddress.getAddress())
                .setUserId(billingAddress.getWebsiteUser().getId())
                .setInvoiceNumber(billingAddress.getInvoiceNumber());
    }

}