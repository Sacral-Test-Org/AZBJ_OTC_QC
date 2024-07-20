package com.azbj.otcqc.controller;

import com.azbj.otcqc.dto.BankDetailsDTO;
import com.azbj.otcqc.service.BankDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bank-details")
public class BankDetailsController {

    @Autowired
    private BankDetailsService bankDetailsService;

    @PostMapping("/save")
    public ResponseEntity<String> saveBankDetails(@RequestBody BankDetailsDTO bankDetailsDTO) {
        try {
            bankDetailsService.saveBankDetails(bankDetailsDTO);
            return new ResponseEntity<>("Bank details saved successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error saving bank details: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
