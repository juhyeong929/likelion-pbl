package com.likelion.likelionpbl.dto;

public record LionCreateRequest(
        String name,
        String major,
        int generation,
        String part,
        String studentId
) {
}