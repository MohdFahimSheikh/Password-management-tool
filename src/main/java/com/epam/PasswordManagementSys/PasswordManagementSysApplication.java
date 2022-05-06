package com.epam.PasswordManagementSys;

import com.epam.PasswordManagementSys.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
	public class PasswordManagementSysApplication implements CommandLineRunner
{

	public static void main(String[] args) {
		SpringApplication.run(PasswordManagementSysApplication.class, args);
	}
@Autowired
	AccountRepository accountRepository;
	@Override
	public void run(String... args) throws Exception {
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
