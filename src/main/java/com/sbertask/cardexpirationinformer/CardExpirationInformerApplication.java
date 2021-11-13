package com.sbertask.cardexpirationinformer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@SpringBootApplication
@EnableAsync
@EnableScheduling
public class CardExpirationInformerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CardExpirationInformerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //run
    }
}
