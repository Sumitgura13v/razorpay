package com.testProject.razorpay.payment.entity;

import com.testProject.razorpay.common.enums.PaymentEvent;
import com.testProject.razorpay.common.enums.PaymentStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payment_transition_log")
public class PaymentTransitionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    @Enumerated(EnumType.STRING)
    @Column(name = "from_status", nullable = false, length = 50)
    private PaymentStatus fromStatus;

    @Enumerated(EnumType.STRING)
    private PaymentEvent event;

    @Enumerated(EnumType.STRING)
    @Column(name = "to_status", nullable = false, length = 50)
    private PaymentStatus toStatus;

    @Column(name = "actor", nullable = false, length = 100)
    private String actor;

    @Column(name = "occurred_at", nullable = false)
    private LocalDateTime occurredAt;



}
