package org.hype.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.hype.domain.popStoreVO; // popStoreVO 클래스 import
import org.hype.service.PopUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @Autowired
    private PopUpService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        int userno = 5; // 임의의 사용자 번호 (예: 로그인한 사용자의 번호)

        // 인기 팝업 스토어 조회
        List<popStoreVO> popularPopUps = service.getPopularPopUps(); 
        model.addAttribute("popularPopUps", popularPopUps); // 모델에 인기 팝업 추가

        // 사용자 관심사에 따른 상위 스토어 조회
        Map<String, List<popStoreVO>> topStoresByInterest = service.getTopStoresByInterests(userno);
        model.addAttribute("topStoresByInterest", topStoresByInterest); // 모델에 관심사별 상위 스토어 추가

        return "popUp/popUpMainPage"; // JSP 페이지 이름 반환
    }
}
