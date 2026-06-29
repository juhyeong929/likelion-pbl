package com.likelion.likelionpbl.member.dto;

public record StaffCreateRequest(
        String name,
        String major,
        int generation,
        String part,
        String position
) {
}
