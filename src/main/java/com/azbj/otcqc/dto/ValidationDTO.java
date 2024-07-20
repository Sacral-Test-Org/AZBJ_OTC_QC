package com.azbj.otcqc.dto;

import java.util.Date;

public class ValidationDTO {
    // Fields for validation data
    private String applicationNo;
    private boolean otcEligible;
    private boolean spgCase;
    private boolean kycFlag;
    private String message;

    // Fields for document received status
    private String docReceivedStatus;

    // Fields for proposal details
    private String proposalNumber;
    private String lifeAssuredName;
    private String applicationNumber;
    private String policyHolderName;
    private String comments;
    private String referToSupervisor;
    private String trlScore;
    private String incomeEstimate;
    private String cibilScore;
    private String accountType;
    private String channelName;
    private String kycStatus;
    private String existingCustomerStatus;
    private String pasaFlag;
    private String partialPasa;
    private String pennyDropSuccessful;
    private String partialKyc;
    private String ocrFlag;
    private String netPurchasePrice;
    private String grossPurchase;
    private String annuityAmount;
    private String annuityOption;
    private String annuityType;
    private String premiumTerm;
    private String annuityFrequency;
    private String customerType;
    private String retention;
    private String edcPersistencyFlag;
    private String sisoFlag;
    private String enachStatus;
    private String medicals;
    private String gsipFlag;
    private String wmmFlag;

    // Field for document verification
    private boolean documentVerified;

    // Fields for document details
    private String documentType;
    private String documentStatus;

    // Fields for bank details
    private String ifscCode;
    private String bankName;
    private String bankBranch;
    private String micrCode;

    // Fields for PAN details
    private String panCardNumber;
    private String firstName;
    private String middleName;
    private String surname;
    private Date dateOfBirth;

    // Getters and Setters
    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public boolean isOtcEligible() {
        return otcEligible;
    }

    public void setOtcEligible(boolean otcEligible) {
        this.otcEligible = otcEligible;
    }

    public boolean isSpgCase() {
        return spgCase;
    }

    public void setSpgCase(boolean spgCase) {
        this.spgCase = spgCase;
    }

    public boolean isKycFlag() {
        return kycFlag;
    }

