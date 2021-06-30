package com.rdgmartins.integrador.qrcode.bb.model.wrapper;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class GenerateQRCodeAdditionalInformation {

    @SerializedName("codigoInformacaoAdicional")
    private String codigoInformacaoAdicional = null;

    @SerializedName("textoInformacaoAdicional")
    private String textoInformacaoAdicional = null;
}
