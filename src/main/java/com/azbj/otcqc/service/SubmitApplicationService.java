package com.azbj.otcqc.service;

import com.azbj.otcqc.repository.SubmitApplicationRepository;
import com.azbj.otcqc.dto.SubmitApplicationDTO;
import com.azbj.otcqc.model.SubmitApplicationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class SubmitApplicationService {

    private static final Logger logger = LoggerFactory.getLogger(SubmitApplicationService.class);

    @Autowired
    private SubmitApplicationRepository submitApplicationRepository;

    public String saveApplicationDetails(SubmitApplicationDTO submitApplicationDTO) {
        // Convert DTO to Model
        SubmitApplicationModel submitApplicationModel = new SubmitApplicationModel();
        submitApplicationModel.setApplicationNumber(submitApplicationDTO.getApplicationNumber());
        submitApplicationModel.setAgeProof(submitApplicationDTO.getAgeProof());
        submitApplicationModel.setIdentityProof(submitApplicationDTO.getIdentityProof());
        submitApplicationModel.setIncomeProof(submitApplicationDTO.getIncomeProof());
        submitApplicationModel.setPermanentAddressProof(submitApplicationDTO.getPermanentAddressProof());
        submitApplicationModel.setCurrentAddressProof(submitApplicationDTO.getCurrentAddressProof());

        // Check if KYC documents have been downloaded
        if (!submitApplicationDTO.isKycDownloaded()) {
            return "KYC documents have not been downloaded.";
        }

        // Validate presence of required documents
        if (submitApplicationDTO.getAgeProof() == null || submitApplicationDTO.getIdentityProof() == null ||
            submitApplicationDTO.getIncomeProof() == null || submitApplicationDTO.getPermanentAddressProof() == null ||
            submitApplicationDTO.getCurrentAddressProof() == null) {
            return "Required documents are missing.";
        }

        // Save application details
        try {
            submitApplicationRepository.save(submitApplicationModel);
            logger.info("Application details saved for application number: " + submitApplicationDTO.getApplicationNumber());
        } catch (Exception e) {
            logger.error("Error saving application details: ", e);
            return "Error saving application details.";
        }

        // Update application status
        // Assuming there's a method to update status in the repository
        try {
            submitApplicationRepository.updateStatus(submitApplicationDTO.getApplicationNumber(), "SUBMITTED");
            logger.info("Application status updated to SUBMITTED for application number: " + submitApplicationDTO.getApplicationNumber());
        } catch (Exception e) {
            logger.error("Error updating application status: ", e);
            return "Error updating application status.";
        }

        return "Application submitted successfully.";
    }
}