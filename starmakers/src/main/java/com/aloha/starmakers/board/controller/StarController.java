package com.aloha.starmakers.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aloha.starmakers.board.dto.QnaBoard;
import com.aloha.starmakers.board.dto.StarBoard;
import com.aloha.starmakers.board.service.StarService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Slf4j
@Controller
@RequestMapping("/page/starCard")
public class StarController {
    
    @Autowired
    private StarService starService;
    
    /**
     * 글 등록 화면 요청
     * @return
     */
    @GetMapping("/starInsert")
    public String insert() {
        return "/page/starCard/starInsert";
    }

    /**
     * 글 등록 요청
     * @param model
     * @param starBoard
     * @param username
     * @return
     * @throws Exception
     */
    @PostMapping("/starInsert")
    public String insertPro(StarBoard starBoard, String username) throws Exception{
        int result = starService.insert(starBoard, username);
        // 리다이렉트
        // 데이터 처리 성공
        if(result>0){
            return "redirect:/page/starCard/starList";
        }
        

        // 데이터 처리 실패
        int no = starBoard.getStarNo();
        return "redirect:/page/starCard/starInsert?no=" + no + "&error";
    }
    
    /**
     * 결제 화면 요청
     * @param starNo
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/starPayment")
    public String payment(@RequestParam("starNo") int starNo, Model model) throws Exception {
        StarBoard starBoard = starService.select(starNo);
        model.addAttribute("starBoard", starBoard);
        return "/page/starCard/starPayment";
    }
    // @PostMapping("/starPayment")
    // public String paymentPro(@RequestParam("starNo") int starNo, Model model) throws Exception {
    //     StarBoard starBoard = starService.select(starNo);
    //     model.addAttribute("starBoard", starBoard);
    //     return "/page/starCard/starPayment";
    // }
    
    

    /**
     * 글 1개 조회
     * @param starNo
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/starRead")
    public String select(@RequestParam("starNo") int starNo, Model model) throws Exception {
        StarBoard starBoard = starService.select(starNo);
        model.addAttribute("starBoard", starBoard);
        return "/page/starCard/starRead";
    }
    
    /**
     * 글 수정 페이지 요청
     * @param starNo
     * @param model
     * @return
     * @throws Exception 
     */
    @GetMapping("/starUpdate")
    public String update(@RequestParam("starNo") int starNo, Model model) throws Exception {
        StarBoard starBoard = starService.select(starNo);
        model.addAttribute("starBoard", starBoard);
        return "/page/starCard/starUpdate";
    }

    @PostMapping("/starUpdate")
    public String updatePro(StarBoard starBoard) throws Exception {

        int result = starService.update(starBoard);
        if ( result > 0) {
            return "redirect:/page/starCard/starList";
        }
        int no = starBoard.getStarNo();
        
        return "redirect:/page/board/qnaBoard/qnaUpdate?qnaNo=" + no + "$error";
    }
    
    
    
}