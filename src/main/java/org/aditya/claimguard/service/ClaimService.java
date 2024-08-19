package org.aditya.claimguard.service;

import org.aditya.claimguard.entity.Claim;
import org.aditya.claimguard.repository.ClaimRepository;
import org.aditya.claimguard.repository.InsurancePolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ClaimService {
    @Autowired
    private ClaimRepository claimRepository;
    @Autowired
    private InsurancePolicyRepository insurancePolicyRepository;

    public Claim fileClaim(Claim claim) {
        claim.setClaimNumber(UUID.randomUUID().toString());
        claim.setStatus("Submitted");
        claim.setClaimDate(LocalDate.now());
        return claimRepository.save(claim);
    }

    public List<Claim> getClaimsByPolicyId(Long policyId) {
        return claimRepository.findByPolicyId(policyId);
    }

    public Claim updateClaimStatus(Long id, String status) {
        Claim claim = claimRepository.findById(id).orElseThrow(() -> new RuntimeException("Claim not found"));
        claim.setStatus(status);
        return claimRepository.save(claim);
    }

    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    public void deleteClaim(Long id) {
        claimRepository.deleteById(id);
    }
}

