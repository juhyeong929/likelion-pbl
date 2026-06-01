package com.likelion.pbl.service;

import com.likelion.pbl.dto.LionCreateRequest;
import com.likelion.pbl.dto.LionUpdateRequest;
import com.likelion.pbl.dto.StaffCreateRequest;
import com.likelion.pbl.dto.StaffUpdateRequest;
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
        if (repository.existsByName(member.getName())) {
            return false;
        }
        repository.save(member);
        return true;
    }

    public boolean isDuplicateName(String name) {
        return repository.existsByName(name);
    }

    public Lion createLion(LionCreateRequest request) {
        if (repository.existsByName(request.name())) {
            return null;
        }

        Lion lion = new Lion(
                request.name(),
                request.major(),
                request.generation(),
                request.part(),
                request.studentId()
        );
        repository.save(lion);
        return lion;
    }

    public Staff createStaff(StaffCreateRequest request) {
        if (repository.existsByName(request.name())) {
            return null;
        }

        Staff staff = new Staff(
                request.name(),
                request.major(),
                request.generation(),
                request.part(),
                request.position()
        );
        repository.save(staff);
        return staff;
    }

    public Lion updateLion(String name, LionUpdateRequest request) {
        Role existingMember = repository.findByName(name);
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
        repository.updateByName(name, updatedLion);
        return updatedLion;
    }

    public Staff updateStaff(String name, StaffUpdateRequest request) {
        Role existingMember = repository.findByName(name);
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
        repository.updateByName(name, updatedStaff);
        return updatedStaff;
    }

    public boolean deleteMember(String name) {
        return repository.deleteByName(name);
    }

    public List<Role> getAllMembers() {
        return repository.findAll();
    }

    public Role findMemberByName(String name) {
        return repository.findByName(name);
    }
}