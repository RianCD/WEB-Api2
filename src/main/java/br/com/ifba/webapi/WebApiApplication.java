package br.com.ifba.webapi;

import br.com.ifba.webapi.client.SpringClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApiApplication.class, args);

		SpringClient.main(args);//utilizado para rodar o SpringClient
	}

}
