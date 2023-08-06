package com.QrCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.QrCode.Service.OpentelemetryService;

@SpringBootApplication
public class QrCodeGeneratorApplication implements CommandLineRunner{
	
	
	@Autowired
	private OpentelemetryService opentelemetryService;

	public static void main(String[] args) {
		SpringApplication.run(QrCodeGeneratorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		opentelemetryService.getResponseService();
		System.out.println("VOILD IT'S WORKING");
		
	}

}
