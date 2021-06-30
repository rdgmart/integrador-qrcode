package com.rdgmartins.integrador.qrcode;

import com.rdgmartins.integrador.qrcode.bb.model.entity.Param;
import com.rdgmartins.integrador.qrcode.bb.model.repository.ParamRepository;
import com.rdgmartins.integrador.qrcode.bb.service.QRCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class IntegradorQrcodeApplication implements CommandLineRunner {

	@Autowired
	QRCodeService service;

	public static void main(String[] args) {

		for(String arg:args) {
			System.out.println(arg);
		}

		SpringApplication.run(IntegradorQrcodeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String environment = args != null && args.length > 0 ? args[0]:"D";
		log.info("ENVIROMENT --> " + environment);


	}

	@Bean
	public CommandLineRunner initialize(ParamRepository repository){
		return (args)-> {
			Param sandbox = repository.findByEnv(Environment.DEVELOPMENT);
			log.debug(sandbox != null ? sandbox.toString(): "NAO CONSEGUIU CARREGAR AS CONFIGURACOES");
		};
	}

	// no terminal execute: ./gradlew bootRun
	/*
		Para criar o jar executavel, no terminal execute:
			> ./gradlew bootBuildImage

		endpoints:
			> localhost:8080/api/bb/token
			> localhost:8080/api/bb/arrecadacao-qrcodes
			> localhost:8080/api/bb/arrecadacao-qrcodes/mock

		Banco:
			http://localhost:8080/h2-console
	 */
}
