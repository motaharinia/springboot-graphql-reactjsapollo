package com.motaharinia.business.service.adminuser;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-16<br>
 * Time: 23:09:56<br>
 * Description:<br>
 *     مقادیر ثابت نوع جستجو<br>
 * مقادیر ثابت نوع جستجوی ماژولهای دیگر از این کلاس اکستند میشوند
 */
public enum AdminUserSearchViewTypeEnum {
    /**
     *نوع جستجوی عمومی<br>
     */
    ADMIN_USER_BRIEF(AdminUserSearchViewTypeBrief.class.getName()),
    ;

    private final String value;

    AdminUserSearchViewTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return super.toString();
    }
}
