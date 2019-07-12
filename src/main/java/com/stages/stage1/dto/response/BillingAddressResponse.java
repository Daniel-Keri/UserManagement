package com.stages.stage1.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class BillingAddressResponse {

    private UUID userId;

    private String address;

    private Integer invoiceNumber;
}
