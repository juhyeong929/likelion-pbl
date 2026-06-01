package com.likelion.pbl.dto;

public record MemberUpdateRequest(
        String name,
        String major,
        int generation,
        String part,
        String studentId,
        String position
) {
}