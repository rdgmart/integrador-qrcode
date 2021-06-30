package com.rdgmartins.integrador.qrcode;

import com.rdgmartins.integrador.qrcode.bb.interceptor.TokenInterceptor;
import com.rdgmartins.integrador.qrcode.bb.model.repository.ParamRepository;
import com.rdgmartins.integrador.qrcode.bb.service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Autowired
    ParamRepository repository;

    @Autowired
    QRCodeService service;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenInterceptor(repository, service)).addPathPatterns("/**");
    }
}
