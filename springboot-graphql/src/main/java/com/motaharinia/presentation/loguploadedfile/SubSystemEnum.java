package com.motaharinia.presentation.loguploadedfile;


public enum SubSystemEnum {

    COMMON("COMMON"),
    ESHOP("ESHOP"),
    WWW("WWW"),
    SUPPORT("SUPPORT");

    private String value;

    private SubSystemEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
