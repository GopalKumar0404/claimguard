package org.aditya.claimguard.controller;

import org.aditya.claimguard.entity.InsurancePolicy;
import org.aditya.claimguard.service.InsurancePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/insurance")
public class InsurancePolicyController {
    @Autowired
    private InsurancePolicyService insurancePolicyService;

    @PostMapping("/create")
    public ResponseEntity<InsurancePolicy> createPolicy(@RequestBody InsurancePolicy insurancePolicy) {
        return new ResponseEntity<>(insurancePolicyService.createPolicy(insurancePolicy), HttpStatus.CREATED);
    }

    @GetMapping("/{policyNumber}")
    public ResponseEntity<InsurancePolicy> getPolicyByNumber(@PathVariable String policyNumber) {
        return ResponseEntity.of(insurancePolicyService.getPolicyByNumber(policyNumber));
    }

    @GetMapping
    public ResponseEntity<List<InsurancePolicy>> getAllPolicies() {
        return ResponseEntity.ok(insurancePolicyService.getAllPolicies());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePolicy(@PathVariable Long id) {
        insurancePolicyService.deletePolicy(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<InsurancePolicy> updatePolicy(@PathVariable Long id, @RequestBody InsurancePolicy policy) {
        return ResponseEntity.ok(insurancePolicyService.updatePolicy(id, policy));
    }
}

