package org.hype.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hype.domain.noticeVO;
import org.hype.domain.qnaVO;
import org.hype.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/support")
public class SupportController {
	
	@Autowired
	private NoticeService noticeService;
	
	@GetMapping(value = "/notices", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getNoticeList(
	    @RequestParam(defaultValue = "1") int pageNum,
	    @RequestParam(defaultValue = "5") int amount) {
	    List<noticeVO> notices = noticeService.getNoticesWithPaging(pageNum, amount);
	    int totalCount = noticeService.getTotalNoticeCount(); 

	    Map<String, Object> response = new HashMap<>();
	    response.put("notices", notices);// �� ���� ����
	    response.put("totalCount", totalCount); 
	    
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/inquiry", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getInquiryList(
	    @RequestParam(defaultValue = "1") int pageNum,
	    @RequestParam(defaultValue = "5") int amount,
	    @RequestParam int userNo) {
	    List<qnaVO> inquiries = noticeService.getInquiriesWithPaging(pageNum, amount, userNo);
	    int totalCount = noticeService.getTotalInquiryCount(userNo); // �� ���� ���� ��������

	    Map<String, Object> response = new HashMap<>();
	    response.put("inquiries", inquiries);
	    response.put("totalCount", totalCount); 

	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(value = "/replyCheck", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> replyCheck(
	    @RequestParam(defaultValue = "1") int pageNum,
	    @RequestParam(defaultValue = "5") int amount,
	    @RequestParam int userNo,
	    @RequestParam(required = false) Boolean answered) { // Boolean���� ����

	    Map<String, Object> response = new HashMap<>();
	    
	    List<qnaVO> inquiries;
	    int totalCount;

	    if (answered == null) {
	        // ��ü����
	        inquiries = noticeService.getInquiriesWithPaging(pageNum, amount, userNo); // ��ü ��ȸ �޼��� ȣ��
	        totalCount = noticeService.getTotalInquiryCount(userNo); // ��ü ���� ��ȸ �޼��� ȣ��
	    } else {
	        // �亯 ���¿� ���� ��ȸ
	        inquiries = noticeService.replyCheckInquiries(pageNum, amount, userNo, answered);
	        totalCount = noticeService.replyCheckCount(userNo, answered);
	    }

	    response.put("inquiries", inquiries);
	    response.put("totalCount", totalCount); 

	    return new ResponseEntity<>(response, HttpStatus.OK);
	}


	// �������� �ۼ� �������� �̵�
	@GetMapping("/createNotice")
	public String createNotice() {
		
		return "/customerService/createNotice";
	}	
	
	@PostMapping("/createNotice") // ���� ����
    public String createNotice(@RequestParam String title, 
                                @RequestParam String content, 
                                Model model) {
        noticeVO notice = new noticeVO();
        notice.setNoticeTitle(title);
        notice.setNoticeContent(content);

        boolean isSaved = noticeService.createNotice(notice); // �������� ����

        if (isSaved) {
            model.addAttribute("message", "���������� ���������� ����Ǿ����ϴ�.");
        } else {
            model.addAttribute("message", "�������� ���忡 �����Ͽ����ϴ�.");
        }

        return "redirect:/hypePop/customerMain"; // �������� ����Ʈ�� �����̷�Ʈ
    }
	
	// ���� �ۼ� �������� �̵�
	@GetMapping("/createInquiry")
	public String createInquiry() {
		
		return "/customerService/createInquiry";
	}
	
	@PostMapping("/createInquiry") // ���� ����
	public String createInquiry(@RequestParam String title, @RequestParam String qnaType, 
	                            @RequestParam String content, /*HttpSession session,*/ Model model) {
	    // ���ǿ��� userNo ��������
//	    Integer userNo = (Integer) session.getAttribute("userNo");
	    
	    // qnaVO ��ü ���� �� �ʵ� ����
	    qnaVO qna = new qnaVO();
	    qna.setQnaTitle(title);
	    qna.setQnaType(qnaType);
	    qna.setQnaContext(content);
	    qna.setQnaAnswer("�亯����");
	    qna.setUserNo(1);
	    
	    // userNo ����
//	    qna.setUserNo(userNo); // userNo�� qnaVO�� �����ؾ� �ϴ� �޼��� �߰� �ʿ�

	    // ���� ����
	    boolean isSaved = noticeService.createInquiry(qna); 

	    if (isSaved) {
	        model.addAttribute("message", "���ǰ� ���������� ����Ǿ����ϴ�.");
	    } else {
	        model.addAttribute("message", "���� ���忡 �����Ͽ����ϴ�.");
	    }

	    return "redirect:/hypePop/customerMain?tab=inquiry"; // ���ǻ��� ����Ʈ�� �����̷�Ʈ
	}


	@GetMapping("/noticeInfo") // ���� �� ����
	public String noticeInfoList(@RequestParam("noticeNo") int noticeNo, Model model) {
		 noticeVO noticeInfo = noticeService.getNoticeInfo(noticeNo);
	     model.addAttribute("noticeInfo", noticeInfo);
	     return "/customerService/noticeInfo";  
	}
	
	@GetMapping("/inquiryInfo") // ���� �� ����
	public String inquiryInfoList(@RequestParam("qnaNo") int qnaNo, Model model) {
		qnaVO inquiryInfo = noticeService.getInquiryInfo(qnaNo);
		model.addAttribute("inquiryInfo", inquiryInfo);
		return "/customerService/inquiryInfo";
	}
	
	@PostMapping("/updateNotice")
	public String updateNotice(@ModelAttribute("noticeInfo") noticeVO nvo) {
		
	    noticeService.updateNotice(nvo);
	  
	    return "redirect:/hypePop/customerMain"; 
	}
	
	@PostMapping("/deleteNotice")
	public String deleteNotice(@RequestParam("noticeNo") int noticeNo, RedirectAttributes redirectAttributes) {
		try {
	        // ���� ȣ���Ͽ� noticeNo�� �ش��ϴ� ���� ����
	        noticeService.deleteNotice(noticeNo);

	        // ���� �޽��� 
	        redirectAttributes.addFlashAttribute("message", "���� ������ ���������� �Ϸ�Ǿ����ϴ�.");
	    } catch (Exception e) {
	    	// ���� �޼���
	        log.error("���� ���� �� ���� �߻�", e);
	        redirectAttributes.addFlashAttribute("message", "���� ���� �� ������ �߻��߽��ϴ�.");
	    }
		
	    return "redirect:/hypePop/customerMain";
	}
	
	@PostMapping("/updateAnswer")
	@ResponseBody
	public ResponseEntity<String> updateAnswer(@RequestBody qnaVO qna) {
	    try {
	        noticeService.updateAnswer(qna);
	        return ResponseEntity.ok("�亯�� ���������� ������Ʈ�Ǿ����ϴ�.");
	    } catch (Exception e) {
	        log.error("�亯 ������Ʈ �� ���� �߻�", e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("�亯 ������Ʈ �� ������ �߻��߽��ϴ�.");
	    }
	}

	@PostMapping("deleteInquiry")
	public String deleteInquiry(@RequestParam("qnaNo") int qnaNo, Model model) {
	    try {
	        noticeService.deleteInquiry(qnaNo); 
	        return "redirect:/hypePop/customerMain?tab=inquiry"; 
	    } catch (Exception e) {
	        model.addAttribute("errorMessage", "���� ���� �� ������ �߻��߽��ϴ�.");
	        return "errorPage"; 
	    }
	}
	  //Ư�� ���� 1:1 ���Ǳ� ��������(��)
    @GetMapping(value = "/userInquiry", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getUserInquiryList(
            @RequestParam int userNo, // ��û �Ķ���ͷ� userNo�� ����
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "5") int amount) {

        List<qnaVO> inquiries = noticeService.getUserInquiriesWithPaging(userNo, pageNum, amount);

        if (inquiries == null || inquiries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

       int totalCount = noticeService.getTotalInquiryCountByUser(userNo); // �� ���� ���� ��������


        Map<String, Object> response = new HashMap<>();
        response.put("inquiries", inquiries);
        response.put("totalCount", totalCount);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // Ư�� ���� ���Ǳ� ���� ��������
    @GetMapping(value = "/getInquiryCounts", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Integer> getInquiryCounts(@RequestParam int userNo) {
        Map<String, Integer> inquiryCounts = noticeService.getInquiryCounts(userNo);
        return inquiryCounts;
    }

    	
}