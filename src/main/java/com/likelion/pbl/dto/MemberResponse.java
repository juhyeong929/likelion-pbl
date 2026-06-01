package com.likelion.pbl.dto;

import com.likelion.pbl.role.Lion;
import com.likelion.pbl.role.Role;
import com.likelion.pbl.role.Staff;

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
    public static MemberResponse from(Role role) {
        if (role instanceof Lion lion) {
            return new MemberResponse(
                    lion.getId(),
                    lion.getName(),
                    lion.getMajor(),
                    lion.getGeneration(),
                    lion.getPart(),
                    "아기사자",
                    lion.getStudentId(),
                    null
            );
        } else if (role instanceof Staff staff) {
            return new MemberResponse(
                    staff.getId(),
                    staff.getName(),
                    staff.getMajor(),
                    staff.getGeneration(),
                    staff.getPart(),
                    "운영진",
                    null,
                    staff.getPosition()
            );
        }
        return null;
    }
}