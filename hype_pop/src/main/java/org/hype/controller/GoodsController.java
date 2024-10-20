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
    	System.out.println("���� �� ������ gno : " + gno);
    	
    	HttpSession session = request.getSession();
    	String open = (String) session.getAttribute("open"); // ���ǿ� Ư�� gno�� open �� ����
    	if (open == null) {
    		session.setAttribute("open", "yes"); // gno���� ���ǿ� 'open' ����
    		
    		int hit = vo.getGhit() + 1; // ��ȸ�� ����
    		vo.setGhit(hit);
    		gService.getUpdatehit(vo);  // ��ȸ�� ������Ʈ
    	}
        model.addAttribute("goods", vo);
        log.info("like count �� " + gService.getOneByGno(gno).getLikeCount());
        
        open = (String) session.getAttribute("open");
        if (open == null) {
            session.setAttribute("open", "yes");
            // �Խñ� ��ȸ �� 'open' ���� 'yes'�� ����

            int hit = vo.getGhit() + 1;
            vo.setGhit(hit);
            gService.getUpdatehit(vo);
            // ��ȸ�� ���� �� ������Ʈ
        }
        
        return "/goodsStore/goodsDetails";
    }
 
    @GetMapping("/goodsMain")
    public String goodsMain(Model model, HttpServletRequest request) {
        // ���� �������� �����ݴϴ�
        log.info("���� �������� �̵�");
        List<goodsVO> vo1 = gService.getListByLikeCount();
        vo1.forEach(item -> log.info("vo1�� " + item.getGname()));
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
        
        
        return "/goodsStore/goodsMain"; // ���� ������ JSP�� ���
    }

    @GetMapping("/goodsSearch")
    public String goodsSearch(@RequestParam(value = "searchText", required = false) String searchText, Model model, HttpServletRequest request) {
        // �˻�� ������ �� ���ڿ��� ó���Ͽ� ��ü ��� �˻�
        if (searchText == null || searchText.trim().isEmpty()) {
            searchText = "";  // �� �˻���� ó���Ͽ� ��ü ��� ���
        }

        // �˻� ����Ʈ �������� (�˻�� ���� ���� ��ü ��� ��ȯ)
        List<goodsVO> voList = gService.getSearchList(searchText, 0, 10);

        // �� ��ǰ�� ī�װ� ���� �߰�
        for (goodsVO vo : voList) {
            gCatVO voCat = gService.getCategory(vo.getGno());
            log.info("�˻� �� cat �� : " + voCat);
            vo.setGcat(voCat);
        }

        // ���� ó��
        HttpSession session = request.getSession();
        session.setAttribute("open", "null");

        // �𵨿� �˻� ��� �� �˻��� �߰�
        model.addAttribute("searchList", voList);
        model.addAttribute("searchText", searchText);

        return "/goodsStore/goodsSearch";  // JSP ������ ��ȯ
    }

}