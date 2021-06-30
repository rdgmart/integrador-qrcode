package com.rdgmartins.integrador.qrcode.bb.model.wrapper;

import com.google.gson.annotations.SerializedName;
import org.springframework.util.StringUtils;

import java.io.Serializable;


public class ResponseBase implements Serializable{

    @SerializedName("error")
    private String error;

    @SerializedName("error_description")
    private String errorDescription;

    public ResponseBase() {}

    public ResponseBase(String error, String errorDescription) {
        this.error = error;
        this.errorDescription = errorDescription;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public boolean hasError() {
        return StringUtils.hasLength(this.error);
    }

    public static ResponseBase getResponseError(String error, String errorDescription) {
        return new ResponseBase(error, errorDescription);
    }
}
