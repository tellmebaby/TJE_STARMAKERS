package com.aloha.starmakers.board.service;

import java.util.List;

import com.aloha.starmakers.board.dto.Option;
import com.aloha.starmakers.board.dto.Page;
import com.aloha.starmakers.board.dto.StarBoard;



public interface StarService {

    // 글 삭제
    public int delete(String starNoList) throws Exception;

    // 목록 조회 - 페이징, 검색
    public List<StarBoard> list(String type 
                               ,Page page
                               ,Option option) throws Exception;

    public List<StarBoard> list(String type 
                                ,Page page
                                ,Option option,
                                int userNo) throws Exception;

    // 글 등록(무료)
    public int insert(StarBoard starBoard, String username) throws Exception;

    // 글 등록(유료)
    public int payInsert(StarBoard starBoard, String username) throws Exception;


    // 글 조회
    public StarBoard select(int starNo) throws Exception;

    // 글 수정
    public int update(StarBoard starBoard) throws Exception;

    // 조회수 증가
    public int views(int starNo) throws Exception;

}
