package com.likelion.likelionpbl.global.dto;

public record ErrorResponse(
        int status,
        String message
) {
}
