package com.aloha.starmakers.user.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Users {
    private int userNo;
    private String name;
    private String id;
    private String email;
    private String password;
    private String confirmPassword;
    private String phone;
    private String address;
    private Date regDate;
    private Date updDate;
    private int enabled;
    private String gender;
    private String birth;

    private int userImgId;

    private int imgNo;

    // 권한 목록
    private List<UserAuth> authList;
}
