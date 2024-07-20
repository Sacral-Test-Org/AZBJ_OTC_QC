package com.azbj.otcqc.controller;

import com.azbj.otcqc.dto.DocumentDTO;
import com.azbj.otcqc.dto.DocumentUploadDTO;
import com.azbj.otcqc.dto.KycValidationDTO;
import com.azbj.otcqc.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping("/view-all")
    public ResponseEntity<List<DocumentDTO>> getDocuments() {
        List<DocumentDTO> documents = documentService.retrieveDocuments();
        return ResponseEntity.ok(documents);
    }

    @PostMapping("/download-kyc")
    public ResponseEntity<String> downloadKycDoc(@RequestBody KycValidationDTO kycValidationDTO) {
        String response = documentService.downloadKycDoc(kycValidationDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadDocument(@RequestBody DocumentUploadDTO documentUploadDTO) {
        documentService.uploadDocument(documentUploadDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/view-image")
    public ResponseEntity<String> getImagePath(@RequestParam String proposalNumber, @RequestParam String imageName) {
        String imagePath = documentService.getImagePath(proposalNumber, imageName);
        return ResponseEntity.ok(imagePath);
    }
}
