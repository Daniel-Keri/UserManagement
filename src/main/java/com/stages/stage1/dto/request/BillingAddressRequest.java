package com.stages.stage1.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class BillingAddressRequest {


    private UUID userId;

    private String address;

    private Integer invoiceNumber;
}
