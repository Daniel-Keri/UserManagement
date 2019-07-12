package com.stages.stage1.dto.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
public class BillingAddressResponse {

    private UUID userId;

    private String address;

    private Integer invoiceNumber;
}
