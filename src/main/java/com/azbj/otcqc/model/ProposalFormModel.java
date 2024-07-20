package com.azbj.otcqc.model;

public class ProposalFormModel {
    private String applicationNumber;
    private String documentViewerUrl;

    public ProposalFormModel() {}

    public ProposalFormModel(String applicationNumber, String documentViewerUrl) {
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
