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
    
    // 검색 데이터가 있을 경우 처리하는 메서드
    @GetMapping("/search") // URL 경로를 여기서 직접 지정
    public String search(@RequestParam("searchData") String searchData, Model model) {
        // searchData를 사용하여 검색 로직을 처리
        System.out.println("검색 데이터: " + searchData);
        
        // DB에서 정보 받아오는 로직이 여기있어야함
        
        // searchData를 모델에 추가하여 JSP로 전달
        model.addAttribute("searchData", searchData);
        
        return "/popUp/searchResults"; // 검색 결과를 보여줄 JSP 페이지 이름
    }
 
    // 검색 데이터가 없을 경우 처리하는 메서드
    @GetMapping("/search/noData") // 다른 URL 경로로 매핑
    public String searchWithoutData() {
        // 검색 데이터가 없는 경우의 처리
        
        return "/popUp/searchResults"; // 검색 결과를 보여줄 JSP 페이지 이름
    }

    @GetMapping("/popUpDetails")
    public String popUpDetails(@RequestParam("storeName") String storeName, Model model) {
        // storeName을 사용하여 검색 로직을 처리
        System.out.println("스토어 이름: " + storeName);
        
        // DB에서 정보 받아오는 로직이 여기있어야함
        
        // storeName을 모델에 추가하여 JSP로 전달
        model.addAttribute("storeName", storeName);
        
        return "/popUp/popUpDetails"; // 스토어 세부 정보를 보여줄 JSP 페이지 이름
    }
    // 검색 데이터가 없을 경우 처리하는 메서드
    @GetMapping("/calendar") // 다른 URL 경로로 매핑
    public String popUpCalendar() {
        // 검색 데이터가 없는 경우의 처리
        
        return "/popUp/calendar"; // 검색 결과를 보여줄 JSP 페이지 이름
    }
}
