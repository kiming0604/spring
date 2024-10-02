package org.jae.controller;

import org.jae.domain.MemberVO;
import org.jae.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/sample")
public class SampleController {
	@Autowired
	MemberService service;
	
	@Autowired
	PasswordEncoder pwencoder;
	
	@GetMapping("/all")
	public String doAll() {
		log.info("do all can access everybody");
		return("sample/all");
	}
	@GetMapping("/member")
	public String doMember() {
		log.info("logined member");
		return "sample/member";
	}
	@GetMapping("/admin")
	public String doAdmin() {
		log.info("admin only");
		return "sample/admin";
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN' , 'ROLE_MEMBER')")
	@GetMapping("/annoMember")
	public String doAnnoMember() {
		log.info("logined annoMember");
		return "sample/annoMember";
	}
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/annoAdmin")
	public String doAnnoAdmin() {
		log.info("logined AnnoAdmin");
		return "sample/annoAdmin";
	}
	@GetMapping("/memberRegister")
	public String showRegisterForm() {
	   
	    return "/board/memberRegister"; 
	}

	@PostMapping("/memberRegister")
	public String memberRegister(MemberVO vo) {
		  
		      vo.setUserPw(pwencoder.encode(vo.getUserPw()));
		      int result = service.register(vo);
		      if(result != 1) {
		         log.info("입력 실패");
		      }else {
		         log.info("입력 성공");
		      }
		
	    return "redirect:/board/list"; // 등록 후 목록으로 리다이렉트
	}

}
