package com.rdgmartins.integrador.qrcode.bb.utils;

public interface PropsConfigUtils {

    public static final String HOST_API_P = "https://api.bb.com.br";
    public static final String HOST_OAUTH_P = "https://oauth.bb.com.br/oauth";

    public static final String HOST_API = "https://api.sandbox.bb.com.br";
    public static final String HOST_OAUTH = "https://oauth.sandbox.bb.com.br/oauth";

    public static final Integer ACCESS_TOKEN_EXPIRE_IN = 580 * 1000; // No BB diz 600 segundos, mas tiro 20 para garantir sempre token valido
}
