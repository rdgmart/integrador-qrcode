package com.rdgmartins.integrador.qrcode.bb.model.wrapper;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ResponseGenerateToken extends ResponseBase {
    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("token_type")
    private String tokenType;

    @SerializedName("expires_in")
    private String expiresIn;

}
