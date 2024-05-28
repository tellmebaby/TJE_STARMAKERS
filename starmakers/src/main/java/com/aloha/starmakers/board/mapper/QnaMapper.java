package com.aloha.starmakers.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.aloha.starmakers.board.dto.QnaBoard;

@Mapper
public interface QnaMapper {

    // 목록 조회
    public List<QnaBoard> list() throws Exception;

    // 글 조회
    public QnaBoard select(int qnaNo) throws Exception;

    // 글 등록
    public int insert(QnaBoard qnaBoard) throws Exception;
    
    // 글 등록
    public int update(int qnaNo) throws Exception;

    public int insertAnswer(QnaBoard qnaBoard) throws Exception;

}