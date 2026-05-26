package com.likelion.likelionpbl.service;

import com.likelion.likelionpbl.repository.MemberRepository;
import com.likelion.likelionpbl.role.Role;
import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("auto")
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public boolean registerMember(Role member) {
        if (memberRepository.existsByName(member.getName())) {
            return false;
        }
        memberRepository.save(member);
        return true;
    }

    public boolean isDuplicateName(String name) {
        return memberRepository.existsByName(name);
    }

    public List<Role> getAllMembers() {
        return memberRepository.findAll();
    }

    public Role findMemberByName(String name) {
        return memberRepository.findByName(name);
    }
}