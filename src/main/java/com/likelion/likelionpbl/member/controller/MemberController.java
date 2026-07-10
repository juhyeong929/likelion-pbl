package com.likelion.likelionpbl.member.controller;

import com.likelion.likelionpbl.member.dto.LionCreateRequest;
import com.likelion.likelionpbl.member.dto.LionUpdateRequest;
import com.likelion.likelionpbl.member.dto.MemberResponse;
import com.likelion.likelionpbl.member.dto.StaffCreateRequest;
import com.likelion.likelionpbl.member.dto.StaffUpdateRequest;
import com.likelion.likelionpbl.member.service.MemberService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/lions")
    public ResponseEntity<MemberResponse> createLion(@RequestBody LionCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(MemberResponse.from(memberService.createLion(request)));
    }

    @PostMapping("/staffs")
    public ResponseEntity<MemberResponse> createStaff(@RequestBody StaffCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(MemberResponse.from(memberService.createStaff(request)));
    }

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllMembers(
            @RequestParam(required = false) String part
    ) {
        List<MemberResponse> responses = (part == null || part.isBlank()
                ? memberService.getAllMembers()
                : memberService.findByPart(part))
                .stream()
                .map(MemberResponse::from)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getMember(@PathVariable Long id) {
        return ResponseEntity.ok(MemberResponse.from(memberService.findMemberById(id)));
    }

    @PutMapping("/lions/{id}")
    public ResponseEntity<MemberResponse> updateLion(
            @PathVariable Long id,
            @RequestBody LionUpdateRequest request
    ) {
        return ResponseEntity.ok(MemberResponse.from(memberService.updateLion(id, request)));
    }

    @PutMapping("/staffs/{id}")
    public ResponseEntity<MemberResponse> updateStaff(
            @PathVariable Long id,
            @RequestBody StaffUpdateRequest request
    ) {
        return ResponseEntity.ok(MemberResponse.from(memberService.updateStaff(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }
}
