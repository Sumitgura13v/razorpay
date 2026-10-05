package com.testProject.razorpay.merchant.service;

import com.testProject.razorpay.merchant.dto.request.MerchantSignupRequest;
import com.testProject.razorpay.merchant.dto.response.MerchantResponse;

public interface AuthService {

    MerchantResponse signup(MerchantSignupRequest request);
}
