package com.motaharinia.presentation.adminuserorganizationunit;

import java.io.Serializable;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * کلاس مدل واحد سازمانی ادمین
 */
public class AdminUserOrganizationUnitModel implements Serializable {
    /**
     * شناسه
     */
    private Integer id;

    /**
     * نام
     */
    private String title;

    /**
     * کد
     */
    private String code;

    /**
     * شناسه والد
     */
    private Integer parent_id;

    @Override
    public String toString() {
        return "AdminUserOrganizationUnitModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", code='" + code + '\'' +
                ", parent_id=" + parent_id +
                '}';
    }

    //getter-setter:
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }
}
