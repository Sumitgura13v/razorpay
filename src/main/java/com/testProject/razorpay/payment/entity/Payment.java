package com.testProject.razorpay.payment.entity;

import com.testProject.razorpay.common.entity.Money;
import com.testProject.razorpay.common.enums.PaymentMethod;
import com.testProject.razorpay.common.enums.PaymentStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderRecord order;

    @Column(nullable = false)
    private UUID merchantId;

    @Embedded
    private Money amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    @Column(nullable = false, length = 100)
    private String idempotencyKey;

    @Column(length = 100)
    private String bankReference;

    @JdbcTypeCode((SqlTypes.JSON))
    @Column(name = "method_details",columnDefinition = "jsonb")
    private Map<String, Object> methodDetails;

    @Column(length = 100)
    private String errorCode;

    @Column(length = 500)
    private String errorDescription;

    private LocalDateTime authorizedAt;

    private LocalDateTime capturedAt;

    private LocalDateTime failedAt;

    private LocalDateTime refundedAt;

    private LocalDateTime settledAt;



}
