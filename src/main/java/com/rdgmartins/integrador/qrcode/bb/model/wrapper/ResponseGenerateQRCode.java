package com.rdgmartins.integrador.qrcode.bb.model.wrapper;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Objects;

@Data
public class ResponseGenerateQRCode extends ResponseBase {

    @SerializedName("timestampCriacaoSolicitacao")
    private String timestampCriacaoSolicitacao = null;

    @SerializedName("estadoSolicitacao")
    private String estadoSolicitacao = null;

    @SerializedName("codigoConciliacaoSolicitante")
    private String codigoConciliacaoSolicitante = null;

    @SerializedName("numeroVersaoSolicitacaoPagamento")
    private Integer numeroVersaoSolicitacaoPagamento = null;

    @SerializedName("linkQrCode")
    private String linkQrCode = null;

    @SerializedName("qrCode")
    private String qrCode = null;

    public ResponseGenerateQRCode(){}

    public ResponseGenerateQRCode(String timestampCriacaoSolicitacao) {
        this.timestampCriacaoSolicitacao = timestampCriacaoSolicitacao;
    }

    public ResponseGenerateQRCode(String error, String errorDescription, String timestampCriacaoSolicitacao) {
        super(error, errorDescription);
        this.timestampCriacaoSolicitacao = timestampCriacaoSolicitacao;
    }

}
