package com.example.kdt_y_be_toy_project2.global.dto;

public record ResponseDto<T>(Integer code, String msg, T data) {
}