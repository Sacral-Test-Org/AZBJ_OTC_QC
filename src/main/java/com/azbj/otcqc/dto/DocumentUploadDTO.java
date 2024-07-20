package com.azbj.otcqc.dto;

public class DocumentUploadDTO {
    private String documentName;
    private String documentType;
    private byte[] documentContent;

    public DocumentUploadDTO() {}

    public DocumentUploadDTO(String documentName, String documentType, byte[] documentContent) {
        this.documentName = documentName;
        this.documentType = documentType;
        this.documentContent = documentContent;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public byte[] getDocumentContent() {
        return documentContent;
    }

    public void setDocumentContent(byte[] documentContent) {
        this.documentContent = documentContent;
    }
}