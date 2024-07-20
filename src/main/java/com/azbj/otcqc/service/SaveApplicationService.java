package com.azbj.otcqc.service;

import com.azbj.otcqc.repository.SaveApplicationRepository;
import com.azbj.otcqc.dto.SaveApplicationDTO;
import com.azbj.otcqc.model.SaveApplicationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaveApplicationService {

    @Autowired
    private SaveApplicationRepository saveApplicationRepository;

    @Transactional
    public String saveApplicationDetails(SaveApplicationDTO saveApplicationDTO) {
        if (saveApplicationDTO.getReasonForLink() == null || saveApplicationDTO.getCommentCount() <= 0) {
            return "Please provide comments.";
        }

        // Convert DTO to Model
        SaveApplicationModel saveApplicationModel = new SaveApplicationModel();
        saveApplicationModel.setApplicationNumber(saveApplicationDTO.getApplicationNumber());
        saveApplicationModel.setReasonForLink(saveApplicationDTO.getReasonForLink());
        saveApplicationModel.setCommentCount(saveApplicationDTO.getCommentCount());

        // Save to database
        SaveApplicationModel savedModel = saveApplicationRepository.save(saveApplicationModel);

        // Process each record in DEQC_DISPLAY section
        for (SaveApplicationDTO.Record record : saveApplicationDTO.getRecords()) {
            if ("Y".equals(record.getMarked())) {
                // Retrieve contract ID
                String contractId = saveApplicationRepository.findContractId(record.getApplicationNumber());

                // Call procedure to issue policy
                String policyNumber = saveApplicationRepository.callAutoIssuanceProcedure(contractId);

                if (policyNumber != null) {
                    // Check for rule errors
                    boolean hasRuleErrors = saveApplicationRepository.checkForRuleErrors(record.getActivityId());

                    // Check if all required documents are received
                    boolean allDocumentsReceived = saveApplicationRepository.checkAllDocumentsReceived(record.getApplicationNumber());

                    if (!hasRuleErrors && allDocumentsReceived) {
                        // Update status to 'PENDING_FOR_AUTO_BBU'
                        saveApplicationRepository.updateStatusToPendingForAutoBBU(contractId, record.getApplicationNumber(), policyNumber);
                    } else {
                        // Update status to 'FR-AR'
                        saveApplicationRepository.updateStatusToFRAR(contractId, record.getApplicationNumber());
                    }
                } else {
                    return "Policy issuance failed for application number: " + record.getApplicationNumber();
                }
            }
        }

        return "Data saved successfully.";
    }
}
