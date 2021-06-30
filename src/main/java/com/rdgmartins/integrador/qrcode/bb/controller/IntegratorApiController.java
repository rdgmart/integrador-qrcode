package com.rdgmartins.integrador.qrcode.bb.controller;

import com.rdgmartins.integrador.qrcode.bb.model.wrapper.GenerateQRCode;
import com.rdgmartins.integrador.qrcode.bb.model.wrapper.GenerateQRCodeAdditionalInformation;
import com.rdgmartins.integrador.qrcode.bb.model.wrapper.ResponseGenerateQRCode;
import com.rdgmartins.integrador.qrcode.bb.model.wrapper.ResponseGenerateToken;
import com.rdgmartins.integrador.qrcode.bb.service.QRCodeService;
import com.rdgmartins.integrador.qrcode.bb.utils.ParamUtils;
import com.rdgmartins.integrador.qrcode.utils.ImageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;

@RestController
@RequestMapping("/api/bb")
@Slf4j
public class IntegratorApiController {

    @Autowired
    QRCodeService service;

    @GetMapping("/token")
    public ResponseGenerateToken criarToken(){
        return service.generateAccessToken();
    }

    @GetMapping("/arrecadacao-qrcodes")
    public String consultarQrCode(@RequestParam Integer numeroConvenio, @RequestParam String codigoGuiaRecebimento){

        log.debug("Numero convenio -->" + numeroConvenio);
        log.debug("codigoGuiaRecebimento -->" + codigoGuiaRecebimento);

        if(numeroConvenio == null || !StringUtils.hasLength(codigoGuiaRecebimento)){
            return "PARAMETROS OBRIGATORIOS: NumeroConvenio e CodigoGuiaRecebimento";
        }

        return service.getInformationQRCode(numeroConvenio, codigoGuiaRecebimento);

    }

    @PostMapping("/arrecadacao-qrcodes")
    public ResponseGenerateQRCode criarQrCode(@RequestBody GenerateQRCode dadosParaQrCode){

        log.debug(dadosParaQrCode != null ? dadosParaQrCode.toString():"Objeto instancia GenerateQRCode vazio");

        // TODO implementacoes de validacao e calculo tempo expiracao

        if(dadosParaQrCode == null
                || dadosParaQrCode.getNumeroConvenio() == null
                || !StringUtils.hasLength(dadosParaQrCode.getCodigoGuiaRecebimento())
                || dadosParaQrCode.getValorOriginalSolicitacao() == null
        ){
            return
                    new ResponseGenerateQRCode("5294493", "Dados informados est\u00E3o inv\u00E1lidos para gera\u00E7\u00E3o do QRCode", LocalDateTime.now().toString());
        }

        if( StringUtils.hasLength(dadosParaQrCode.getDataVencimentoSolicitacao())
                && (dadosParaQrCode.getQuantidadeSegundoExpiracao() == null || dadosParaQrCode.getQuantidadeSegundoExpiracao().equals(0l))){
            String dataVencimentoSolicitacao = dadosParaQrCode.getDataVencimentoSolicitacao().replaceAll("[.]", "/");
            dadosParaQrCode.setQuantidadeSegundoExpiracao(
                    ParamUtils.calcExpirationTimeQrCode(ParamUtils.convertStringToDate(dataVencimentoSolicitacao))
            );
        }

        return service.generateQRCode(dadosParaQrCode);

    }

    @PostMapping("/arrecadacao-qrcodes/img")
    public String generateQRCodeImage(String content){
        byte[] qrcode = ImageUtils.generateQRCodeImage(content);
        long datetime = Instant.now().getEpochSecond();
        return "data:image/png;name=QRCodeImg;filename=QRCodeImg" + datetime + ".png;base64," + Base64.getEncoder().encodeToString(qrcode);
    }

    @GetMapping("/arrecadacao-qrcodes/mock")
    public ResponseGenerateQRCode criarQrCodeTeste(){

        GenerateQRCode dadosParaQrCode = new GenerateQRCode();
        dadosParaQrCode.setNumeroConvenio(62191);
        dadosParaQrCode.setIndicadorCodigoBarras("S");
        dadosParaQrCode.setCodigoGuiaRecebimento("81610000002435544042021113001000021498331823");
        dadosParaQrCode.setEmailDevedor("contribuinte.silva@provedor.com.br");
        dadosParaQrCode.setCodigoPaisTelefoneDevedor(55);
        dadosParaQrCode.setDddTelefoneDevedor(67);
        dadosParaQrCode.setNumeroTelefoneDevedor("999999999");
        dadosParaQrCode.setCodigoSolicitacaoBancoCentralBrasil("88a33759-78b0-43b7-8c60-e5e3e7cb55fe");
        dadosParaQrCode.setDescricaoSolicitacaoPagamento("Arrecadacao PIX");
        dadosParaQrCode.setValorOriginalSolicitacao(new BigDecimal("243.55"));
        dadosParaQrCode.setCpfDevedor("03125194199");
        dadosParaQrCode.setNomeDevedor("Contribuinte da Silva");
        dadosParaQrCode.setQuantidadeSegundoExpiracao(864000l);


        dadosParaQrCode.setListaInformacaoAdicional(new ArrayList<>());
        GenerateQRCodeAdditionalInformation info1 = new GenerateQRCodeAdditionalInformation();
        info1.setCodigoInformacaoAdicional("IPTU");
        info1.setCodigoInformacaoAdicional("COTA ÚNICA 2021");
        dadosParaQrCode.getListaInformacaoAdicional().add(info1);

        return service.generateQRCode(dadosParaQrCode);

    }
}
