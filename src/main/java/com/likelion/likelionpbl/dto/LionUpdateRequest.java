package com.likelion.likelionpbl.dto;

public record LionUpdateRequest(
        String major,
        int generation,
        String part,
        String studentId
) {
}