package com.aloha.starmakers.message.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aloha.starmakers.message.dto.Message;
import com.aloha.starmakers.message.mapper.MessageMapper;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public int insertMessage(Message message) {
        int result = messageMapper.insertMessage(message);
        return result;
    }

    @Override
    public Message getMessageById(int messageNo) {
        Message message = messageMapper.getMessageById(messageNo);
        return message;
    }


    @Override
    public int updateMessage(Message message) {
        int result = messageMapper.updateMessage(message);
        return result;
    }

    @Override
    public int deleteMessage(int messageNo) {
        int result = messageMapper.deleteMessage(messageNo);
        return result;
    }

    @Override
    public List<Message> getMessageByReply(int replyNo) {
        List<Message> messagesList = messageMapper.getMessageByReply(replyNo);
        return messagesList;
    }

    @Override
    public List<Message> getMessageByPay(int payNo) {
        List<Message> messagesList = messageMapper.getMessageByPay(payNo);
        return messagesList;
    }

    @Override
    public List<Message> getMessageByQna(int qnaNo) {
        List<Message> messagesList = messageMapper.getMessageByQna(qnaNo);
        return messagesList;
    }

    @Override
    public List<Message> getMessageByUser(int userNo) {
        List<Message> messagesList = messageMapper.getMessageByUser(userNo);
        return messagesList;
    }

    @Override
    public int updateMessageByUser( int userNo ) {
        return messageMapper.updateMessageByUser(userNo);
    }
    
    // 1:1 대화 받아오기
    @Override
    public List<Message> getChatMessageByUser(int userNo) {
        return messageMapper.getChatMessageByUser(userNo);
    }
}
