package com.aloha.starmakers.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.aloha.starmakers.board.dto.Option;
import com.aloha.starmakers.board.dto.Page;
import com.aloha.starmakers.board.dto.StarBoard;
import com.aloha.starmakers.user.dto.Users;

import groovyjarjarantlr4.v4.codegen.model.ExceptionClause;

@Mapper
public interface StarMapper {

    // 글 삭제
    public int delete(String starNoList) throws Exception;

    // 홍보 승인
    public int approve(String starNoList) throws Exception;

    // 홍보 반려
    public int companion(String starNoList) throws Exception;

    // 목록 조회 - 페이징, 검색
    public List<StarBoard> list(@Param("type") String type, @Param("page") Page page, @Param("option") Option option)
            throws Exception;

    // 목록 조회 - 페이징, 검색
    public List<StarBoard> list(@Param("type") String type,
            @Param("page") Page page, @Param("option") Option option, @Param("userNo") int userNo) throws Exception;
            
    // 목록 조회 - 페이징, 검색
    public List<StarBoard> list(@Param("type") String type,
            @Param("page") Page page, @Param("option") Option option, @Param("userNo") int userNo, @Param("status") int status) throws Exception;

    // 글 등록
    public int insert(StarBoard starBoard) throws Exception;

    // 글 등록(유료)
    public int payInsert(StarBoard starBoard, String username) throws Exception;

    // 글 조회
    public StarBoard select(int starNo) throws Exception;

    // 글 수정
    public int update(StarBoard starBoard) throws Exception;

    // 게시글 번호(기본키) 최댓값
    public int maxPk(String type) throws Exception;

    // 게시글 데이터 개수 조회
    public int count(@Param("option") Option option, @Param("type") String type) throws Exception;

    // 게시글 목록 - [검색]
    public List<StarBoard> search(@Param("option") Option option) throws Exception;

    // 조회수 증가
    public int views(int starNo) throws Exception;

    // 새 메인 페이지 카드리스트 요청
    public List<StarBoard> mainCardList(String type) throws Exception;

    // 마이페이지 쓴 글 조회
    public List<StarBoard> promotionList(@Param("userNo") int userNo, @Param("page") Page page,
            @Param("option") Option option) throws Exception;

    // 로그인 유저 모든 카드 불러오기
<<<<<<< HEAD
    public List<StarBoard> getMainCardListForLoggedInUser(@Param("userNo") int userNo,@Param("type") String type ) throws Exception;
=======
    public List<StarBoard> getMainCardListForLoggedInUser(int userNo, String type) throws Exception;
>>>>>>> fefeb600769e6df6f4ea50f8839eb41a2be00cf2
}
