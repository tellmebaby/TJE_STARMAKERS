package com.aloha.starmakers.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aloha.starmakers.board.dto.QnaBoard;
import com.aloha.starmakers.board.mapper.QnaMapper;
import com.aloha.starmakers.user.dto.Users;
import com.aloha.starmakers.user.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class QnaServiceImpl implements QnaService {

    @Autowired
    private QnaMapper qnaMapper;
    
    @Autowired
    private UserMapper userMapper;

    // 목록 조회
    @Override
    public List<QnaBoard> list() throws Exception {
        List<QnaBoard> qnaList = qnaMapper.list();
        return qnaList;
    }

    @Override
    public QnaBoard select(int qnaNo) throws Exception {
        QnaBoard qnaBoard = qnaMapper.select(qnaNo);
        return qnaBoard;
    }

    // 글 등록
    @Override
    public int insert(QnaBoard qnaBoard, String username) throws Exception {
        // 작성자 정보 불러오기
        Users user = userMapper.login(username);
        qnaBoard.setUserNo(user.getUserNo());
        qnaBoard.setWriter(user.getName());

        // 정보 등록
        int result = qnaMapper.insert(qnaBoard);
        
        return result;
    }

    @Override
    public int update(int qnaNo) throws Exception {
        int result = qnaMapper.update(qnaNo);

        return result;
    }

    @Override
    public int insertAnswer(QnaBoard qnaBoard) throws Exception {
        int result = qnaMapper.insertAnswer(qnaBoard);
        return result;
    }


    
}