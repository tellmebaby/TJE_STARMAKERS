package com.aloha.starmakers.board.service;

import com.aloha.starmakers.board.dto.StarBoard;

public interface StarService {

    // 글 등록(무료)
    public int insert(StarBoard starBoard, String username) throws Exception;
    // 글 등록(유료)
    public int payInsert(StarBoard starBoard, String username) throws Exception;


    // 글 조회
    public StarBoard select(int starNo) throws Exception;


}
