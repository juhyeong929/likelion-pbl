package com.likelion.likelionpbl.service;

import com.likelion.likelionpbl.dto.LionCreateRequest;
import com.likelion.likelionpbl.dto.LionUpdateRequest;
import com.likelion.likelionpbl.dto.StaffCreateRequest;
import com.likelion.likelionpbl.dto.StaffUpdateRequest;
import com.likelion.likelionpbl.repository.MemberRepository;
import com.likelion.likelionpbl.role.Lion;
import com.likelion.likelionpbl.role.Role;
import com.likelion.likelionpbl.role.Staff;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
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

    public Lion createLion(LionCreateRequest request) {
        if (memberRepository.existsByName(request.name())) {
            return null;
        }

        Lion lion = new Lion(
                request.name(),
                request.major(),
                request.generation(),
                request.part(),
                request.studentId()
        );
        memberRepository.save(lion);
        return lion;
    }

    public Staff createStaff(StaffCreateRequest request) {
        if (memberRepository.existsByName(request.name())) {
            return null;
        }

        Staff staff = new Staff(
                request.name(),
                request.major(),
                request.generation(),
                request.part(),
                request.position()
        );
        memberRepository.save(staff);
        return staff;
    }

    public Lion updateLion(String name, LionUpdateRequest request) {
        Role existingMember = memberRepository.findByName(name);
        if (!(existingMember instanceof Lion)) {
            return null;
        }

        Lion updatedLion = new Lion(
                name,
                request.major(),
                request.generation(),
                request.part(),
                request.studentId()
        );
        memberRepository.updateByName(name, updatedLion);
        return updatedLion;
    }

    public Staff updateStaff(String name, StaffUpdateRequest request) {
        Role existingMember = memberRepository.findByName(name);
        if (!(existingMember instanceof Staff)) {
            return null;
        }

        Staff updatedStaff = new Staff(
                name,
                request.major(),
                request.generation(),
                request.part(),
                request.position()
        );
        memberRepository.updateByName(name, updatedStaff);
        return updatedStaff;
    }

    public boolean deleteMember(String name) {
        return memberRepository.deleteByName(name);
    }

    public List<Role> getAllMembers() {
        return memberRepository.findAll();
    }

    public Role findMemberByName(String name) {
        return memberRepository.findByName(name);
    }
}