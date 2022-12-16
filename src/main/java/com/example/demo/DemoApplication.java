package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootApplication
public class DemoApplication {
	
	@Autowired
    private JavaMailSender emailSender;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	

}
