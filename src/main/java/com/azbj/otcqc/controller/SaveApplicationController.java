package com.azbj.otcqc.controller;

import com.azbj.otcqc.dto.SaveApplicationDTO;
import com.azbj.otcqc.service.SaveApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/application")
public class SaveApplicationController {

    @Autowired
    private SaveApplicationService saveApplicationService;

    @PostMapping("/save")
    public ResponseEntity<String> saveApplicationDetails(@RequestBody SaveApplicationDTO saveApplicationDTO) {
        // Call the service method to save application details
        return saveApplicationService.saveApplicationDetails(saveApplicationDTO);
    }

    @PostMapping("/saveBankDetails")
    public ResponseEntity<String> saveBankDetails(@RequestBody SaveApplicationDTO saveApplicationDTO) {
        // Call the service method to save bank details
        return saveApplicationService.saveBankDetails(saveApplicationDTO);
    }
}
