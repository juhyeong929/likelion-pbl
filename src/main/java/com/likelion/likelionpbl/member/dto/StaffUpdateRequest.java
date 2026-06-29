package com.likelion.likelionpbl.member.dto;

public record StaffUpdateRequest(
        String major,
        int generation,
        String part,
        String position
) {
}
