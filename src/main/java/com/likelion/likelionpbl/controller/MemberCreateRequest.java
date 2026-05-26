package com.likelion.likelionpbl.controller;

public record MemberCreateRequest(
        String roleType,
        String name,
        String major,
        int generation,
        String part,
        String studentId,
        String position
) {
}