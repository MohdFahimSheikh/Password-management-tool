package com.example.PasswordManagementSys;

import com.example.PasswordManagementSys.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PasswordManagementSysApplication implements CommandLineRunner
{

	public static void main(String[] args) {
		SpringApplication.run(PasswordManagementSysApplication.class, args);
		System.out.println("fgj");
	}
@Autowired
	AccountRepository accountRepository;
	@Override
	public void run(String... args) throws Exception {
//		Account account =new Account("fahim","fdsd","fefgrger:/","sdfs",new Date(),new Date());
	//	accountRepository.save(account);
	}
}
