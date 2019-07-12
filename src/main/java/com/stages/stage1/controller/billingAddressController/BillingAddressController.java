package com.stages.stage1.controller.billingAddressController;

import com.stages.stage1.dto.request.BillingAddressRequest;
import com.stages.stage1.dto.response.BillingAddressResponse;
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

    @ResponseStatus(CREATED)
    @ResponseBody
    @PostMapping("/createBilling")
    public BillingAddressResponse saveBillingAddress(@RequestBody BillingAddressRequest request) {
        return billingAddressService.saveBillingAddress(request);
    }

    @ResponseStatus(OK)
    @ResponseBody
    @GetMapping("/getInvoices/{id}")
    public List<BillingAddressResponse> findAllBillingAddress(@PathVariable("id") UUID id) {
        return billingAddressService.findAllBillingAddress(id);
    }

}