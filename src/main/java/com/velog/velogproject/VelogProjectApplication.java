package com.velog.velogproject;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

@SpringBootApplication
@OpenAPIDefinition(servers = {
		@Server(url = "https://belog.diligentp.com", description = "Belog Server")
})
public class VelogProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(VelogProjectApplication.class, args);
	}

}
