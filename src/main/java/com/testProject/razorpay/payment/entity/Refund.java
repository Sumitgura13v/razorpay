package com.testProject.razorpay.payment.entity;

import com.testProject.razorpay.common.entity.Money;
import com.testProject.razorpay.common.enums.RefundStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "refunds")
public class Refund {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    private UUID id;

    @JoinColumn(name = "payment_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Payment payment;

    @JoinColumn(name = "merchant_id", nullable = false)
    private UUID merchantId;

    @Embedded
    private Money amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RefundStatus status = RefundStatus.PENDING;

    @Column(nullable = false, length = 100)
    private String bankReference;

    @Column(length = 100)
    private String errorCode;

    @Column(length = 500)
    private String errorDescription;

    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> notes;

    private LocalDateTime processedAt;



}
