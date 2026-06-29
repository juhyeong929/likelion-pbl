package com.likelion.likelionpbl.member.dto;

public record LionUpdateRequest(
        String major,
        int generation,
        String part,
        String studentId
) {
}
