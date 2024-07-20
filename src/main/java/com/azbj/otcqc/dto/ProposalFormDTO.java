package com.azbj.otcqc.dto;

public class ProposalFormDTO {
    private String applicationNumber;
    private String documentViewerUrl;

    public ProposalFormDTO() {}

    public ProposalFormDTO(String applicationNumber, String documentViewerUrl) {
        this.applicationNumber = applicationNumber;
        this.documentViewerUrl = documentViewerUrl;
    }

    public String getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    public String getDocumentViewerUrl() {
        return documentViewerUrl;
    }

    public void setDocumentViewerUrl(String documentViewerUrl) {
        this.documentViewerUrl = documentViewerUrl;
    }
}
