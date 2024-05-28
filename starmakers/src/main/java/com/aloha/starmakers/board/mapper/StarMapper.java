package com.aloha.starmakers.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.aloha.starmakers.board.dto.StarBoard;

@Mapper
public interface StarMapper {
    // 목록 조회

    // 글 등록
    public int insert(StarBoard starBoard) throws Exception;

}
