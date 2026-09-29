package com.testProject.razorpay.operations.entity;

import com.testProject.razorpay.common.enums.WebhookEventStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.Map;

@Entity
@Table(name = "webhook_event")
public class WebhookEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID merchantId;

    @Column(nullable = false, length = 20)
    private String eventType;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String , Object> payload;

    @Column(nullable = false)
    private String targetUrl;

    @Column(nullable = false)
    private String signature;

    @Enumerated(EnumType.STRING)
    private WebhookEventStatus status;

    @Column(nullable = false)
    private Integer attempts=0;

    private LocalDateTime nextRetryAt;

    private LocalDateTime lastAttemptAt;

    @Column(nullable = false)
    private Integer lastResponseCode;

    @Column(nullable = false, length = 1500)
    private String lastResponseBody;

    private LocalDateTime deliveredAt;



}
