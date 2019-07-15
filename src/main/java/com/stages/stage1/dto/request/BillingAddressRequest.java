package com.stages.stage1.dto.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain=true)
public class BillingAddressRequest {

    private UUID userId;

    private String address;

    private Integer invoiceNumber;
}
