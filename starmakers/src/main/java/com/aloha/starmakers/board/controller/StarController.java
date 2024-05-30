package com.aloha.starmakers.board.controller;



import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import com.aloha.starmakers.board.dto.StarBoard;
import com.aloha.starmakers.user.dto.Users;
import com.aloha.starmakers.board.service.FileService;
import com.aloha.starmakers.board.service.StarService;
import com.aloha.starmakers.board.dto.Option;
import com.aloha.starmakers.board.dto.Page;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/page")
public class StarController {

    @Autowired
    private StarService starService;

    @Autowired
    private FileService fileService;

  
    
    /**
     * 글 등록 화면 요청
     * 
     * @return
     */
    @GetMapping("/starCard/starInsert")
    public String insert() {
        return "/page/starCard/starInsert";
    }

    /**
     * 글 등록 요청
     * 
     * @param model
     * @param starBoard
     * @param username
     * @return
     * @throws Exception
     */
    @PostMapping("/starCard/starInsert")
    public String insertPro(StarBoard starBoard, String username, @RequestParam(value = "image", required = false) MultipartFile file ,HttpSession session)
            throws Exception {
        int starNo = starService.insert(starBoard, username);

        Users user = (Users) session.getAttribute("user");
        int userNo = user.getUserNo();


        // 리다이렉트
        // 데이터 처리 성공
        if (starNo > 0) {
            // 파일 처리 로직
            if (file != null && !file.isEmpty()) {
                fileService.upload(file, starNo, userNo);
            }
            return "redirect:/page/starCard/starList";
        }

        // 데이터 처리 실패
        int no = starBoard.getStarNo();
        return "redirect:/page/starCard/starInsert?no=" + no + "&error";
    }

    @GetMapping("/starCard/starList")
    public String cardList(@RequestParam(value = "type", defaultValue = "starCard") String type
                                    ,Model model, Page page
                                    ,Option option) throws Exception {

        List<StarBoard> starList = starService.list(type, page, option);

        // log.info("statList ="+starList.toString());

        model.addAttribute("starList", starList);
        model.addAttribute("page", page);
        model.addAttribute("option", option);
        

        return "/page/starCard/starList";
    }




    /**
     * 결제 화면 요청
     * 
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
    // public String paymentPro(@RequestParam("starNo") int starNo, Model model)
    // throws Exception {
    // StarBoard starBoard = starService.select(starNo);
    // model.addAttribute("starBoard", starBoard);
    // return "/page/starCard/starPayment";
    // }

    /**
     * 글 1개 조회
     * 
     * @param starNo
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/starCard/starRead")
    public String select(@RequestParam("starNo") int starNo, Model model) throws Exception {
        StarBoard starBoard = starService.select(starNo);
        // 조회수 증가
        int views = starService.view(starNo);
        model.addAttribute("starBoard", starBoard);
        return "/page/starCard/starRead";
    }

    /**
     * 글 수정 페이지 요청
     * 
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
        if (result > 0) {
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
        model.addAttribute("page", page);
        model.addAttribute("option", option);
        
        List<Option> optionList = new ArrayList<Option>();
        optionList.add(new Option("제목+내용", 0));
        optionList.add(new Option("제목", 1));
        optionList.add(new Option("내용", 2));
        optionList.add(new Option("작성자", 3));
        model.addAttribute("optionList", optionList);

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
     /**
     * 글 1개 조회
     * @param starNo
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/board/eventBoard/eventPost")
    public String eventSelect(@RequestParam("starNo") int starNo, Model model) throws Exception {
        StarBoard starBoard = starService.select(starNo);
        model.addAttribute("starBoard", starBoard);
        return "/page/board/eventBoard/eventPost";
    }
    
    /**
     * 글 수정 페이지 요청
     * @param starNo
     * @param model
     * @return
     * @throws Exception 
     */
    @GetMapping("/board/eventBoard/eventUpdate")
    public String eventUpdate(@RequestParam("starNo") int starNo, Model model) throws Exception {
        StarBoard starBoard = starService.select(starNo);
        model.addAttribute("starBoard", starBoard);
        return "/page/board/eventBoard/eventUpdate";
    }

    @PostMapping("/board/eventBoard/eventUpdate")
    public String eventUpdatePro(StarBoard starBoard) throws Exception {

        int result = starService.update(starBoard);
        if ( result > 0) {
            return "redirect:/page/board/eventBoard/eventList";
        }
        int no = starBoard.getStarNo();
        
        return "redirect:/page/board/eventBoard/eventUpdate?qnaNo=" + no + "$error";
    }

    @PostMapping("/board/eventBoard/eventDelete")
    public String eventDelete(@RequestParam("starNos") String starNos) throws Exception {
       
        int result = 0;
        result = starService.delete(starNos);

        if (result > 0) {
            return "redirect:/page/mypage/event";
        }
        
        return "redirect:/page/mypage/event?error";  // 삭제 실패시에도 같은 페이지로 리디렉션
    }


