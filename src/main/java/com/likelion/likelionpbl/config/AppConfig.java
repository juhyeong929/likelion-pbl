package com.likelion.likelionpbl.config;

import com.likelion.likelionpbl.repository.MemberRepository;
import com.likelion.likelionpbl.repository.MemoryMemberRepository;
import com.likelion.likelionpbl.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("manual")
public class AppConfig {

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public MemberService memberService(MemberRepository memberRepository) {
        return new MemberService(memberRepository);
    }
}