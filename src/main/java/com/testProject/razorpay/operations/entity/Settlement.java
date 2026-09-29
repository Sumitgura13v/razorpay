package com.testProject.razorpay.operations.entity;

import com.testProject.razorpay.common.entity.Money;
import com.testProject.razorpay.common.enums.SettlementStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "settlements")
public class Settlement {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID merchantId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amountUnits", column = @Column(name = "gross_amount_units", nullable = false)),
            @AttributeOverride(name = "currency", column = @Column(name = "gross_amount_currency", nullable = false))
    })
    private Money grossAmount;

    @AttributeOverrides({
            @AttributeOverride(name = "amountUnits", column = @Column(name = "refund_amount_units", nullable = false)),
            @AttributeOverride(name = "currency", column = @Column(name = "refund_amount_currency", nullable = false))
    })
    private Money refundAmount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amountUnits", column = @Column(name = "gst_amount_units", nullable = false)),
            @AttributeOverride(name = "currency", column = @Column(name = "gst_amount_currency", nullable = false))
    })
    private Money gstAmount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amountUnits", column = @Column(name = "net_amount_units", nullable = false)),
            @AttributeOverride(name = "currency", column = @Column(name = "net_amount_currency", nullable = false))
    })
    private Money netAmount;

    @Column(nullable = false, length = 100)
    private String bankReference;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private SettlementStatus settlementStatus;

    private LocalDateTime processedAt;

}
