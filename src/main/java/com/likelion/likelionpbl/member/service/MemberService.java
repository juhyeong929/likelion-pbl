package com.likelion.likelionpbl.member.service;

import com.likelion.likelionpbl.global.exception.DuplicateMemberException;
import com.likelion.likelionpbl.global.exception.MemberNotFoundException;
import com.likelion.likelionpbl.member.domain.Member;
import com.likelion.likelionpbl.member.domain.RoleType;
import com.likelion.likelionpbl.member.dto.LionCreateRequest;
import com.likelion.likelionpbl.member.dto.LionUpdateRequest;
import com.likelion.likelionpbl.member.dto.StaffCreateRequest;
import com.likelion.likelionpbl.member.dto.StaffUpdateRequest;
import com.likelion.likelionpbl.member.repository.MemberRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Member createLion(LionCreateRequest request) {
        validateDuplicateName(request.name());

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

    @Transactional
    public Member createStaff(StaffCreateRequest request) {
        validateDuplicateName(request.name());

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

    @Transactional
    public Member updateLion(Long id, LionUpdateRequest request) {
        Member member = findMemberById(id);
        if (member.getRoleType() != RoleType.LION) {
            throw new MemberNotFoundException("멤버를 찾을 수 없습니다. id=" + id);
        }

        member.updateInfo(request.major(), request.generation(), request.part());
        member.updateStudentId(request.studentId());

        return memberRepository.save(member);
    }

    @Transactional
    public Member updateStaff(Long id, StaffUpdateRequest request) {
        Member member = findMemberById(id);
        if (member.getRoleType() != RoleType.STAFF) {
            throw new MemberNotFoundException("멤버를 찾을 수 없습니다. id=" + id);
        }

        member.updateInfo(request.major(), request.generation(), request.part());
        member.updatePosition(request.position());

        return memberRepository.save(member);
    }

    @Transactional
    public void deleteMember(Long id) {
        findMemberById(id);
        memberRepository.deleteById(id);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public List<Member> findByPart(String part) {
        return memberRepository.findByPart(part);
    }

    public Member findMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("멤버를 찾을 수 없습니다. id=" + id));
    }

    private void validateDuplicateName(String name) {
        if (memberRepository.findByName(name).isPresent()) {
            throw new DuplicateMemberException("이미 존재하는 멤버 이름입니다. name=" + name);
        }
    }
}
