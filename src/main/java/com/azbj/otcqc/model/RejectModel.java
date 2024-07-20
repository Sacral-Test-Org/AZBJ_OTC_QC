package com.azbj.otcqc.model;

public class RejectModel {
    private String applicationNumber;
    private String reasonLink;
    private String ch;
    private String proposalNo;

    // Getters and Setters
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

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }

    public String getProposalNo() {
        return proposalNo;
    }

    public void setProposalNo(String proposalNo) {
        this.proposalNo = proposalNo;
    }
}