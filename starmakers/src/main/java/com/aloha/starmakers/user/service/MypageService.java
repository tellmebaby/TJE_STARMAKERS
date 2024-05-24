package com.aloha.starmakers.user.service;

import com.aloha.starmakers.user.dto.Users;

public interface MypageService {

    // 마이페이지 회원 조회
    public Users select(int userNo) throws Exception;

    // 마이페이지 회원 수정
    public int update(Users user) throws Exception;

    // 마이페이지 회원 탈퇴
    public int delete(int userNo) throws Exception;
    
}
