package com.testProject.razorpay.merchant.entity;

import com.testProject.razorpay.common.enums.Environment;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "api_key")
public class ApiKey {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merchant_id", nullable = false)
    private Merchant merchant;

    @Column(nullable = false,length = 100, unique = true)
    private String keyId;

    @Column(nullable = false,length = 200)
    private String keySecretHash;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Environment envirnonment;

    @Column(nullable = false)
    private boolean enabled;

    private LocalDateTime lastUsedAt;
    private LocalDateTime rotatedAt;
    private LocalDateTime gracePeriodExpiresAt;


}
