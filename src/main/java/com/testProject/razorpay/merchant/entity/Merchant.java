package com.testProject.razorpay.merchant.entity;

import com.testProject.razorpay.common.enums.BusinessType;
import com.testProject.razorpay.common.enums.MerchantStatus;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="merchant")
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(length = 100)
    private String businessName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 20)
    private String contantNumber;

    @Enumerated(EnumType.STRING)
    @Column(length = 100)
    private BusinessType businessType;

    @Enumerated(EnumType.STRING)
    @Column(length = 100)
    private MerchantStatus Status = MerchantStatus.PENDING_kyc;

    @Column(length = 20)
    private String gst_id;

    @Column(length = 30)
    private String pan;

    @Column(length = 50)
    private String settlement_bank_account;

    @Column(length = 50)
    private String settlement_ifsc;

    @Column(length = 200)
    private String settlement_account_holder_name;

}
