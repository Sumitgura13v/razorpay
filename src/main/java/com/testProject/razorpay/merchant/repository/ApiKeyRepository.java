package com.testProject.razorpay.merchant.repository;

import com.testProject.razorpay.merchant.entity.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKey, UUID> {

    List<ApiKey>findByMerchant_Id(UUID merchantId);

    Optional<ApiKey> findByKeyId(String keyId);


}
