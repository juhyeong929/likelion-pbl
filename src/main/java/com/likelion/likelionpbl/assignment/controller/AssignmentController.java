package com.likelion.likelionpbl.assignment.controller;

import com.likelion.likelionpbl.assignment.dto.AssignmentCreateRequest;
import com.likelion.likelionpbl.assignment.dto.AssignmentResponse;
import com.likelion.likelionpbl.assignment.dto.AssignmentUpdateRequest;
import com.likelion.likelionpbl.assignment.service.AssignmentService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping("/members/{memberId}/assignments")
    public ResponseEntity<AssignmentResponse> create(
            @PathVariable Long memberId,
            @RequestBody AssignmentCreateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AssignmentResponse.from(assignmentService.createAssignment(memberId, request)));
    }

    @GetMapping("/assignments")
    public ResponseEntity<List<AssignmentResponse>> findAll() {
        List<AssignmentResponse> responses = assignmentService.findAll()
                .stream()
                .map(AssignmentResponse::from)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/members/{memberId}/assignments")
    public ResponseEntity<List<AssignmentResponse>> findByMemberId(@PathVariable Long memberId) {
        List<AssignmentResponse> responses = assignmentService.findByMemberId(memberId)
                .stream()
                .map(AssignmentResponse::from)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/assignments/search")
    public ResponseEntity<List<AssignmentResponse>> searchByTitle(@RequestParam String keyword) {
        List<AssignmentResponse> responses = assignmentService.searchByTitle(keyword)
                .stream()
                .map(AssignmentResponse::from)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/assignments/{id}")
    public ResponseEntity<AssignmentResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(AssignmentResponse.from(assignmentService.findById(id)));
    }

    @PutMapping("/assignments/{id}")
    public ResponseEntity<AssignmentResponse> update(
            @PathVariable Long id,
            @RequestBody AssignmentUpdateRequest request
    ) {
        return ResponseEntity.ok(AssignmentResponse.from(assignmentService.updateAssignment(id, request)));
    }

    @DeleteMapping("/assignments/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        assignmentService.deleteAssignment(id);
        return ResponseEntity.noContent().build();
    }
}
