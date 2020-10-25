package com.motaharinia.presentation.adminuser;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Task {

    private String code;
    private String description;


    @JsonCreator
    public Task(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
