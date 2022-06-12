package com.example.myweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude= {DataSourceAutoConfiguration.class})
public class MywebApplication {

	public static void main(String[] args) {
		SpringApplication.run(MywebApplication.class, args);
		System.out.println("웹 실행 완료!");
	}

}
