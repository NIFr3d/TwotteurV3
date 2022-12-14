package com.example.twotteur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
public class TwotteurApplication {
    public static void main(String[] args) {
        SpringApplication.run(TwotteurApplication.class, args);
    }

}
