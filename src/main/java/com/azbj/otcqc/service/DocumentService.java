package com.azbj.otcqc.service;

import com.azbj.otcqc.dto.DocumentDTO;
import com.azbj.otcqc.dto.DocumentUploadDTO;
import com.azbj.otcqc.dto.KycValidationDTO;
import com.azbj.otcqc.model.DocumentModel;
import com.azbj.otcqc.model.DocumentUploadModel;
import com.azbj.otcqc.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public List<DocumentDTO> retrieveDocuments() {
        List<DocumentModel> documents = documentRepository.findAllDocuments();
        return documents.stream().map(document -> new DocumentDTO(document.getId(), document.getName(), document.getPath())).collect(Collectors.toList());
    }

    public String downloadKycDoc(KycValidationDTO kycValidationDTO) {
        String contractId = documentRepository.getContractId(kycValidationDTO.getProposalNumber());
        PersonalDetails personalDetails = documentRepository.getPersonalDetails(contractId);
        String dobFormatted = new SimpleDateFormat("dd-MM-yyyy").format(personalDetails.getDateOfBirth());
        String jsonString = String.format("{\"taxId\":\"%s\",\"dob\":\"%s\",\"sex\":\"%s\",\"firstName\":\"%s\",\"middleName\":\"%s\",\"surname\":\"%s\"}",
                personalDetails.getTaxId(), dobFormatted, personalDetails.getSex(), personalDetails.getFirstName(), personalDetails.getMiddleName(), personalDetails.getSurname());
        // Send JSON to predefined URL for KYC validation (mocked here)
        String response = sendJsonToKycValidationService(jsonString);
        return response;
    }

    private String sendJsonToKycValidationService(String jsonString) {
        // Mocking the KYC validation service call
        return "KYC validation successful";
    }

    public void uploadDocument(DocumentUploadDTO documentUploadDTO) {
        DocumentUploadModel documentUploadModel = new DocumentUploadModel(documentUploadDTO.getId(), documentUploadDTO.getName(), documentUploadDTO.getPath());
        documentRepository.save(documentUploadModel);
    }

    public String getImagePath(String proposalNumber, String imageName) {
        return documentRepository.findImagePath(proposalNumber, imageName);
    }

    public void transferImage(String imagePath) {
        try {
            Path sourcePath = Paths.get(imagePath);
            Path destinationPath = Paths.get(System.getProperty("java.io.tmpdir"), sourcePath.getFileName().toString());
            Files.copy(sourcePath, destinationPath);
            // Log transfer details (mocked here)
            logTransferDetails(destinationPath.toString());
        } catch (IOException e) {
            // Handle error
            logError(e.getMessage());
        }
    }

    private void logTransferDetails(String details) {
        // Mocking the logging of transfer details
        System.out.println("Transfer details: " + details);
    }

    private void logError(String error) {
        // Mocking the logging of errors
        System.err.println("Error: " + error);
    }
}
