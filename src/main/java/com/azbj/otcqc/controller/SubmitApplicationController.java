package com.azbj.otcqc.controller;

import com.azbj.otcqc.service.SubmitApplicationService;
import com.azbj.otcqc.dto.SubmitApplicationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/application")
public class SubmitApplicationController {

    @Autowired
    private SubmitApplicationService submitApplicationService;

    @PostMapping("/submit")
    public ResponseEntity<String> submitApplication(@RequestBody SubmitApplicationDTO submitApplicationDTO) {
        try {
            submitApplicationService.submitApplication(submitApplicationDTO);
            return ResponseEntity.ok("Application submitted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error submitting application: " + e.getMessage());
        }
    }
}
