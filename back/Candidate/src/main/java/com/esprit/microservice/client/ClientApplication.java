package com.esprit.microservice.client;

import java.util.stream.Stream;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan;




@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@ComponentScan(basePackages="com.esprit.microservice.client")

public class ClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);

	}
	
	@Bean
    ApplicationRunner init(ClientRepository repository) {
        return args -> {
            Stream.of("manoubia", "Sarra", "Mohamed").forEach(nom -> {
            	repository.save(new Client(nom));
                
            });
            repository.findAll().forEach(System.out::println);
        };
    }

}


