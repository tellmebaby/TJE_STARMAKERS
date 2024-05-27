package com.aloha.starmakers.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aloha.starmakers.board.dto.QnaBoard;
import com.aloha.starmakers.board.service.QnaService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Slf4j
@Controller
@RequestMapping("/page/board/qnaBoard")
public class QnaController {

    @Autowired
    private QnaService qnaService;

    @GetMapping("/qnaList")
    public String list(Model model) throws Exception {
        log.info("qna 목록");

        List<QnaBoard> qnaList = qnaService.list();
        model.addAttribute("qnaList", qnaList);
        return "/page/board/qnaBoard/qnaList";
    }

    /**
     * 게시글 등록 화면 요청
     * @return
     * @throws Exception
     */
    // @GetMapping("/qnaInsert")
    // public String insert() throws Exception {

    //     return "/page/board/qnaBoard/qnaInsert";
    // }


    @PostMapping("/qnaInsert")
    public String insertPro(QnaBoard qnaBoard, String username) throws Exception {
        int result = qnaService.insert(qnaBoard, username);
        // 리다이렉트
        // 데이터 처리 성공
        if(result>0){
            return "redirect:/page/board/qnaBoard/qnaList";
        }
        // 데이터 처리 실패
        int no = qnaBoard.getQnaNo();
        return "redirect:/board/insert?no=" + no + "&error";
    }

    
    
}