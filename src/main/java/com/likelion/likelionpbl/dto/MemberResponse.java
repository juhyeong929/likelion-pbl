package com.likelion.likelionpbl.dto;

import com.likelion.likelionpbl.domain.Member;

public record MemberResponse(
        Long id,
        String name,
        String major,
        int generation,
        String part,
        String roleName,
        String studentId,
        String position
) {
    public static MemberResponse from(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getName(),
                member.getMajor(),
                member.getGeneration(),
                member.getPart(),
                member.getRoleType() != null ? member.getRoleType().getDisplayName() : null,
                member.getStudentId(),
                member.getPosition()
        );
    }
}
