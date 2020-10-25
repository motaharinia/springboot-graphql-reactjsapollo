/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.motaharinia.presentation.loguploadedfile.frontuploader;

/**
 *
 * @author Administrator
 */
public class FineUploaderResponseModel {

    private Boolean success;
    private String error;
    private Boolean preventRetry;

    //getter-setter:
    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Boolean getPreventRetry() {
        return preventRetry;
    }

    public void setPreventRetry(Boolean preventRetry) {
        this.preventRetry = preventRetry;
    }

}
