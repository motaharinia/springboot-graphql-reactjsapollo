package com.motaharinia.presentation.generic;

public enum EntityParametersModeEnum {

    //ETC_ITEM
    ETC_ITEM__GENDER("ETC_ITEM__GENDER", EntityEnum.ETC_ITEM),
    ;

    private final String value;
    private final EntityEnum type;

    private EntityParametersModeEnum(String value, EntityEnum type) {
        this.value = value;
        this.type = type;
    }

    public EntityEnum getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
