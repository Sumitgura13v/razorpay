package com.testProject.razorpay.merchant.dto.request;

import com.testProject.razorpay.common.enums.BusinessType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MerchantSignupRequest(

        @NotNull(message = "name should be provided")
        @Size(max = 50, message = "name should not be more than 50 characters")
        String name,

        @Email
        @NotNull
        String email,

        @NotNull(message = "Password is required")
        @Size(min = 8, message = "Password should be atleast 8 characters long")
        String password,

        @Size(max = 50, message = "BusinessName should not be more than 50 characters long")
        String businessName,

        BusinessType businessType
){

}
