package com.likelion.likelionpbl.controller;

import com.likelion.likelionpbl.dto.LionCreateRequest;
import com.likelion.likelionpbl.dto.LionResponse;
import com.likelion.likelionpbl.dto.LionUpdateRequest;
import com.likelion.likelionpbl.dto.StaffCreateRequest;
import com.likelion.likelionpbl.dto.StaffResponse;
import com.likelion.likelionpbl.dto.StaffUpdateRequest;
import com.likelion.likelionpbl.role.Lion;
import com.likelion.likelionpbl.role.Role;
import com.likelion.likelionpbl.role.Staff;
import com.likelion.likelionpbl.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PostMapping("/lions")
    public ResponseEntity<LionResponse> createLion(@RequestBody LionCreateRequest request) {
        Lion createdLion = memberService.createLion(request);
        if (createdLion == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(LionResponse.from(createdLion));
    }

    @PostMapping("/staffs")
    public ResponseEntity<StaffResponse> createStaff(@RequestBody StaffCreateRequest request) {
        Staff createdStaff = memberService.createStaff(request);
        if (createdStaff == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(StaffResponse.from(createdStaff));
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getMemberByName(@PathVariable String name) {
        Role member = memberService.findMemberByName(name);
        if (member == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (member instanceof Lion lion) {
            return ResponseEntity.ok(LionResponse.from(lion));
        }

        if (member instanceof Staff staff) {
            return ResponseEntity.ok(StaffResponse.from(staff));
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PutMapping("/lions/{name}")
    public ResponseEntity<LionResponse> updateLion(
            @PathVariable String name,
            @RequestBody LionUpdateRequest request
    ) {
        Lion updatedLion = memberService.updateLion(name, request);
        if (updatedLion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(LionResponse.from(updatedLion));
    }

    @PutMapping("/staffs/{name}")
    public ResponseEntity<StaffResponse> updateStaff(
            @PathVariable String name,
            @RequestBody StaffUpdateRequest request
    ) {
        Staff updatedStaff = memberService.updateStaff(name, request);
        if (updatedStaff == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(StaffResponse.from(updatedStaff));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteMember(@PathVariable String name) {
        if (!memberService.deleteMember(name)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.noContent().build();
    }
}