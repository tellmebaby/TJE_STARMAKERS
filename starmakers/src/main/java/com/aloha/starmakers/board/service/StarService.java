package com.aloha.starmakers.board.service;

import com.aloha.starmakers.board.dto.StarBoard;

public interface StarService {

        // 글 등록
    public int insert(StarBoard starBoard, String username) throws Exception;


}
