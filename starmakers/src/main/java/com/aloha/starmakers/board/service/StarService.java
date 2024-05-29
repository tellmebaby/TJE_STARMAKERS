package com.aloha.starmakers.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aloha.starmakers.board.dto.StarBoard;


@Service
public interface StarService {

    // 글 목록
    public List<StarBoard> list(String type) throws Exception;

    // 글 등록(무료)
    public int insert(StarBoard starBoard, String username) throws Exception;
    // 글 등록(유료)
    public int payInsert(StarBoard starBoard, String username) throws Exception;


    // 글 조회
    public StarBoard select(int starNo) throws Exception;

    // 글 수정
    public int update(StarBoard starBoard) throws Exception;
}
