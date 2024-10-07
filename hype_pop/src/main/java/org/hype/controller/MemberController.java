package org.hype.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/member/*")
public class MemberController {
  
    @GetMapping("/login")
    public String popUpLogin() {
         
         log.info("로그인 페이지로");
        
        return "/member/login"; // 검색 결과를 보여줄 JSP 페이지 이름
    }
    @GetMapping("/searchId")
    public String searchId() {
         
         log.info("아이디 찾기 페이지로");
        
        return "/member/searchId"; // 검색 결과를 보여줄 JSP 페이지 이름
    }
    @GetMapping("/searchPw")
    public String searchPw() {
         
         log.info("비밀번호 찾기 페이지로");
        
        return "/member/searchPw"; // 검색 결과를 보여줄 JSP 페이지 이름
    }
    @GetMapping("/signIn")
    public String signIn() {
         
         log.info("회원가입 페이지로");
        
        return "/member/signIn"; // 검색 결과를 보여줄 JSP 페이지 이름
    }
}
