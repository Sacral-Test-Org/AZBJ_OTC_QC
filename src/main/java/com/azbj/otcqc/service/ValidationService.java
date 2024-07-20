package com.azbj.otcqc.service;

import com.azbj.otcqc.dto.*;
import com.azbj.otcqc.repository.ValidationRepository;
import com.azbj.otcqc.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ValidationService {

    @Autowired
    private ValidationRepository validationRepository;

    public ValidationDTO validateFlagsAndStatuses(String applicationNo) {
        ValidationDTO validationDTO = new ValidationDTO();
        // Initialize variables and set default values
        validationDTO.setOtcEligible(false);
        validationDTO.setSpgCase(false);
        validationDTO.setKycFlag(false);

        // Validate user's authorization
        boolean isAuthorized = validationRepository.checkUserAuthorization("USER");
        if (!isAuthorized) {
            validationDTO.setAuthorized(false);
            return validationDTO;
        }
        validationDTO.setAuthorized(true);

        // Check for SPG case status
        boolean spgCaseStatus = validationRepository.checkSpgCaseStatus(applicationNo);
        validationDTO.setSpgCase(spgCaseStatus);

        // Check for OTC eligibility
        boolean otcEligibility = validationRepository.checkOtcEligibility(applicationNo);
        validationDTO.setOtcEligible(otcEligibility);

        // Check for KYC flag
        boolean kycFlag = validationRepository.checkKycFlag(applicationNo);
        validationDTO.setKycFlag(kycFlag);

        return validationDTO;
    }

    public void saveDocumentStatus(String status) {
        validationRepository.saveStatus(status);
    }

    public String fetchDocumentStatus() {
        return validationRepository.getStatus();
    }

    public ProposalDetailsDTO getProposalDetails(String proposalId) {
        Optional<ProposalDetailsModel> proposalDetails = validationRepository.findById(proposalId);
        if (proposalDetails.isPresent()) {
            ProposalDetailsDTO dto = new ProposalDetailsDTO();
            ProposalDetailsModel model = proposalDetails.get();
            dto.setProposalNumber(model.getProposalNumber());
            dto.setLifeAssuredName(model.getLifeAssuredName());
            dto.setApplicationNumber(model.getApplicationNumber());
            dto.setPolicyHolderName(model.getPolicyHolderName());
            dto.setComments(model.getComments());
            dto.setReferToSupervisor(model.getReferToSupervisor());
            dto.setTrlScore(model.getTrlScore());
            dto.setIncomeEstimate(model.getIncomeEstimate());
            dto.setCibilScore(model.getCibilScore());
            dto.setAccountType(model.getAccountType());
            dto.setChannelName(model.getChannelName());
            dto.setKycStatus(model.getKycStatus());
            dto.setExistingCustomerStatus(model.getExistingCustomerStatus());
            dto.setPasaFlag(model.getPasaFlag());
            dto.setPartialPasa(model.getPartialPasa());
            dto.setPennyDropSuccessful(model.getPennyDropSuccessful());
            dto.setPartialKyc(model.getPartialKyc());
            dto.setOcrFlag(model.getOcrFlag());
            dto.setNetPurchasePrice(model.getNetPurchasePrice());
            dto.setGrossPurchase(model.getGrossPurchase());
            dto.setAnnuityAmount(model.getAnnuityAmount());
            dto.setAnnuityOption(model.getAnnuityOption());
            dto.setAnnuityType(model.getAnnuityType());
            dto.setPremiumTerm(model.getPremiumTerm());
            dto.setAnnuityFrequency(model.getAnnuityFrequency());
            dto.setCustomerType(model.getCustomerType());
            dto.setRetention(model.getRetention());
            dto.setEdcPersistencyFlag(model.getEdcPersistencyFlag());
            dto.setSisoFlag(model.getSisoFlag());
            dto.setEnachStatus(model.getEnachStatus());
            dto.setMedicals(model.getMedicals());
            dto.setGsipFlag(model.getGsipFlag());
            dto.setWmmFlag(model.getWmmFlag());
            return dto;
        }
        return null;
    }

    public void submitProposal(ProposalDetailsDTO proposalDetails) {
        ProposalDetailsModel model = new ProposalDetailsModel();
        model.setProposalNumber(proposalDetails.getProposalNumber());
        model.setLifeAssuredName(proposalDetails.getLifeAssuredName());
        model.setApplicationNumber(proposalDetails.getApplicationNumber());
        model.setPolicyHolderName(proposalDetails.getPolicyHolderName());
        model.setComments(proposalDetails.getComments());
        model.setReferToSupervisor(proposalDetails.getReferToSupervisor());
        model.setTrlScore(proposalDetails.getTrlScore());
        model.setIncomeEstimate(proposalDetails.getIncomeEstimate());
        model.setCibilScore(proposalDetails.getCibilScore());
        model.setAccountType(proposalDetails.getAccountType());
        model.setChannelName(proposalDetails.getChannelName());
        model.setKycStatus(proposalDetails.getKycStatus());
        model.setExistingCustomerStatus(proposalDetails.getExistingCustomerStatus());
        model.setPasaFlag(proposalDetails.getPasaFlag());
        model.setPartialPasa(proposalDetails.getPartialPasa());
        model.setPennyDropSuccessful(proposalDetails.getPennyDropSuccessful());
        model.setPartialKyc(proposalDetails.getPartialKyc());
        model.setOcrFlag(proposalDetails.getOcrFlag());
        model.setNetPurchasePrice(proposalDetails.getNetPurchasePrice());
        model.setGrossPurchase(proposalDetails.getGrossPurchase());
        model.setAnnuityAmount(proposalDetails.getAnnuityAmount());
        model.setAnnuityOption(proposalDetails.getAnnuityOption());
        model.setAnnuityType(proposalDetails.getAnnuityType());
        model.setPremiumTerm(proposalDetails.getPremiumTerm());
        model.setAnnuityFrequency(proposalDetails.getAnnuityFrequency());
        model.setCustomerType(proposalDetails.getCustomerType());
        model.setRetention(proposalDetails.getRetention());
        model.setEdcPersistencyFlag(proposalDetails.getEdcPersistencyFlag());
        model.setSisoFlag(proposalDetails.getSisoFlag());
        model.setEnachStatus(proposalDetails.getEnachStatus());
        model.setMedicals(proposalDetails.getMedicals());
        model.setGsipFlag(proposalDetails.getGsipFlag());
        model.setWmmFlag(proposalDetails.getWmmFlag());
        validationRepository.save(model);
    }

    public String generateDocumentViewerUrl(String applicationNumber) {
        return "http://documentviewer.com/view?applicationNumber=" + applicationNumber;
    }

    public boolean checkDocumentVerificationStatus() {
        return validationRepository.isDocumentVerified();
    }

    public List<UnderwriterCommentDTO> fetchComments(String userId, String controlProfile, String contractId) {
        List<UnderwriterCommentModel> comments;
        if (userId.startsWith("P00%") || !"1".equals(controlProfile)) {
            comments = validationRepository.findAllComments(contractId);
        } else {
            comments = validationRepository.findCommentsWithFlagN(contractId);
        }
        List<UnderwriterCommentDTO> commentDTOs = new ArrayList<>();
        for (UnderwriterCommentModel comment : comments) {
            UnderwriterCommentDTO dto = new UnderwriterCommentDTO();
            dto.setUserId(comment.getUserId());
            dto.setCommentDate(comment.getCommentDate());
            dto.setCommentText(comment.getCommentText());
            commentDTOs.add(dto);
        }
        return commentDTOs;
    }

    public void rejectApplications(RejectDTO rejectData) {
        List<String> rejectedApplications = new ArrayList<>();
        for (ApplicationData appData : rejectData.getApplications()) {
            if ("Y".equals(appData.getCh())) {
                if (appData.getReasonLink() == null || appData.getReasonLink().isEmpty() || rejectData.getPackageVariable() == 0) {
                    throw new IllegalArgumentException("Please enter comments.");
                }
                String contractId = validationRepository.findContractId(appData.getApplicationNumber(), "FRP");
                CommentDTO commentDTO = new CommentDTO();
                commentDTO.setEventNo(rejectData.getEventNo());
                commentDTO.setContractId(contractId);
                commentDTO.setPolicyNo(appData.getProposalNo());
                commentDTO.setMoveCode("AZBJ_WEB_OTC");
                commentDTO.setPolicyStatus(rejectData.getPolicyStatus());
                commentDTO.setUserId("USER");
                commentDTO.setCommentDate(new Date());
                commentDTO.setComments(appData.getReasonLink());
                commentDTO.setFlag("N");
                validationRepository.insertComment(commentDTO);
                rejectedApplications.add(appData.getApplicationNumber());
            }
        }
        System.out.println("Rejected Applications: " + rejectedApplications.size() + " - " + rejectedApplications);
    }

    public ValidationDTO validateIFSCCode(String ifscCode) {
        Optional<ValidationModel> validationModel = validationRepository.findByIfscCode(ifscCode);
        if (validationModel.isPresent()) {
            ValidationDTO dto = new ValidationDTO();
            ValidationModel model = validationModel.get();
            dto.setBankName(model.getBankName());
            dto.setBranch(model.getBranch());
            dto.setMicr(model.getMicr());
            return dto;
        }
        return null;
    }

    public boolean validateDocuments() {
        List<Document> requiredDocuments = validationRepository.findRequiredDocuments();
        return requiredDocuments != null && !requiredDocuments.isEmpty();
    }

    public void submitForm() {
        Application application = new Application();
        validationRepository.saveApplication(application);
    }

    public BankDetailsDTO getBankDetails(String ifscCode) {
        BankDetailsModel model = validationRepository.findByIfscCode(ifscCode);
        BankDetailsDTO dto = new BankDetailsDTO();
        dto.setBankName(model.getBankName());
        dto.setBranch(model.getBranch());
        dto.setMicr(model.getMicr());
        return dto;
    }

    public boolean validateAccountDetails(String accountNumber, String ifscCode) {
        return accountNumber != null && !accountNumber.isEmpty() && ifscCode != null && !ifscCode.isEmpty();
    }

    public ValidationResult validatePanCard(PanDetailsDTO panDetails) {
        Optional<PanDetailsModel> panDetailsModel = validationRepository.findPanDetails(panDetails.getPanNumber());
        ValidationResult result = new ValidationResult();
        if (panDetailsModel.isPresent()) {
            result.setValid(true);
            result.setDetails(panDetailsModel.get());
        } else {
            result.setValid(false);
        }
        return result;
    }

    public List<SearchResultModel> searchApplications(SearchCriteriaDTO searchCriteriaDTO) {
        List<SearchResultModel> searchResults = validationRepository.searchApplications(searchCriteriaDTO);
        for (SearchResultModel result : searchResults) {
            int statusCount = validationRepository.countStatus(result.getAppno());
            int biDocCount = validationRepository.countBiDoc(result.getAppno());
            int pfDocCount = validationRepository.countPfDoc(result.getAppno());
            int photoDocCount = validationRepository.countPhotoDoc(result.getAppno());
            int ageProofDocCount = validationRepository.countAgeProofDoc(result.getAppno());
            int addProofDocCount = validationRepository.countAddProofDoc(result.getAppno());
            if (statusCount > 0 && biDocCount > 0 && pfDocCount > 0 && photoDocCount > 0 && ageProofDocCount > 0 && addProofDocCount > 0) {
                result.setDocumentStatus("Documents Received");
            } else if (statusCount > 0) {
                result.setDocumentStatus("Documents Not Received");
            } else {
                result.setDocumentStatus("All Documents Pending");
            }
        }
        return searchResults;
    }

    public void createParamList(Map<String, Object> paramList) {
        if (paramList.containsKey("Param1")) {
            paramList.remove("Param1");
        }
        paramList.put("Param1", new HashMap<String, Object>());
        paramList.put("PAR_PH_PART_ID", "currentPolicyHolderPartId");
        paramList.put("PAR_PAN_CARD_NO", "currentPolicyHolderPanCardNumber");
        paramList.put("PAR_MODULE", "BBU");
        paramList.put("PAR_PAN_PH_NAME", "policyHolderName");
        paramList.put("PAR_PAN_PH_DOB", "policyHolderDob");
    }

    public void callForm(Map<String, Object> paramList) {
        createParamList(paramList);
        // Call the form 'AZBJ_OLD_POLICY_DTLS' with the created parameter list
        // Set a variable indicating that the PAN has been validated to 'Y'
    }

    public boolean checkPanStatus() {
        return validationRepository.checkPanStatus();
    }

    public PanDetailsDTO validatePanCard(String panCardNumber) {
        Optional<PanDetailsModel> panDetailsModel = validationRepository.findPanDetails(panCardNumber);
        if (panDetailsModel.isPresent()) {
            PanDetailsDTO dto = new PanDetailsDTO();
            PanDetailsModel model = panDetailsModel.get();
            dto.setFirstName(model.getFirstName());
            dto.setMiddleName(model.getMiddleName());
            dto.setSurname(model.getSurname());
            dto.setDateOfBirth(model.getDateOfBirth());
            return dto;
        }
        return null;
    }

    public boolean validatePanCardDetails(PanDetailsModel panDetails) {
        boolean isFormatValid = validatePanCardFormat(panDetails.getPanNumber());
        if (!isFormatValid) {
            return false;
        }
        ResponseEntity<?> response = callExternalApi(panDetails);
        return response.getStatusCode().is2xxSuccessful();
    }

    private boolean validatePanCardFormat(String panNumber) {
        return panNumber != null && panNumber.matches("^[A-Z]{3}[C,P,H,F,A,T,B,L,J,G][A-Z][0-9]{4}[A-Z]");
    }

    private ResponseEntity<?> callExternalApi(PanDetailsModel panDetails) {
        // Assuming RestTemplate is configured as a bean
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://externalapi.com/validatePan";
        HttpEntity<PanDetailsModel> request = new HttpEntity<>(panDetails);
        return restTemplate.postForEntity(url, request, Object.class);
    }
}