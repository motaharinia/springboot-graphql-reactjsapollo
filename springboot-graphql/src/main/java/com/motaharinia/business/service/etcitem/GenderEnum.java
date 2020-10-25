package com.motaharinia.business.service.etcitem;

import com.motaharinia.persistence.orm.etcitem.EtcItem;
import org.springframework.util.ObjectUtils;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-11<br>
 * Time: 01:22:13<br>
 * Description:<br>
 *     مقادیر ثابت EtcItem جنسیت
 */
public enum GenderEnum implements EtcItemEnum {

    /**
     * جنسیت مرد
     */
    MALE("MALE"),
    /**
     * جنسیت زن
     */
    FEMALE("FEMALE");

    private final String value;

    GenderEnum(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public Class getEntityClass() {
        return EtcItem.class;
    }

    @Override
    public String getType() {
        String type = this.getClass().getSimpleName().replace("Enum", "");
        type = type.substring(0, 1).toLowerCase() + type.substring(1);
        return type;
    }

//    public static String getType2() {
//        String type = EtcItemEnum.getEtcClassName().replace("Enum", "");
//        type = type.substring(0, 1).toLowerCase() + type.substring(1);
//        return type;
//    }



    @Override
    public String toString() {
        return super.toString();
    }




}
