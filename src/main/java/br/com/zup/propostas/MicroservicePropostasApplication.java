package br.com.zup.propostas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableFeignClients
@SpringBootApplication
public class MicroservicePropostasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicePropostasApplication.class, args);
	}

}
