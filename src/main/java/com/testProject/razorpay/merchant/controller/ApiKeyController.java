package com.testProject.razorpay.merchant.controller;

import com.testProject.razorpay.merchant.dto.request.CreateApiKeyRequest;
import com.testProject.razorpay.merchant.dto.response.ApiKeyCreateResponse;
import com.testProject.razorpay.merchant.dto.response.ApiKeyResponse;
import com.testProject.razorpay.merchant.service.ApiKeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/merchants/{merchantId}/api-keys")
public class ApiKeyController {

    private final ApiKeyService apiKeyService;

    @PostMapping
    public ResponseEntity<ApiKeyCreateResponse> create(@PathVariable UUID merchantId,
                                                        @RequestBody CreateApiKeyRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                apiKeyService.create(merchantId, request)
        );
    }

    @GetMapping
    public ResponseEntity<List<ApiKeyResponse>> listByMerchant(@PathVariable UUID merchantId) {
        return ResponseEntity.ok(apiKeyService.listByMerchant(merchantId));

    }
    @DeleteMapping("/{keyId}")
    public ResponseEntity<Void> revoke(@PathVariable UUID merchantId, @PathVariable String keyId) {
        apiKeyService.revoke(merchantId, keyId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{keyId}/rotate")
    public ResponseEntity<ApiKeyCreateResponse> rotateKey(@PathVariable UUID merchantId, @PathVariable String keyId) {
        /*return ResponseEntity.ok(apiKeyService.rotate(merchantId, keyId));*/
        apiKeyService.rotate(merchantId, keyId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
