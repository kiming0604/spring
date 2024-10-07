package org.hype.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/goodsStore/*")
public class GoodsController {
    @GetMapping("/goodsDetails")
    public String goodsSearch(@RequestParam("goodsName") String goodsName, Model model) {
        // searchData를 사용하여 검색 로직을 처리
        System.out.println("검색 데이터: " + goodsName);
        
        //DB에서 정보 받아오는 로직이 여기있어야함
        
        // searchData를 모델에 추가하여 JSP로 전달
        model.addAttribute("goodsName", goodsName);
        
        return "/goodsStore/goodsDetails"; // 검색 결과를 보여줄 JSP 페이지 이름
    }
 

    @GetMapping("/goodsMain")
    public String goodsMain() {
    	
    	// 굿즈들 정보 받아오는 로직 필요
         
         log.info("굿즈 메인페이지로");
        
        return "/goodsStore/goodsMain"; // 검색 결과를 보여줄 JSP 페이지 이름
    }
//    @GetMapping("/goodsMain")
//    public String goodsMain(Model model) {
//        log.info("굿즈 메인페이지로");
//
//        // 서비스에서 굿즈 리스트를 받아옴
//        List<Goods> goodsList = goodsService.getGoodsList();
//        
//        // 모델에 굿즈 리스트 추가
//        model.addAttribute("goodsList", goodsList);
//        
//        return "/goodsStore/goodsMain"; // 메인 페이지로 이동
//    }
    @GetMapping("/goodsSearch")
    public String goodsSearch() {
         
    	// 굿즈들 정보 받아오는 로직 필요
    	
         log.info("굿즈 검색페이지로");
        
        return "/goodsStore/goodsSearch"; // 검색 결과를 보여줄 JSP 페이지 이름
    }
}
