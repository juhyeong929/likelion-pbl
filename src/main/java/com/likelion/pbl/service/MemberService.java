package com.likelion.pbl.service;

import com.likelion.pbl.repository.MemberRepository;
import com.likelion.pbl.role.Role;
import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("auto")
public class MemberService {

    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public boolean registerMember(Role member) {
        if (repository.existsByName(member.getName())) {
            return false;
        }
        repository.save(member);
        return true;
    }

    public boolean isDuplicateName(String name) {
        return repository.existsByName(name);
    }

    public List<Role> getAllMembers() {
        return repository.findAll();
    }

    public Role findMemberByName(String name) {
        return repository.findByName(name);
    }
}