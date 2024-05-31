package com.aloha.starmakers.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.aloha.starmakers.board.dto.Option;
import com.aloha.starmakers.board.dto.Page;
import com.aloha.starmakers.board.dto.StarBoard;

@Mapper
public interface StarMapper {

    // 글 삭제
    public int delete(String starNoList) throws Exception;

    // 목록 조회 - 페이징, 검색
    public List<StarBoard> list(@Param("type") String type
                               ,@Param("page") Page page
                               ,@Param("option") Option option) throws Exception;



    // 목록 조회 - 페이징, 검색
    public List<StarBoard> list(@Param("type") String type,
                                @Param("page") Page page
                               ,@Param("option") Option option, @Param("userNo") int userNo) throws Exception;

    // 글 등록
    public int insert(StarBoard starBoard) throws Exception;

    // 글 등록(유료)
    public int payInsert(StarBoard starBoard, String username) throws Exception;

    // 글 조회
    public StarBoard select(int starNo) throws Exception;

    // 글 수정
    public int update(StarBoard starBoard) throws Exception;

    // 게시글 번호(기본키) 최댓값
    public int maxPk(String type) throws Exception;

    // 게시글 데이터 개수 조회
    public int count(@Param("option") Option option
                    ,@Param("type") String type) throws Exception;

    // 게시글 목록 - [검색]
    public List<StarBoard> search(@Param("option") Option option) throws Exception;

    // 조회수 증가
    public int view(int starNo) throws Exception;

}
