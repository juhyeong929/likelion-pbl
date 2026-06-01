package com.likelion.pbl.service;

import com.likelion.pbl.dto.MemberCreateRequest;
import com.likelion.pbl.dto.MemberUpdateRequest;
import com.likelion.pbl.repository.MemberRepository;
import com.likelion.pbl.role.Lion;
import com.likelion.pbl.role.Role;
import com.likelion.pbl.role.Staff;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public boolean registerMember(Role member) {
        repository.save(member);
        return true;
    }

    public Role createMember(MemberCreateRequest request) {
        Role member;
        if ("LION".equalsIgnoreCase(request.roleType())) {
            member = new Lion(
                    request.name(),
                    request.major(),
                    request.generation(),
                    request.part(),
                    request.studentId()
            );
        } else if ("STAFF".equalsIgnoreCase(request.roleType())) {
            member = new Staff(
                    request.name(),
                    request.major(),
                    request.generation(),
                    request.part(),
                    request.position()
            );
        } else {
            return null; // Invalid role type
        }
        
        repository.save(member);
        return member;
    }

    public Role updateMember(Long id, MemberUpdateRequest request) {
        Role existingMember = repository.findById(id);
        if (existingMember == null) {
            return null;
        }

        Role updatedMember;
        if (existingMember instanceof Lion) {
            updatedMember = new Lion(
                    request.name(),
                    request.major(),
                    request.generation(),
                    request.part(),
                    request.studentId()
            );
        } else if (existingMember instanceof Staff) {
            updatedMember = new Staff(
                    request.name(),
                    request.major(),
                    request.generation(),
                    request.part(),
                    request.position()
            );
        } else {
            return null;
        }

        repository.updateById(id, updatedMember);
        return repository.findById(id);
    }

    public boolean deleteMember(Long id) {
        return repository.deleteById(id);
    }

    public List<Role> getAllMembers() {
        return repository.findAll();
    }

    public Role findMemberById(Long id) {
        return repository.findById(id);
    }
}