package com.aloha.starmakers.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.aloha.starmakers.user.dto.UserAuth;
import com.aloha.starmakers.user.dto.Users;

@Mapper
public interface UserMapper {

    // 로그인
    public Users login(String username) throws Exception;

    // 회원 조회
    public Users select(String username) throws Exception;

    // 회원 가입
    public int join(Users user) throws Exception;

    // 마이페이지

    // 회원 조회
    public Users read(String email) throws Exception; 

    // 회원 수정
    public int update(Users user) throws Exception;

    // 회원 탈퇴
    public int delete(Users user) throws Exception;

    // 회원 권한 등록
    public int insertAuth(UserAuth userAuth) throws Exception;

    // 가입 여부 확인
    public int selectEmail( Users user ) throws Exception;

    // 아이디 중복 확인
    public int selectId( Users user ) throws Exception;

    // 관리자 회원 리스트
    public List<Users> list() throws Exception;


}
