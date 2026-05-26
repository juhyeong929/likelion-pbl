package com.likelion.likelionpbl.controller;

import com.likelion.likelionpbl.role.Lion;
import com.likelion.likelionpbl.role.Role;
import com.likelion.likelionpbl.role.Staff;
import com.likelion.likelionpbl.service.MemberService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<String> createMember(@RequestBody MemberCreateRequest request) {
        if (memberService.isDuplicateName(request.name())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 같은 이름의 멤버가 있습니다.");
        }

        Role member = createRole(request);
        memberService.registerMember(member);
        return ResponseEntity.status(HttpStatus.CREATED).body("멤버 등록이 완료되었습니다.");
    }

    @GetMapping
    public List<String> getAllMembers() {
        return memberService.getAllMembers().stream()
                .map(Role::getDetails)
                .toList();
    }

    @GetMapping("/{name}")
    public ResponseEntity<String> getMemberByName(@PathVariable String name) {
        Role member = memberService.findMemberByName(name);
        if (member == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 이름을 가진 멤버가 없습니다.");
        }
        return ResponseEntity.ok(member.getDetails());
    }

    private Role createRole(MemberCreateRequest request) {
        String roleType = request.roleType() == null ? "" : request.roleType().trim().toUpperCase();
        if ("LION".equals(roleType)) {
            return new Lion(request.name(), request.major(), request.generation(), request.part(), request.studentId());
        }
        if ("STAFF".equals(roleType)) {
            return new Staff(request.name(), request.major(), request.generation(), request.part(), request.position());
        }
        throw new IllegalArgumentException("roleType must be LION or STAFF");
    }
}