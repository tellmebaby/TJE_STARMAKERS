package com.aloha.starmakers.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.aloha.starmakers.board.dto.StarBoard;

@Mapper
public interface StarMapper {
    // 목록 조회

    // 글 등록
    public int insert(StarBoard starBoard) throws Exception;

    // 글 등록(유료)
    public int payInsert(StarBoard starBoard, String username) throws Exception;

    // 글 조회
    public StarBoard select(int starNo) throws Exception;

    // 글 수정
    public int update(StarBoard starBoard) throws Exception;
}
