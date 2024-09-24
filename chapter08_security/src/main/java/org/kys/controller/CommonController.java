package org.kys.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class CommonController {

	@GetMapping("/accessError")
	public String accessDenied(Authentication auth, Model model) {
		log.info("Access Denied : " + auth);
		model.addAttribute("msg", "Access Denied");
		return "/accessError";
	}
	
	@GetMapping("/customLogin")
	public String loginInput(String err, String logout, Model model) {
		log.info("error : " + err);
		log.info("logout : " + logout);
		
		if (err != null) {
			model.addAttribute("error", "Login Error Check your Account");
		}
		if (logout != null) {
			model.addAttribute("logout", "Logout!");
		}
		
		return "/customLogin";
	}
	@GetMapping("/customLogout")
	public String logoutGET() {
		log.info("custom logout");
		return "customLogout";
	}
	
}
