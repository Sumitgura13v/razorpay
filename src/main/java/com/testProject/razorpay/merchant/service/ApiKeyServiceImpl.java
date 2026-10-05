package com.testProject.razorpay.merchant.service;

import com.testProject.razorpay.common.exception.ResourceNotFoundException;
import com.testProject.razorpay.common.util.RandomizerUtil;
import com.testProject.razorpay.merchant.dto.request.CreateApiKeyRequest;
import com.testProject.razorpay.merchant.dto.response.ApiKeyCreateResponse;
import com.testProject.razorpay.merchant.dto.response.ApiKeyResponse;
import com.testProject.razorpay.merchant.entity.ApiKey;
import com.testProject.razorpay.merchant.entity.Merchant;
import com.testProject.razorpay.merchant.repository.ApiKeyRepository;
import com.testProject.razorpay.merchant.repository.MerchantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApiKeyServiceImpl implements ApiKeyService {

    private final ApiKeyRepository apiKeyRepository;
    private final MerchantRepository merchantRepository;

    @Transactional
    @Override
    public ApiKeyCreateResponse create(UUID merchantId, CreateApiKeyRequest request) {

        Merchant merchant = merchantRepository.findById(merchantId).orElseThrow(() ->
                new ResourceNotFoundException("merchant", merchantId));

        String keyId = "rzp_"+request.environment().name().toLowerCase()+"_"+ RandomizerUtil.randomBase64(24);
        String rawSecret = RandomizerUtil.randomBase64(40);

        ApiKey apiKey = ApiKey.builder()
                .merchant(merchant)
                .keyId(keyId)
                .keySecretHash(rawSecret)
                .environment(request.environment())
                .build();

        apiKey = apiKeyRepository.save(apiKey);

        return new ApiKeyCreateResponse(apiKey.getId(), keyId, rawSecret, request.environment());
    }

    @Transactional
    @Override
    public List<ApiKeyResponse> listByMerchant(UUID merchantId) {
        return apiKeyRepository.findByMerchant_Id(merchantId).stream()
                .map(apiKey -> new ApiKeyResponse(
                        apiKey.getId(),
                        apiKey.getKeyId(),
                        apiKey.getEnvironment(),
                        apiKey.isEnabled(),
                        apiKey.getLastUsedAt(),null))
                .toList();
    }

    @Transactional
    @Override
    public void revoke(UUID merchantId, String keyId) {

        ApiKey key = apiKeyRepository.findByKeyId(keyId)
                .filter(k -> k.getMerchant().getId().equals(merchantId))
                .orElseThrow(() ->
                        new ResourceNotFoundException("apiKey", keyId));

        key.setEnabled(false);

        apiKeyRepository.save(key);
    }

    @Override
    public Object rotate(UUID merchantId, String keyId) {
        ApiKey apiKey = apiKeyRepository.findByKeyId(keyId)
                .filter(k -> k.getMerchant().getId().equals(merchantId))
                .orElseThrow(() ->
                        new ResourceNotFoundException("apiKey", keyId));

       String rawSecret = RandomizerUtil.randomBase64(40);
       apiKey.setPreviousKeySecretHash(apiKey.getKeySecretHash());
       apiKey.setKeySecretHash(rawSecret);//TODO: Hash the secret before saving//
        apiKey.setRotatedAt(LocalDateTime.now());
        apiKey.setGracePeriodExpiresAt(LocalDateTime.now().plusHours(24));
        apiKey = apiKeyRepository.save(apiKey);

        return new ApiKeyCreateResponse(apiKey.getId(), apiKey.getKeyId(), rawSecret, apiKey.getEnvironment());

     }


}
