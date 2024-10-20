package org.hype.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hype.domain.gCatVO;
import org.hype.domain.goodsVO;
import org.hype.domain.rankVO;
import org.hype.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private GoodsService gService;
	
	String open = null;
	
    @GetMapping("/goodsDetails")
    public String goodsDetails(@RequestParam("gno") int gno, Model model, HttpServletRequest request) {
    	goodsVO vo = gService.getOneByGno(gno);
    	System.out.println("굿즈 상세 페이지 gno : " + gno);
    	
    	HttpSession session = request.getSession();
    	String open = (String) session.getAttribute("open"); // 세션에 특정 gno의 open 값 저장
    	if (open == null) {
    		session.setAttribute("open", "yes"); // gno별로 세션에 'open' 설정
    		
    		int hit = vo.getGhit() + 1; // 조회수 증가
    		vo.setGhit(hit);
    		gService.getUpdatehit(vo);  // 조회수 업데이트
    	}
        model.addAttribute("goods", vo);
        log.info("like count 는 " + gService.getOneByGno(gno).getLikeCount());
        
        open = (String) session.getAttribute("open");
        if (open == null) {
            session.setAttribute("open", "yes");
            // 게시글 조회 시 'open' 값을 'yes'로 설정

            int hit = vo.getGhit() + 1;
            vo.setGhit(hit);
            gService.getUpdatehit(vo);
            // 조회수 증가 후 업데이트
        }
        
        return "/goodsStore/goodsDetails";
    }
 
    @GetMapping("/goodsMain")
    public String goodsMain(Model model, HttpServletRequest request) {
        // 메인 페이지를 보여줍니다
        log.info("메인 페이지로 이동");
        List<goodsVO> vo1 = gService.getListByLikeCount();
        vo1.forEach(item -> log.info("vo1는 " + item.getGname()));
        model.addAttribute("likeGoods", gService.getListByLikeCount());
        
        
        Map<String, Object> result1 = gService.getListByInterestOneNotLogin();
        String category1 = (String) result1.get("category");
        List<goodsVO> interestOneNotLogin = (List<goodsVO>) result1.get("goodsList");
        
        Map<String, Object> result2 = gService.getListByInterestTwoNotLogin();
        String category2 = (String) result2.get("category");
        List<goodsVO> interestTwoNotLogin = (List<goodsVO>) result2.get("goodsList");
        
        Map<String, Object> result3 = gService.getListByInterestThreeNotLogin();
        String category3 = (String) result3.get("category");
        List<goodsVO> interestThreeNotLogin = (List<goodsVO>) result3.get("goodsList");
        
        HttpSession session = request.getSession();
        session.setAttribute("open", "null");
        
        model.addAttribute("categoryOne", category1);
        model.addAttribute("categoryTwo", category2);
        model.addAttribute("categoryThree", category3);
        model.addAttribute("interestOneNotLogin", interestOneNotLogin);
        model.addAttribute("interestTwoNotLogin", interestTwoNotLogin);
        model.addAttribute("interestThreeNotLogin", interestThreeNotLogin);
        
        
        return "/goodsStore/goodsMain"; // 메인 페이지 JSP의 경로
    }

    @GetMapping("/goodsSearch")
    public String goodsSearch(@RequestParam(value = "searchText", required = false) String searchText, Model model, HttpServletRequest request) {
        // 검색어가 없으면 빈 문자열로 처리하여 전체 목록 검색
        if (searchText == null || searchText.trim().isEmpty()) {
            searchText = "";  // 빈 검색어로 처리하여 전체 결과 출력
        }

        // 검색 리스트 가져오기 (검색어가 없을 때는 전체 결과 반환)
        List<goodsVO> voList = gService.getSearchList(searchText, 0, 10);

        // 각 상품에 카테고리 정보 추가
        for (goodsVO vo : voList) {
            gCatVO voCat = gService.getCategory(vo.getGno());
            log.info("검색 시 cat 은 : " + voCat);
            vo.setGcat(voCat);
        }

        // 세션 처리
        HttpSession session = request.getSession();
        session.setAttribute("open", "null");

        // 모델에 검색 결과 및 검색어 추가
        model.addAttribute("searchList", voList);
        model.addAttribute("searchText", searchText);

        return "/goodsStore/goodsSearch";  // JSP 페이지 반환
    }

}