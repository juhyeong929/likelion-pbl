package com.likelion.likelionpbl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.likelion.pbl", "com.likelion.likelionpbl"})
public class LikelionPblApplication {

    public static void main(String[] args) {
        SpringApplication.run(LikelionPblApplication.class, args);
    }
}