package com.aloha.starmakers.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    @Override
    public int insert(StarBoard starBoard, String username) throws Exception {
        Users user = userMapper.login(username);
        starBoard.setUserNo(user.getUserNo());
        starBoard.setWriter(user.getName());

        // 정보 등록
        int result = starMapper.insert(starBoard);
        
        return result;
    }
    
}
