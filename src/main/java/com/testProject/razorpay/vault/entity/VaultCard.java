package com.testProject.razorpay.vault.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "vault_card")
public class VaultCard {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id; // Unique identifier for the vault card

    @Column(nullable = false, length =4)
    private String lastFour; //Last 4 digits of the card number

    @Column(nullable = false, length = 6)
    private String bin; //First 6 digits of the card number

    @Column(nullable = false)
    private byte[] encryptedPan; //Encrypted Primary Account Number (PAN)

    @Column(nullable = false)
    private byte[] encryptedDek; //Encrypted Data Encryption Key (DEK) used for encrypting the PAN

    @Column(nullable = false)
    private String brand; //VISA, MASTERCARD, AMEX, etc.

    @Column(nullable = false)
    private String expiryMonth;

    @Column(nullable = false)
    private String expiryYear;

    @Column(nullable = false)
    private String cardHolderName;

    private LocalDateTime deletedAt;

}
