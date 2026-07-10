package com.likelion.likelionpbl.assignment.service;

import com.likelion.likelionpbl.assignment.domain.Assignment;
import com.likelion.likelionpbl.assignment.dto.AssignmentCreateRequest;
import com.likelion.likelionpbl.assignment.dto.AssignmentUpdateRequest;
import com.likelion.likelionpbl.assignment.repository.AssignmentRepository;
import com.likelion.likelionpbl.global.exception.AssignmentNotFoundException;
import com.likelion.likelionpbl.member.service.MemberService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final MemberService memberService;

    public AssignmentService(
            AssignmentRepository assignmentRepository,
            MemberService memberService
    ) {
        this.assignmentRepository = assignmentRepository;
        this.memberService = memberService;
    }

    @Transactional
    public Assignment createAssignment(Long memberId, AssignmentCreateRequest request) {
        var member = memberService.findMemberById(memberId);

        Assignment assignment = new Assignment(request.title(), request.description(), member);
        return assignmentRepository.save(assignment);
    }

    public List<Assignment> findAll() {
        return assignmentRepository.findAll();
    }

    public List<Assignment> findByMemberId(Long memberId) {
        return assignmentRepository.findByMemberId(memberId);
    }

    public Assignment findById(Long id) {
        return assignmentRepository.findById(id)
                .orElseThrow(() -> new AssignmentNotFoundException("과제를 찾을 수 없습니다. id=" + id));
    }

    public List<Assignment> searchByTitle(String keyword) {
        return assignmentRepository.findByTitleContaining(keyword);
    }

    @Transactional
    public Assignment updateAssignment(Long id, AssignmentUpdateRequest request) {
        Assignment assignment = findById(id);

        assignment.updateInfo(request.title(), request.description());
        return assignmentRepository.save(assignment);
    }

    @Transactional
    public void deleteAssignment(Long id) {
        findById(id);
        assignmentRepository.deleteById(id);
    }
}
