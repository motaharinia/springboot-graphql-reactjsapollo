package com.motaharinia.business.service;

import com.motaharinia.msutility.customexception.CustomExceptionKey;

public enum BusinessExceptionEnum implements CustomExceptionKey {
    ID_NOT_FOUND("BUSINESS_EXCEPTION.ID_NOT_FOUND"),
    DUPLICATE_EMAIL("BUSINESS_EXCEPTION.DUPLICATE_EMAIL"),
    AOP_ERROR("BUSINESS_EXCEPTION.AOP_ERROR"),
    INVALID_FILE_KEY("BUSINESS_EXCEPTION.INVALID_FILE_KEY");

    private String messageKey;

    BusinessExceptionEnum(String value) {
        this.messageKey = value;
    }

    public String getValue() {
        return messageKey;
    }

}
