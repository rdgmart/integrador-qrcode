package com.rdgmartins.integrador.qrcode.bb.model.repository;

import com.rdgmartins.integrador.qrcode.Environment;
import com.rdgmartins.integrador.qrcode.bb.model.entity.Param;
import org.springframework.data.repository.CrudRepository;

public interface ParamRepository extends CrudRepository<Param, Long> {

    Param findById(long id);
    Param findByEnv(Environment env);

}
