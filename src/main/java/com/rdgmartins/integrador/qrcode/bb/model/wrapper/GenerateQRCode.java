package com.rdgmartins.integrador.qrcode.bb.model.wrapper;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class GenerateQRCode {

    @SerializedName("numeroConvenio")
    private Integer numeroConvenio;

    @SerializedName("indicadorCodigoBarras")
    private String indicadorCodigoBarras;

    @SerializedName("codigoGuiaRecebimento")
    private String codigoGuiaRecebimento;

    @SerializedName("emailDevedor")
    private String emailDevedor;

    @SerializedName("codigoPaisTelefoneDevedor")
    private Integer codigoPaisTelefoneDevedor;

    @SerializedName("dddTelefoneDevedor")
    private Integer dddTelefoneDevedor;

    @SerializedName("numeroTelefoneDevedor")
    private String numeroTelefoneDevedor;

    @SerializedName("codigoSolicitacaoBancoCentralBrasil")
    private String codigoSolicitacaoBancoCentralBrasil;

    @SerializedName("descricaoSolicitacaoPagamento")
    private String descricaoSolicitacaoPagamento;

    @SerializedName("valorOriginalSolicitacao")
    private BigDecimal valorOriginalSolicitacao;

    @SerializedName("valorDescontoSolicitacao")
    private BigDecimal valorDescontoSolicitacao;

    @SerializedName("valorJurosSolicitacao")
    private BigDecimal valorJurosSolicitacao;

    @SerializedName("valorMultaSolicitacao")
    private BigDecimal valorMultaSolicitacao;

    @SerializedName("cpfDevedor")
    private String cpfDevedor;

    @SerializedName("cnpjDevedor")
    private String cnpjDevedor;

    @SerializedName("nomeDevedor")
    private String nomeDevedor;

    @SerializedName("quantidadeSegundoExpiracao")
    private Long quantidadeSegundoExpiracao;

    @SerializedName("dataVencimentoSolicitacao")
    private String dataVencimentoSolicitacao;

    @SerializedName("quantidadeInformacaoAdicional")
    private Integer quantidadeInformacaoAdicional;

    @SerializedName("listaInformacaoAdicional")
    private List<GenerateQRCodeAdditionalInformation> listaInformacaoAdicional;

}
