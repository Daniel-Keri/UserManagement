package com.stages.stage1.controller.billingAddress;

import com.stages.stage1.dto.billingAddress.BillingAddressRequest;
import com.stages.stage1.dto.billingAddress.BillingAddressResponse;
import com.stages.stage1.exc.WebsiteUserNotFoundException;
import com.stages.stage1.service.BillingAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("billing")
public class BillingAddressController {

    private final BillingAddressService billingAddressService;

    // http://localhost:8080/billing/createBilling
    @ResponseStatus(CREATED)
    @PostMapping("/createBilling")
    public BillingAddressResponse saveBillingAddress(@RequestBody BillingAddressRequest request) throws WebsiteUserNotFoundException {
        return billingAddressService.saveBillingAddress(request);
    }

    // http://localhost:8080/billing/getInvoices/{user_id}
    @ResponseStatus(OK)
    @GetMapping("/getInvoices/{user_id}")
    public List<BillingAddressResponse> findAllBillingAddressByUserId(@PathVariable("user_id") UUID user_id) {
        return billingAddressService.findAllBillingAddressByUserId(user_id);
    }

    // http://localhost:8080/billing/getInvoices/invoice/{invoice_id}
    @ResponseStatus(OK)
    @GetMapping("/getInvoices/invoice/{invoice_id}")
    public BillingAddressResponse findInvoice(@PathVariable("invoice_id") UUID invoice_id) throws WebsiteUserNotFoundException {
        return billingAddressService.findByInvoiceId(invoice_id);
    }
}