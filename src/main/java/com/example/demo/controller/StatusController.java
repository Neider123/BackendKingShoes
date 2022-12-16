package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {
	
	@GetMapping("/healthcheck")
	public String status() {
		return "OK";
	}
	
	@GetMapping("/error")
	public String error() {
		return "UPPPSSS, la cagaste";
	}
}