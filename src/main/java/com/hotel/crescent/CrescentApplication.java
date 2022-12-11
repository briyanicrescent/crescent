package com.hotel.crescent;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.hotel.crescent"})
@EntityScan("com.hotel.crescent")
@EnableCaching
public class CrescentApplication {

	public static void main(String[] args) throws GeneralSecurityException, IOException {
		SpringApplication.run(CrescentApplication.class, args);
	}

}
