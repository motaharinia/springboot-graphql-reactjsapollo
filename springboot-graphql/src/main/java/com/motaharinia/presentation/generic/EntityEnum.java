package com.motaharinia.presentation.generic;

public enum EntityEnum {


    User("User"),
    //-------------------------------
    ETC_ITEM("ETC_ITEM"),
    ;

    private final String value;

    private EntityEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
