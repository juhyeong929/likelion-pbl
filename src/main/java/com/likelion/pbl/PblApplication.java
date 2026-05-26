package com.likelion.pbl;

import com.likelion.pbl.service.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PblApplication {

    public static void main(String[] args) {
        SpringApplication.run(PblApplication.class, args);
    }

    @Bean
    CommandLineRunner beanCheck(ApplicationContext applicationContext) {
        return args -> {
            MemberService memberService = applicationContext.getBean(MemberService.class);
            System.out.println("MemberService bean: " + memberService.getClass().getName());
        };
    }
}