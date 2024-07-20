package com.azbj.otcqc.controller;

import com.azbj.otcqc.dto.*;
import com.azbj.otcqc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/validation")
public class ValidationController {

    @Autowired
    private ValidationService validationService;

    @Autowired
    private ProposalDetailsService proposalDetailsService;

    @Autowired
    private UwCommentsService uwCommentsService;

    @Autowired
    private DeqcSearchService deqcSearchService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private QcService qcService;

    @Autowired
    private RejectService rejectService;

    @Autowired
    private UnderwriterCommentService underwriterCommentService;

    @PostMapping("/validateFlagsAndStatuses")
    public ResponseEntity<ValidationDTO> validateFlagsAndStatuses(@RequestParam String applicationNo) {
        ValidationDTO validationDTO = validationService.validateFlagsAndStatuses(applicationNo);
        return ResponseEntity.ok(validationDTO);
    }

    @PostMapping("/saveDocumentStatus")
    public ResponseEntity<Void> saveDocumentStatus(@RequestParam String status) {
        validationService.saveDocumentStatus(status);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getDocumentReceivedStatus")
    public ResponseEntity<String> getDocumentReceivedStatus() {
        String status = validationService.fetchDocumentStatus();
        return ResponseEntity.ok(status);
    }

    @GetMapping("/getProposalDetails")
    public ResponseEntity<ProposalDetailsDTO> getProposalDetails(@RequestParam String proposalId) {
        ProposalDetailsDTO proposalDetails = proposalDetailsService.getProposalDetails(proposalId);
        return ResponseEntity.ok(proposalDetails);
    }

    @PostMapping("/submitProposal")
    public ResponseEntity<Void> submitProposal(@RequestBody ProposalDetailsDTO proposalDetails) {
        proposalDetailsService.submitProposal(proposalDetails);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/generateDocumentViewerUrl")
    public ResponseEntity<String> generateDocumentViewerUrl(@RequestParam String applicationNumber) {
        String url = validationService.generateDocumentViewerUrl(applicationNumber);
        return ResponseEntity.ok(url);
    }

    @GetMapping("/checkDocumentVerificationStatus")
    public ResponseEntity<Boolean> checkDocumentVerificationStatus() {
        boolean isVerified = validationService.checkDocumentVerificationStatus();
        return ResponseEntity.ok(isVerified);
    }

    @PostMapping("/addComment")
    public ResponseEntity<Void> addComment(@RequestParam String comment) {
        uwCommentsService.addComment(comment);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getComments")
    public ResponseEntity<List<Comment>> getComments() {
        List<Comment> comments = uwCommentsService.getComments();
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/getUnderwriterComments")
    public ResponseEntity<List<UnderwriterCommentDTO>> getUnderwriterComments(@RequestParam String userId, @RequestParam String controlProfile, @RequestParam String contractId) {
        List<UnderwriterCommentDTO> comments = validationService.fetchComments(userId, controlProfile, contractId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/rejectApplications")
    public ResponseEntity<String> rejectApplications(@RequestBody RejectDTO rejectData) {
        validationService.rejectApplications(rejectData);
        return ResponseEntity.ok("Applications rejected successfully");
    }

    @PostMapping("/searchRecords")
    public ResponseEntity<List<DeqcSearchDTO>> searchRecords(@RequestParam String fromDate, @RequestParam String toDate, @RequestParam String applicationNumber, @RequestParam String partnerType, @RequestParam String status) {
        List<DeqcSearchDTO> records = deqcSearchService.searchRecords(fromDate, toDate, applicationNumber, partnerType, status);
        return ResponseEntity.ok(records);
    }

    @PostMapping("/saveState")
    public ResponseEntity<Void> saveState(@RequestBody ValidationDTO currentState) {
        deqcSearchService.saveState(currentState);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/viewDocuments")
    public ResponseEntity<List<DocumentDTO>> viewDocuments(@RequestParam String recordId) {
        List<DocumentDTO> documents = documentService.viewDocuments(recordId);
        return ResponseEntity.ok(documents);
    }

    @PostMapping("/uploadDocuments")
    public ResponseEntity<Void> uploadDocuments(@RequestParam String recordId, @RequestParam MultipartFile[] documents) {
        documentService.uploadDocuments(recordId, documents);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/editRecord")
    public ResponseEntity<Void> editRecord(@RequestParam String recordId) {
        qcService.editRecord(recordId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getComments")
    public ResponseEntity<List<UnderwriterCommentDTO>> getComments(@RequestParam String recordId) {
        List<UnderwriterCommentDTO> comments = underwriterCommentService.getComments(recordId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/rejectRecord")
    public ResponseEntity<Void> rejectRecord(@RequestParam String recordId) {
        rejectService.rejectRecord(recordId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/confirmReason")
    public ResponseEntity<Void> confirmReason(@RequestParam String reason) {
        // Logic to confirm the reason for rejection
        return ResponseEntity.ok().build();
    }

    @PostMapping("/validateIFSCCode")
    public ResponseEntity<ValidationDTO> validateIFSCCode(@RequestParam String ifscCode) {
        ValidationDTO validationDTO = validationService.validateIFSCCode(ifscCode);
        return ResponseEntity.ok(validationDTO);
    }

    @GetMapping("/validateDocuments")
    public ResponseEntity<Boolean> validateDocuments() {
        boolean isValid = validationService.validateDocuments();
        return ResponseEntity.ok(isValid);
    }

    @PostMapping("/submitForm")
    public ResponseEntity<Void> submitForm() {
        validationService.submitForm();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getBankDetails")
    public ResponseEntity<BankDetailsDTO> getBankDetails(@RequestParam String ifscCode) {
        BankDetailsDTO bankDetails = validationService.getBankDetails(ifscCode);
        return ResponseEntity.ok(bankDetails);
    }

    @PostMapping("/validateAccountDetails")
    public ResponseEntity<Boolean> validateAccountDetails(@RequestBody ValidationDTO validationDTO) {
        boolean isValid = validationService.validateAccountDetails(validationDTO.getAccountNumber(), validationDTO.getIfscCode());
        return ResponseEntity.ok(isValid);
    }

    @PostMapping("/validatePanCard")
    public ResponseEntity<ValidationResult> validatePanCard(@RequestBody PanDetailsDTO panDetailsDTO) {
        ValidationResult result = validationService.validatePanCard(panDetailsDTO);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/searchApplications")
    public ResponseEntity<List<SearchResultDTO>> searchApplications(@RequestBody SearchCriteriaDTO searchCriteriaDTO) {
        List<SearchResultDTO> results = validationService.searchApplications(searchCriteriaDTO);
        return ResponseEntity.ok(results);
    }

    @PostMapping("/createAndCallForm")
    public ResponseEntity<String> createAndCallForm(@RequestBody Map<String, Object> paramList) {
        validationService.createParamList(paramList);
        validationService.callForm(paramList);
        return ResponseEntity.ok("Form called successfully");
    }

    @GetMapping("/checkPanStatus")
    public ResponseEntity<Boolean> checkPanStatus() {
        boolean isVerified = validationService.checkPanStatus();
        return ResponseEntity.ok(isVerified);
    }

    @PostMapping("/validatePanCard")
    public ResponseEntity<PanDetailsDTO> validatePanCard(@RequestParam String panCardNumber) {
        PanDetailsDTO panDetails = validationService.validatePanCard(panCardNumber);
        return ResponseEntity.ok(panDetails);
    }
}