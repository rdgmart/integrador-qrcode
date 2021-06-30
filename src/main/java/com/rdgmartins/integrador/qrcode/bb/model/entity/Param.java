package com.rdgmartins.integrador.qrcode.bb.model.entity;

import com.rdgmartins.integrador.qrcode.Environment;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "TBLBBPARAM")
@Data
public class Param implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String developerApplicationKey;
    @Column(length = 2048)
    private String clientId;
    @Column(length = 2048)
    private String clientSecret;
    @Column(length = 2048)
    private String registrationAcessToken;
    @Enumerated(EnumType.STRING)
    private Environment env;
    @Column(length = 2048)
    private String accessToken;
    private Date accessTokenDate;

    public Param() {}
}
