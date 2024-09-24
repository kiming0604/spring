package org.jae.controller;

import java.util.List;

import org.jae.domain.BoardAttachVO;
import org.jae.domain.BoardVO;
import org.jae.domain.Criteria;
import org.jae.domain.PageDTO;
import org.jae.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.jdi.connect.AttachingConnector;

import lombok.extern.log4j.Log4j;


@Log4j
@Controller
@RequestMapping("/board/*")  // 이걸로 board로 시작하는 친구들은 전부 오게 되어있음
public class BoardController {
	/* 
	 * Task        	- URL 		     -Method       -Parameter 
	 * 전체 목록        /board/list          -get
	 * 등록		 /board/register 	  -post           모든항목
	 * 조회              /board/read          -get            bno
	 * 삭제              /board/remove        -post           bno
	 * 수정              /board/modify        -post           모든항목
	 * */
	
	@Autowired
	private BoardService service;
	
	// 전체 게시글 조회 - 기존 방식
//	@RequestMapping("/list")
//	public String list(Model model) {
//		log.info("list...");
//		model.addAttribute("list" , service.getList());
//		
//		
//		return "/board/list";
//	}
	
	@GetMapping("/list")
	public String list(Model model , Criteria cri) {
		log.info("list...");
		if (cri.getPageNum() == 0 || cri.getAmount() == 0) {
			cri.setAmount(5);
			cri.setPageNum(1);
		}
		int total = service.getTotal();
		model.addAttribute("pageMaker", new PageDTO(cri,total));
		model.addAttribute("list" , service.getList(cri));
		System.out.println("--" + cri.getPageNum());
		return "board/list";
	}
	//게시글 등록
	@PostMapping("/register")
	public String register(BoardVO vo , RedirectAttributes rttr) {
		log.info("register..." + vo);
		
		service.register(vo);
		
		if (vo.getAttachList() != null) {
			vo.getAttachList().forEach(attach -> log.info("잘 넘어옵니다 : " + attach));
		}
		
		rttr.addFlashAttribute("result" , "success");
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/register")
	public String register2() {
		log.info("register...2");
	
		return "/board/register";
	}
	
	
	// 게시글 조회
	@GetMapping({"/get" , "/modify"})
	public void get(@RequestParam("bno") int bno , Model model) {
		log.info("get..." + bno);
		model.addAttribute("vo" , service.get(bno));
		
	}
	// 게시글 수정
	@PostMapping("/modify")
	public String modify(BoardVO vo) {
		log.info("modify..." + vo);
		service.modify(vo);
		return "redirect:/board/list";
	}
	
   //게시글 삭제
	@PostMapping("/remove") // get인지 포워드인지
	public String remove(@RequestParam("bno") int bno) {  //기존의 request.getparam
		log.info("remove..." + bno);
		service.remove(bno); // 불러주고
		return "redirect:/board/list"; // 기존의 path
	}
	
	// 첨부 파일 리스트 조회
	
	@ResponseBody
	@GetMapping(value = "/getAttachList/{bno}" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<BoardAttachVO>> getAttachList(@PathVariable("bno") int bno){
		log.info("getAttachList..." + bno);
		return new ResponseEntity<List<BoardAttachVO>>(service.getAttachList(bno) ,HttpStatus.OK);
	}
}
