package com.likelion.likelionpbl.dto;

public record StaffUpdateRequest(
        String major,
        int generation,
        String part,
        String position
) {
}