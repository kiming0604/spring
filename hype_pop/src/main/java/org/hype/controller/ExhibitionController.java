package org.hype.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hype.domain.Criteria;
import org.hype.domain.PageDTO;
import org.hype.domain.goodsVO;
import org.hype.domain.popStoreVO;
import org.hype.domain.qnaVO;
import org.hype.domain.signInVO;
import org.hype.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/exhibition")
public class ExhibitionController {
	  
	
	@RequestMapping(value = "/exhibitionMain", method = RequestMethod.GET)
	    public String goExhibitionMain() {
	    	
	        return "exhibition/exhibitionMain"; // JSP 페이지 이름 반환
	    }
	
}