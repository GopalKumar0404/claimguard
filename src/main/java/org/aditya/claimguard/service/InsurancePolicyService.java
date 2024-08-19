package org.aditya.claimguard.service;

import org.aditya.claimguard.entity.InsurancePolicy;
import org.aditya.claimguard.repository.InsurancePolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InsurancePolicyService {
    @Autowired
    private InsurancePolicyRepository insurancePolicyRepository;

    public InsurancePolicy createPolicy(InsurancePolicy insurancePolicy) {
        insurancePolicy.setPolicyNumber(UUID.randomUUID().toString());
        return insurancePolicyRepository.save(insurancePolicy);
    }

    public Optional<InsurancePolicy> getPolicyByNumber(String policyNumber) {
        return insurancePolicyRepository.findByPolicyNumber(policyNumber);
    }

    public List<InsurancePolicy> getAllPolicies() {
        return insurancePolicyRepository.findAll();
    }

    public void deletePolicy(Long id) {
        insurancePolicyRepository.deleteById(id);
    }

    public InsurancePolicy updatePolicy(Long id, InsurancePolicy policyDetails) {
        InsurancePolicy policy = insurancePolicyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Policy not found"));
        policy.setEndDate(policyDetails.getEndDate());
        policy.setPremiumAmount(policyDetails.getPremiumAmount());
        return insurancePolicyRepository.save(policy);
    }
}

