package edu.hanoi.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("edu.*")
//@ImportResource("classpath:config.xml")
public class SpringClazzApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringClazzApplication.class, args);
	}

}
