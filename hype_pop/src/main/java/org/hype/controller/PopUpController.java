package org.hype.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/hypePop")
public class PopUpController {
	

    // 검색 결과를 보여주는 메서드
    @GetMapping("/search") // URL 매핑에 해당하는 메서드
    public String search(@RequestParam("searchData") String searchData, Model model) {
        // searchData를 받아 검색 결과를 처리
        System.out.println("검색 데이터: " + searchData);
        
        // DB에서 검색 결과를 가져오는 로직 작성
        
        // searchData를 JSP에 전달
        model.addAttribute("searchData", searchData);
        
        return "/popUp/searchResults"; // 검색 결과를 보여주는 JSP 경로
    }

    // 검색 결과가 없는 경우를 처리하는 메서드
    @GetMapping("/search/noData") // 특정 URL 매핑
    public String searchWithoutData() {
        // 검색 결과가 없는 경우 처리 로직
        
        return "/popUp/searchResults"; // 검색 결과를 보여주는 JSP 경로
    }

    @GetMapping("/popUpDetails")
    public String popUpDetails(@RequestParam("storeName") String storeName, Model model) {
        // storeName을 받아 상세 정보를 처리
        System.out.println("스토어 이름: " + storeName);
        
        // DB에서 상세 정보를 가져오는 로직 작성
        
        // storeName을 JSP에 전달
        model.addAttribute("storeName", storeName);
        
        return "/popUp/popUpDetailsPage"; // 상세 정보를 보여주는 JSP 경로
    }

    // 캘린더를 보여주는 메서드
    @GetMapping("/calendar") // 특정 URL 매핑
    public String popUpCalendar() {
        // 캘린더 처리 로직
        
        return "/popUp/calendar"; // 캘린더를 보여주는 JSP 경로
    }
}
