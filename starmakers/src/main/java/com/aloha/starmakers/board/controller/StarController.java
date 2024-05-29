package com.aloha.starmakers.board.controller;


import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.aloha.starmakers.board.dto.Option;
import com.aloha.starmakers.board.dto.Page;

import com.aloha.starmakers.board.dto.StarBoard;
import com.aloha.starmakers.board.service.StarService;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller
@RequestMapping("/page")
public class StarController {
    
    @Autowired
    private StarService starService;
    
    /**
     * 글 등록 화면 요청
     * @return
     */
    @GetMapping("/starCard/starInsert")
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
    @PostMapping("/starCard/starInsert")
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
    @GetMapping("/starCard/starPayment")
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
    @GetMapping("/starCard/starRead")
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
    @GetMapping("/starCard/starUpdate")
    public String update(@RequestParam("starNo") int starNo, Model model) throws Exception {
        StarBoard starBoard = starService.select(starNo);
        model.addAttribute("starBoard", starBoard);
        return "/page/starCard/starUpdate";
    }

    @PostMapping("/starCard/starUpdate")
    public String updatePro(StarBoard starBoard) throws Exception {

        int result = starService.update(starBoard);
        if ( result > 0) {
            return "redirect:/page/starCard/starList";
        }
        int no = starBoard.getStarNo();
        
        return "redirect:/page/board/qnaBoard/qnaUpdate?qnaNo=" + no + "$error";
    }
    
    // 아래부터 event 게시판

    @GetMapping("/board/eventBoard/eventList")
    public String eventList(@RequestParam(value = "type", defaultValue = "event") String type
                                    ,Model model, Page page
                                    ,Option option) throws Exception {

        List<StarBoard> starList = starService.list(type, page, option);
        model.addAttribute("starList", starList);
        return "/page/board/eventBoard/eventList";
    }
    
    @PostMapping("/board/eventBoard/eventInsert")
    public String eventInsertPro(StarBoard starBoard, String username) throws Exception{
        int result = starService.insert(starBoard, username);
        // 리다이렉트
        // 데이터 처리 성공
        if(result>0){
            return "redirect:/page/board/eventBoard/eventList";
        }
        

        // 데이터 처리 실패
        int no = starBoard.getStarNo();
        return "redirect:/page/board/eventBoard/eventInsert?starNo=" + no + "&error";
    }


    // review 게시판

    @GetMapping("/board/reviewboard/reviewList")
    public String reviewList(@RequestParam(value = "type", defaultValue = "review") String type, Model model, Page page, Option option) throws Exception {

        List<StarBoard> starList = starService.list(type, page, option);

        // 페이징, 검색
        log.info("page : " + page);
        log.info("option : " + option);

        
        model.addAttribute("starList", starList);
        model.addAttribute("page", page);
        model.addAttribute("option", option);

        List<Option> optionList = new ArrayList<Option>();
        optionList.add(new Option("제목+내용", 0));
        optionList.add(new Option("제목", 1));
        optionList.add(new Option("내용", 2));
        optionList.add(new Option("작성자", 3));
        model.addAttribute("optionList", optionList);
        return "/page/board/reviewBoard/reviewList";
    }

    @GetMapping("/board/reviewBoard/reviewInsert")
    public String reviewInsert() {

        return "/page/board/reviewBoard/reviewInsert";
    }
    


    @PostMapping("/board/reviewBoard/reviewInsert")
    public String reviewInsertPro(StarBoard starBoard, String username) throws Exception{
        int result = starService.insert(starBoard, username);
        // 리다이렉트
        // 데이터 처리 성공
        if(result > 0){
            return "redirect:/page/board/reviewBoard/reviewList";
        }

        // 데이터 처리 실패
        int no = starBoard.getStarNo();
        return "redirect:/page/board/reviewBoard/reviewInsert?starNo=" + no + "&error";
    }
    
    
    
}