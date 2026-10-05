package com.testProject.razorpay.merchant.dto.request;

import com.testProject.razorpay.common.enums.Environment;

public record CreateApiKeyRequest(
        Environment environment
) {
}
