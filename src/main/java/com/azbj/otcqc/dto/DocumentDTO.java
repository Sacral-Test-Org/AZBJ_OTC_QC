package com.azbj.otcqc.dto;

public class DocumentDTO {
    private String documentPath;
    private String documentType;

    public DocumentDTO() {}

    public DocumentDTO(String documentPath, String documentType) {
        this.documentPath = documentPath;
        this.documentType = documentType;
    }

    public String getDocumentPath() {
        return documentPath;
    }

    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }
}
