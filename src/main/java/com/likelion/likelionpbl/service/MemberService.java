package com.likelion.likelionpbl.service;

import com.likelion.likelionpbl.domain.Member;
import com.likelion.likelionpbl.domain.RoleType;
import com.likelion.likelionpbl.dto.LionCreateRequest;
import com.likelion.likelionpbl.dto.LionUpdateRequest;
import com.likelion.likelionpbl.dto.StaffCreateRequest;
import com.likelion.likelionpbl.dto.StaffUpdateRequest;
import com.likelion.likelionpbl.repository.MemberRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member createLion(LionCreateRequest request) {
        Member member = new Member(
                request.name(),
                request.major(),
                request.generation(),
                request.part(),
                RoleType.LION,
                request.studentId(),
                null
        );
        return memberRepository.save(member);
    }

    public Member createStaff(StaffCreateRequest request) {
        Member member = new Member(
                request.name(),
                request.major(),
                request.generation(),
                request.part(),
                RoleType.STAFF,
                null,
                request.position()
        );
        return memberRepository.save(member);
    }

    public Member updateLion(Long id, LionUpdateRequest request) {
        Member member = memberRepository.findById(id).orElse(null);
        if (member == null || member.getRoleType() != RoleType.LION) {
            return null;
        }

        member.updateInfo(request.major(), request.generation(), request.part());
        member.updateStudentId(request.studentId());
        
        return memberRepository.save(member);
    }

    public Member updateStaff(Long id, StaffUpdateRequest request) {
        Member member = memberRepository.findById(id).orElse(null);
        if (member == null || member.getRoleType() != RoleType.STAFF) {
            return null;
        }

        member.updateInfo(request.major(), request.generation(), request.part());
        member.updatePosition(request.position());

        return memberRepository.save(member);
    }

    public boolean deleteMember(Long id) {
        if (!memberRepository.existsById(id)) {
            return false;
        }
        memberRepository.deleteById(id);
        return true;
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member findMemberById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }
}