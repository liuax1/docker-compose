package com.example.docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.io.PrintStream;

//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		try{
			System.setOut(new PrintStream(System.out, true, "UTF-8"));
		}catch (Exception e){
			e.printStackTrace();
		}
		SpringApplication.run(DemoApplication.class, args);
	}

}
