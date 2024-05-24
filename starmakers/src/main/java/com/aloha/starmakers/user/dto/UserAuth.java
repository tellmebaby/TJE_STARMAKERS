package com.aloha.starmakers.user.dto;

import lombok.Data;

@Data
public class UserAuth {
    private String authNo;
    private String userId;      // ✅ 아이디(이메일)
    private String auth;
}