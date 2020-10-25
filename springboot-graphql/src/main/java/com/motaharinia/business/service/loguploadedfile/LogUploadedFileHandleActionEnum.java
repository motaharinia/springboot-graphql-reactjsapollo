package com.motaharinia.business.service.loguploadedfile;


public enum LogUploadedFileHandleActionEnum {
    ENTITY_CREATE("ENTITY_CREATE"),
    ENTITY_UPDATE("ENTITY_UPDATE"),
    ENTITY_DELETE("ENTITY_DELETE");

    private final String value;

    private LogUploadedFileHandleActionEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
