package com.stages.stage1.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table( name = "billing_address")
@Accessors(chain = true)
public class BillingAddress extends BaseEntity {

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private WebsiteUser websiteUser;

    @Column(name = "address")
    private String address;

    @Column(name = "invoice_number")
    private Integer invoiceNumber;
}