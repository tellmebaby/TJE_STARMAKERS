package com.aloha.starmakers.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.aloha.starmakers.board.dto.Option;
import com.aloha.starmakers.board.dto.Page;
import com.aloha.starmakers.board.dto.QnaBoard;

@Mapper
public interface QnaMapper {

    // 목록 조회 - 페이징, 검색
    public List<QnaBoard> list(@Param("page") Page page
                              ,@Param("option") Option option) throws Exception;

    // 글 조회
    public QnaBoard select(int qnaNo) throws Exception;

    // 글 등록
    public int insert(QnaBoard qnaBoard) throws Exception;
    
    // 글 수정
    public int update(QnaBoard qnaBoard) throws Exception;
    
    // 글 삭제
    public int delete(String qnaNoList) throws Exception;

    // 답변 등록()
    public int insertAnswer(QnaBoard qnaBoard) throws Exception;

    // 게시글 번호(기본키) 최댓값
    public int maxPk() throws Exception;

    // 게시글 데이터 개수 조회
    public int count(@Param("option") Option option) throws Exception;

    // 게시글 목록 - [검색]
    public List<QnaBoard> search(@Param("option") Option option) throws Exception;

    // 조회수 증가
    public int view(int no) throws Exception;


}