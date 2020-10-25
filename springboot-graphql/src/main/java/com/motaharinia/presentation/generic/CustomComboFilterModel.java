package com.motaharinia.presentation.generic;

import com.motaharinia.msutility.genericmodel.ComboTypeEnum;

import java.util.HashMap;

public class CustomComboFilterModel {

    //@Required
    private EntityParametersModeEnum mode;

    //todo=>[فعلا استفاده نمیشود.]
    private EntityEnum entity;

    /**
     * جستجو
     */
    private String input;

    /**
     * داده های کمکی
     */
    private HashMap<String, Object> extMap = new HashMap<>();

    private Integer page = 0;
    private Integer rows = 1;

    private ComboTypeEnum type;

    //=========================================//
    public EntityParametersModeEnum getMode() {
        return mode;
    }

    public void setMode(EntityParametersModeEnum mode) {
        this.mode = mode;
    }

    public EntityEnum getEntity() {
        return entity;
    }

    public void setEntity(EntityEnum entity) {
        this.entity = entity;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public HashMap<String, Object> getExtMap() {
        return extMap;
    }

    public void setExtMap(HashMap<String, Object> extMap) {
        this.extMap = extMap;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public ComboTypeEnum getType() {
        return type;
    }

    public void setType(ComboTypeEnum type) {
        this.type = type;
    }
}
