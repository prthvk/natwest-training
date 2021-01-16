package com.stackroute.employeemongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class EmployeemongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeemongoApplication.class, args);
	}

}
