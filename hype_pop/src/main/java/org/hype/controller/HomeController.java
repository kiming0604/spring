package org.hype.controller;

import java.util.List;
import java.util.Locale;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        
        // 팝업 스토어 리스트를 서비스에서 받아오는 코드 필요
    
        
        // 모델에 팝업 스토어 리스트를 추가하여 JSP에 전달 하는 코드 필요
        

        return "popUp/popUpMain"; // 메인 화면 JSP로 이동
    }
}
