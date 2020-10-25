package com.motaharinia.config.graphql;

public class FileUpload {
    private String contentType;
    private byte[] content;

    public FileUpload(String contentType, byte[] content) {
        this.contentType = contentType;
        this.content = content;
    }

    public String getContentType() {
        return contentType;
    }

    public byte[] getContent() {
        return content;
    }
}
