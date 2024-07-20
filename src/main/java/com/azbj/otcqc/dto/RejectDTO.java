package com.azbj.otcqc.dto;

public class RejectDTO {
    private String applicationNumber;
    private String reasonLink;
    // other relevant fields

    public RejectDTO() {}

    public RejectDTO(String applicationNumber, String reasonLink) {
        this.applicationNumber = applicationNumber;
        this.reasonLink = reasonLink;
    }

    public String getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    public String getReasonLink() {
        return reasonLink;
    }

    public void setReasonLink(String reasonLink) {
        this.reasonLink = reasonLink;
    }

    // other getters and setters for relevant fields
}