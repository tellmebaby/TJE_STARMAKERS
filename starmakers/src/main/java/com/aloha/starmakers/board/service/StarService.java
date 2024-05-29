package com.aloha.starmakers.board.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aloha.starmakers.board.dto.Option;
import com.aloha.starmakers.board.dto.Page;
import com.aloha.starmakers.board.dto.StarBoard;

public interface StarService {

    // 목록 조회 - 페이징, 검색
    public List<StarBoard> list(@Param("page") Page page
                               ,@Param("option") Option option) throws Exception;

    // 글 등록(무료)
    public int insert(StarBoard starBoard, String username) throws Exception;

    // 글 등록(유료)
    public int payInsert(StarBoard starBoard, String username) throws Exception;


    // 글 조회
    public StarBoard select(int starNo) throws Exception;

    // 글 수정
    public int update(StarBoard starBoard) throws Exception;
}