    public void setKycFlag(boolean kycFlag) {
        this.kycFlag = kycFlag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDocReceivedStatus() {
        return docReceivedStatus;
    }

    public void setDocReceivedStatus(String docReceivedStatus) {
        this.docReceivedStatus = docReceivedStatus;
    }

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public String getLifeAssuredName() {
        return lifeAssuredName;
    }

    public void setLifeAssuredName(String lifeAssuredName) {
        this.lifeAssuredName = lifeAssuredName;
    }

    public String getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    public String getPolicyHolderName() {
        return policyHolderName;
    }

    public void setPolicyHolderName(String policyHolderName) {
        this.policyHolderName = policyHolderName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getReferToSupervisor() {
        return referToSupervisor;
    }

    public void setReferToSupervisor(String referToSupervisor) {
        this.referToSupervisor = referToSupervisor;
    }

    public String getTrlScore() {
        return trlScore;
    }

    public void setTrlScore(String trlScore) {
        this.trlScore = trlScore;
    }

    public String getIncomeEstimate() {
        return incomeEstimate;
    }

    public void setIncomeEstimate(String incomeEstimate) {
        this.incomeEstimate = incomeEstimate;
    }

    public String getCibilScore() {
        return cibilScore;
    }

    public void setCibilScore(String cibilScore) {
        this.cibilScore = cibilScore;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getKycStatus() {
        return kycStatus;
    }

    public void setKycStatus(String kycStatus) {
        this.kycStatus = kycStatus;
    }

    public String getExistingCustomerStatus() {
        return existingCustomerStatus;
    }

    public void setExistingCustomerStatus(String existingCustomerStatus) {
        this.existingCustomerStatus = existingCustomerStatus;
    }

    public String getPasaFlag() {
        return pasaFlag;
    }

    public void setPasaFlag(String pasaFlag) {
        this.pasaFlag = pasaFlag;
    }

    public String getPartialPasa() {
        return partialPasa;
    }

    public void setPartialPasa(String partialPasa) {
        this.partialPasa = partialPasa;
    }

    public String getPennyDropSuccessful() {
        return pennyDropSuccessful;
    }

    public void setPennyDropSuccessful(String pennyDropSuccessful) {
        this.pennyDropSuccessful = pennyDropSuccessful;
    }

    public String getPartialKyc() {
        return partialKyc;
    }

    public void setPartialKyc(String partialKyc) {
        this.partialKyc = partialKyc;
    }

    public String getOcrFlag() {
        return ocrFlag;
    }

    public void setOcrFlag(String ocrFlag) {
        this.ocrFlag = ocrFlag;
    }

    public String getNetPurchasePrice() {
        return netPurchasePrice;
    }

    public void setNetPurchasePrice(String netPurchasePrice) {
        this.netPurchasePrice = netPurchasePrice;
    }

    public String getGrossPurchase() {
        return grossPurchase;
    }

    public void setGrossPurchase(String grossPurchase) {
        this.grossPurchase = grossPurchase;
    }

    public String getAnnuityAmount() {
        return annuityAmount;
    }

    public void setAnnuityAmount(String annuityAmount) {
        this.annuityAmount = annuityAmount;
    }

    public String getAnnuityOption() {
        return annuityOption;
    }

    public void setAnnuityOption(String annuityOption) {
        this.annuityOption = annuityOption;
    }

    public String getAnnuityType() {
        return annuityType;
    }

    public void setAnnuityType(String annuityType) {
        this.annuityType = annuityType;
    }

    public String getPremiumTerm() {
        return premiumTerm;
    }

    public void setPremiumTerm(String premiumTerm) {
        this.premiumTerm = premiumTerm;
    }

    public String getAnnuityFrequency() {
        return annuityFrequency;
    }

    public void setAnnuityFrequency(String annuityFrequency) {
        this.annuityFrequency = annuityFrequency;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getRetention() {
        return retention;
    }

    public void setRetention(String retention) {
        this.retention = retention;
    }

    public String getEdcPersistencyFlag() {
        return edcPersistencyFlag;
    }

    public void setEdcPersistencyFlag(String edcPersistencyFlag) {
        this.edcPersistencyFlag = edcPersistencyFlag;
    }

    public String getSisoFlag() {
        return sisoFlag;
    }

    public void setSisoFlag(String sisoFlag) {
        this.sisoFlag = sisoFlag;
    }

    public String getEnachStatus() {
        return enachStatus;
    }

    public void setEnachStatus(String enachStatus) {
        this.enachStatus = enachStatus;
    }

    public String getMedicals() {
        return medicals;
    }

    public void setMedicals(String medicals) {
        this.medicals = medicals;
    }

    public String getGsipFlag() {
        return gsipFlag;
    }

    public void setGsipFlag(String gsipFlag) {
        this.gsipFlag = gsipFlag;
    }

    public String getWmmFlag() {
        return wmmFlag;
    }

    public void setWmmFlag(String wmmFlag) {
        this.wmmFlag = wmmFlag;
    }

    public boolean isDocumentVerified() {
        return documentVerified;
    }

    public void setDocumentVerified(boolean documentVerified) {
        this.documentVerified = documentVerified;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentStatus() {
        return documentStatus;
    }

    public void setDocumentStatus(String documentStatus) {
        this.documentStatus = documentStatus;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getMicrCode() {
        return micrCode;
    }

    public void setMicrCode(String micrCode) {
        this.micrCode = micrCode;
    }

    public String getPanCardNumber() {
        return panCardNumber;
    }

    public void setPanCardNumber(String panCardNumber) {
        this.panCardNumber = panCardNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}