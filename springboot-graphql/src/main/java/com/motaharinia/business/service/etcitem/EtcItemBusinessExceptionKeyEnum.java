package com.motaharinia.business.service.etcitem;


import com.motaharinia.msutility.customexception.CustomExceptionKey;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-11<br>
 * Time: 01:22:13<br>
 * Description:<br>
 *     مقادیر ثابت کلید اکسپشنهای یوتیلیتی که در کلاینت ساید پروژه ترجمه آنها طبق زبان انتخاب شده کاربر نمایش داده خواهد شد
 */
public enum EtcItemBusinessExceptionKeyEnum implements CustomExceptionKey {


    /**
     * نوع مقدار ثابت جستجو شده با مقدار ثابت ورودی متد یکسان نمیباشد
     */
    ETC_ITEM_TYPE_NOT_MATCH("ETC_ITEM_TYPE_NOT_MATCH"),
    /**
     *
     */
    ETC_ITEM_TYPETAG_NOT_MATCH("ETC_ITEM_TYPETAG_NOT_MATCH"),

;

    private final String value;
    private final String moduleName = "test";

    EtcItemBusinessExceptionKeyEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return moduleName + "." + value;
    }

    public String toString() {
        return super.toString();
    }
}
