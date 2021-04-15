package com.fullth.tic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.fullth.tic")
public class TICProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(TICProjectApplication.class, args);
	}

}
