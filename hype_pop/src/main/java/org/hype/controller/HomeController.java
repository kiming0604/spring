package org.hype.controller;

import java.util.List;
import java.util.Locale;

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
    PopUpService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        
        List<popStoreVO> popUps = service.getPopularPopUps(); // 데이터 가져오기
        model.addAttribute("popUps", popUps); // 모델에 추가
        
        return "popUp/popUpMainPage"; 
    }
}
