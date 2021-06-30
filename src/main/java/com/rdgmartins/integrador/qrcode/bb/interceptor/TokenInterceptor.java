package com.rdgmartins.integrador.qrcode.bb.interceptor;

import com.rdgmartins.integrador.qrcode.Environment;
import com.rdgmartins.integrador.qrcode.bb.model.entity.Param;
import com.rdgmartins.integrador.qrcode.bb.model.repository.ParamRepository;
import com.rdgmartins.integrador.qrcode.bb.model.wrapper.ResponseGenerateToken;
import com.rdgmartins.integrador.qrcode.bb.service.QRCodeService;
import com.rdgmartins.integrador.qrcode.bb.utils.ParamUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class TokenInterceptor implements HandlerInterceptor {

    private final ParamRepository repository;
    private final QRCodeService service;

    public TokenInterceptor(ParamRepository repository, QRCodeService service) {
        this.repository = repository;
        this.service = service;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Param param = repository.findByEnv(Environment.DEVELOPMENT);

        service.setDeveloperApplicationKey(param.getDeveloperApplicationKey());
        service.setRegistrationAcessToken(param.getRegistrationAcessToken());

        if(ParamUtils.isExpired(param.getAccessTokenDate())){
            ResponseGenerateToken responseGenerateToken = service.generateAccessToken();
            if(responseGenerateToken != null && !responseGenerateToken.hasError()){
                param.setAccessTokenDate(new Date());
                param.setAccessToken(responseGenerateToken.getAccessToken());
                repository.save(param);
            }
        }
        service.setAccessToken(param.getAccessToken());

        return true;
    }
}