    // 아래부터 review 게시판


    // review 게시판
     /**
     * 글 삭제
     * @return
     * @throws Exception 
     */
    @PostMapping("/review/delete")
    public String reviewDelete(@RequestParam("starNos") String starNos) throws Exception {
        int result = starService.delete(starNos);
        if(result>0){
            log.info("삭제 완료");
        }
        else {
            log.info("삭제 실패");
        }
        return "/page/board/reviewBoard/reviewList";
    }

    @GetMapping("/board/reviewBoard/reviewList")
    public String reviewList(@RequestParam(value = "type", defaultValue = "review") String type
                                    ,Model model, Page page
                                    ,Option option) throws Exception {
                                        
        List<StarBoard> starList = starService.list(type, page, option);
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
        int no = starBoard.getStarNo();
        if(result>0){

            return "redirect:/page/board/reviewBoard/reviewList";
        }

        // 데이터 처리 실패
        return "redirect:/page/board/reviewBoard/reviewInsert?starNo=" + no + "&error";
    }

    /**
     * 글 1개 조회
     * @param starNo
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/board/reviewBoard/reviewPost")
    public String reviewSelect(@RequestParam("starNo") int starNo, Model model) throws Exception {
        StarBoard starBoard = starService.select(starNo);
        model.addAttribute("starBoard", starBoard);
        return "/page/board/reviewBoard/reviewPost";
    }
    
    /**
     * 글 수정 페이지 요청
     * @param starNo
     * @param model
     * @return
     * @throws Exception 
     */
    @GetMapping("/board/reviewBoard/reviewUpdate")
    public String reviewUpdate(@RequestParam("starNo") int starNo, Model model) throws Exception {
        StarBoard starBoard = starService.select(starNo);
        model.addAttribute("starBoard", starBoard);
        return "/page/board/reviewBoard/reviewUpdate";
    }

    @PostMapping("/board/reviewBoard/reviewUpdate")
    public String reviewUpdatePro(StarBoard starBoard) throws Exception {

        int result = starService.update(starBoard);
        if ( result > 0) {
            return "redirect:/page/board/reviewBoard/reviewList";
        }
        int no = starBoard.getStarNo();
        
        return "redirect:/page/board/reviewBoard/reviewUpdate?qnaNo=" + no + "$error";
    }
    
    // 아래부터 공지게시판
    @GetMapping("/board/anBoard/anList")
    public String anList(@RequestParam(value = "type", defaultValue = "an") String type
                                    ,Model model, Page page
                                    ,Option option) throws Exception {
                                        
        List<StarBoard> starList = starService.list(type, page, option);
        model.addAttribute("starList", starList);
        model.addAttribute("page", page);
        model.addAttribute("option", option);

        List<Option> optionList = new ArrayList<Option>();
        optionList.add(new Option("제목+내용", 0));
        optionList.add(new Option("제목", 1));
        optionList.add(new Option("내용", 2));
        optionList.add(new Option("작성자", 3));
        model.addAttribute("optionList", optionList);
        return "/page/board/anBoard/anList";
    }


    @PostMapping("/board/anBoard/anInsert")
    public String anInsertPro(StarBoard starBoard, String username) throws Exception{
        int result = starService.insert(starBoard, username);
        // 리다이렉트
        // 데이터 처리 성공
        if(result>0){
            return "redirect:/page/board/anBoard/anList";
        }
        

        // 데이터 처리 실패
        int no = starBoard.getStarNo();
        return "redirect:/page/board/anBoard/anInsert?starNo=" + no + "&error";
    }
 /**
     * 글 1개 조회
     * @param starNo
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/board/anBoard/anPost")
    public String anSelect(@RequestParam("starNo") int starNo, Model model) throws Exception {
        StarBoard starBoard = starService.select(starNo);
        model.addAttribute("starBoard", starBoard);
        return "/page/board/anBoard/anPost";
    }
    
    /**
     * 글 수정 페이지 요청
     * @param starNo
     * @param model
     * @return
     * @throws Exception 
     */
    @GetMapping("/board/anBoard/anUpdate")
    public String anUpdate(@RequestParam("starNo") int starNo, Model model) throws Exception {
        StarBoard starBoard = starService.select(starNo);
        model.addAttribute("starBoard", starBoard);
        return "/page/board/anBoard/anUpdate";
    }

    /**
     * 글 수정
     * @param starBoard
     * @return
     * @throws Exception
     */
    @PostMapping("/board/anBoard/anUpdate")
    public String anUpdatePro(StarBoard starBoard) throws Exception {

        int result = starService.update(starBoard);
        if ( result > 0) {
            return "redirect:/page/board/anBoard/anList";
        }
        int no = starBoard.getStarNo();
        
        return "redirect:/page/board/anBoard/anUpdate?qnaNo=" + no + "$error";
    }



}