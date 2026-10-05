package com.testProject.razorpay.merchant.service;

import com.testProject.razorpay.common.enums.MerchantStatus;
import com.testProject.razorpay.common.enums.UserRole;
import com.testProject.razorpay.common.exception.DuplicateResourceException;
import com.testProject.razorpay.merchant.dto.request.MerchantSignupRequest;
import com.testProject.razorpay.merchant.dto.response.MerchantResponse;
import com.testProject.razorpay.merchant.entity.AppUser;
import com.testProject.razorpay.merchant.entity.Merchant;
import com.testProject.razorpay.merchant.repository.AppUserRepository;
import com.testProject.razorpay.merchant.repository.MerchantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService{

    private final AppUserRepository appUserRepository;
    private final MerchantRepository merchantRepository;

    @Override
    @Transactional
    public MerchantResponse signup(MerchantSignupRequest request) {
        if(merchantRepository.existsByEmail(request.email())){
            throw new DuplicateResourceException("DUPLICATE_MERCHANT_EMAIL",
                    "Merchant with same email already exist:"+request.email());
        }
        Merchant merchant = Merchant.builder()
                .businessName(request.businessName())
                .businessType(request.businessType())
                .name(request.name())
                .email(request.email())
                .status(MerchantStatus.PENDING_kyc)
                .build();
        merchant = merchantRepository.save(merchant);

        AppUser appUser = AppUser.builder()
                .email(request.email())
                .merchant(merchant)
                .passwordHash(request.password())//Becrypt
                .role(UserRole.OWNER)
                .build();
        appUserRepository.save(appUser);

        return new MerchantResponse(merchant.getId(), merchant.getName(),
                merchant.getEmail(), merchant.getBusinessName(),
                merchant.getBusinessType(),merchant.getStatus());
    }
}
