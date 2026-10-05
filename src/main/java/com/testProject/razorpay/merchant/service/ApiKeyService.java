package com.testProject.razorpay.merchant.service;

import com.testProject.razorpay.merchant.dto.request.CreateApiKeyRequest;
import com.testProject.razorpay.merchant.dto.response.ApiKeyCreateResponse;
import com.testProject.razorpay.merchant.dto.response.ApiKeyResponse;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public interface ApiKeyService {
    ApiKeyCreateResponse create(UUID merchantId, CreateApiKeyRequest request);

     List<ApiKeyResponse> listByMerchant(UUID merchantId);

     void revoke(UUID merchantId, String keyId);

     Object rotate(UUID merchantId, String keyId);
}


