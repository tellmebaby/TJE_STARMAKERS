package com.aloha.starmakers.board.service;

import java.util.List;

import com.aloha.starmakers.board.dto.QnaBoard;

public interface QnaService {
    // 목록 조회
    public List<QnaBoard> list() throws Exception;

    // 글 조회
    public QnaBoard select(int qnaNo) throws Exception;

    // 글 등록
    public int insert(QnaBoard qnaBoard, String username) throws Exception;
        
    // 글 수정
    public int update(QnaBoard qnaBoard) throws Exception;

    // 글 등록
    public int delete(String qnaNoList) throws Exception;
    
    // 답변 등록
    public int insertAnswer(QnaBoard qnaBoard) throws Exception;
}