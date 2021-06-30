package com.rdgmartins.integrador.qrcode.bb.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rdgmartins.integrador.qrcode.bb.model.wrapper.GenerateQRCode;
import com.rdgmartins.integrador.qrcode.bb.model.wrapper.ResponseGenerateQRCode;
import com.rdgmartins.integrador.qrcode.bb.model.wrapper.ResponseGenerateToken;
import com.rdgmartins.integrador.qrcode.bb.utils.PropsConfigUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.UriComponentsBuilder;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Slf4j
public class QRCodeService {

    private String accessToken;
    private String developerApplicationKey;
    private String registrationAcessToken;

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setDeveloperApplicationKey(String developerApplicationKey) {
        this.developerApplicationKey = developerApplicationKey;
    }

    public void setRegistrationAcessToken(String registrationAcessToken) {
        this.registrationAcessToken = registrationAcessToken;
    }

    public String getInformationQRCode(Integer numeroConvenio, String codigoDeBarras){

        RestTemplate restTemplate = new RestTemplate(generateSSLRequestFactory());

        MultiValueMap<String, String> headers= new LinkedMultiValueMap<>();
        headers.add("accept", "application/json");
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Bearer " + accessToken);

        MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("gw-dev-app-key", developerApplicationKey);
        requestBody.add("numeroConvenio", numeroConvenio);
        requestBody.add("codigoGuiaRecebimento", codigoDeBarras);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(PropsConfigUtils.HOST_API + "/pix-bb/v1/arrecadacao-qrcodes");
        for (Map.Entry<String, List<Object>> entry : requestBody.entrySet()) {
            builder.queryParam(entry.getKey(), entry.getValue());
        }

        HttpEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity(headers), String.class);

        log.debug(response != null ? response.getBody(): "nada");

        return response != null ? response.getBody(): null;
    }

    public ResponseGenerateQRCode generateQRCode(GenerateQRCode inputForQRCode){

        RestTemplate restTemplate = new RestTemplate(generateSSLRequestFactory());

        MultiValueMap<String, String> headers= new LinkedMultiValueMap<>();
        headers.add("accept", "application/json");
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Bearer " + accessToken);

        HttpEntity<GenerateQRCode> request = new HttpEntity<>(inputForQRCode, headers);
        ResponseGenerateQRCode response = restTemplate.postForObject(
                PropsConfigUtils.HOST_API + "/pix-bb/v1/arrecadacao-qrcodes?gw-dev-app-key=" + developerApplicationKey, request, ResponseGenerateQRCode.class);

        log.debug(response != null ? response.toString(): "nada");

        return response;
    }

    public ResponseGenerateToken generateAccessToken(){

        RestTemplate restTemplate = new RestTemplate(generateSSLRequestFactory());

        MultiValueMap<String, String> headers= new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        headers.add("Authorization", "Basic " + registrationAcessToken);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<String, String>();
        requestBody.add("grant_type", "client_credentials");
        requestBody.add("scope", "pix.arrecadacao-requisicao pix.arrecadacao-info");

        HttpEntity request = new HttpEntity<MultiValueMap<String, String>>(requestBody, headers);
        String response = restTemplate.postForObject(
                PropsConfigUtils.HOST_OAUTH + "/token", request, String.class);

        log.debug(response);

        if(StringUtils.hasLength(response)){
            Gson gson = new Gson();
            JsonObject object = JsonParser.parseString(response).getAsJsonObject();// response will be the json String
            return gson.fromJson(object, ResponseGenerateToken.class);
        }

        return null;
    }

    private HttpComponentsClientHttpRequestFactory generateSSLRequestFactory() {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        SSLContext sslContext = null;
        try {
            sslContext = org.apache.http.ssl.SSLContexts.custom()
                    .loadTrustMaterial(null, acceptingTrustStrategy)
                    .build();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf)
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        requestFactory.setHttpClient(httpClient);
        return requestFactory;
    }

}
