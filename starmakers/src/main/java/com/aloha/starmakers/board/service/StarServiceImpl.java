package com.aloha.starmakers.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aloha.starmakers.board.dto.Option;
import com.aloha.starmakers.board.dto.Page;
import com.aloha.starmakers.board.dto.StarBoard;
import com.aloha.starmakers.board.mapper.StarMapper;
import com.aloha.starmakers.user.dto.Users;
import com.aloha.starmakers.user.mapper.UserMapper;

@Service
public class StarServiceImpl implements StarService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StarMapper starMapper;

    /**
     * 게시글 목록
     */
    @Override
    public List<StarBoard> list(Page page, Option option) throws Exception {
        int total = starMapper.count(option);
        page.setTotal(total);
        List<StarBoard> starList = starMapper.list(page, option);
        return starList;
    }

    /**
     * 글 등록(무료)
     */
    @Override
    public int insert(StarBoard starBoard, String username) throws Exception {
        Users user = userMapper.login(username);
        starBoard.setUserNo(user.getUserNo());
        starBoard.setWriter(user.getName());
        // starBoard.setStatus("무료홍보");
        // 정보 등록
        int result = starMapper.insert(starBoard);
        
        return result;
    }

    /**
     * 글 등록(유료)
     * 결제버튼 눌렀을 때 이거로 실행
     */
    @Override
    public int payInsert(StarBoard starBoard, String username) throws Exception {
        Users user = userMapper.login(username);
        starBoard.setUserNo(user.getUserNo());
        starBoard.setWriter(user.getName());
        starBoard.setStatus("유료홍보");
        // 정보 등록
        int result = starMapper.insert(starBoard);
        
        return result;
    }


    /**
     * 글 조회
     */
    @Override
    public StarBoard select(int starNo) throws Exception {
        StarBoard starBoard = starMapper.select(starNo);
      
        return starBoard;
    }

    @Override
    public int update(StarBoard starBoard) throws Exception {
        int result = starMapper.update(starBoard);
        return result;
    }

}
