package com.likelion.likelionpbl.dto;

public record StaffCreateRequest(
        String name,
        String major,
        int generation,
        String part,
        String position
) {
}