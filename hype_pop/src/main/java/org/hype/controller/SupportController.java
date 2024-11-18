package org.hype.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hype.domain.noticeVO;
import org.hype.domain.qnaVO;
import org.hype.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired
    @Qualifier("alarmController")  // 어떤 빈을 사용할지 명시
    private AlarmController alarmController;
    
    @GetMapping(value = "/notices", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getNoticeList(
        @RequestParam(defaultValue = "1") int pageNum,
        @RequestParam(defaultValue = "5") int amount) {
        List<noticeVO> notices = noticeService.getNoticesWithPaging(pageNum, amount);
        int totalCount = noticeService.getTotalNoticeCount(); 

        Map<String, Object> response = new HashMap<>();
        response.put("notices", notices); // 모든 공지사항
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
        int totalCount = noticeService.getTotalInquiryCount(userNo); // 전체 문의 사항 개수

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
        @RequestParam(required = false) Boolean answered) { // Boolean 값 처리

        Map<String, Object> response = new HashMap<>();
        
        List<qnaVO> inquiries;
        int totalCount;

        if (answered == null) {
            // 전체 조회
            inquiries = noticeService.getInquiriesWithPaging(pageNum, amount, userNo); // 전체 조회 기능 호출
            totalCount = noticeService.getTotalInquiryCount(userNo); // 전체 문의 사항 개수 호출
        } else {
            // 답변 여부 체크
            inquiries = noticeService.replyCheckInquiries(pageNum, amount, userNo, answered);
            totalCount = noticeService.replyCheckCount(userNo, answered);
        }

        response.put("inquiries", inquiries);
        response.put("totalCount", totalCount); 

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 공지 작성 페이지로 이동
    @GetMapping("/createNotice")
    public String createNotice() {
        
        return "/customerService/createNotice";
    }    
    
    @PostMapping("/createNotice") // 공지 작성
    public String createNotice(@RequestParam String title, 
                                @RequestParam String content, 
                                Model model) {
        noticeVO notice = new noticeVO();
        notice.setNoticeTitle(title);
        notice.setNoticeContent(content);

        boolean isSaved = noticeService.createNotice(notice); // 공지 저장

        if (isSaved) {
            model.addAttribute("message", "공지사항이 성공적으로 작성되었습니다.");
        } else {
            model.addAttribute("message", "공지사항 작성에 실패하였습니다.");
        }

        return "redirect:/hypePop/customerMain"; // 작성 후 페이지로 이동
    }
    
    // 문의 작성 페이지로 이동
    @GetMapping("/createInquiry")
    public String createInquiry() {
        
        return "/customerService/createInquiry";
    }
    
    @PostMapping("/createInquiry") // 문의 작성
    public String createInquiry(@RequestParam String title, @RequestParam String qnaType, 
                                @RequestParam String content, /*HttpSession session,*/ Model model) {
        // 사용자 번호 추출
//        Integer userNo = (Integer) session.getAttribute("userNo");
        
        // qnaVO 객체 생성 후 값 설정
        qnaVO qna = new qnaVO();
        qna.setQnaTitle(title);
        qna.setQnaType(qnaType);
        qna.setQnaContext(content);
        qna.setQnaAnswer("미답변");
        qna.setUserNo(1);
        
        // userNo 설정
//        qna.setUserNo(userNo); // userNo 값 설정

        // 문의 저장
        boolean isSaved = noticeService.createInquiry(qna); 
        int noticeNo = noticeService.getNoticeNo(title);
        

        if (isSaved) {
            model.addAttribute("message", "문의가 성공적으로 작성되었습니다.");
             alarmController.sendNoticeNotifications(noticeNo); // 알림 전송 메서드 호출
        } else {
            model.addAttribute("message", "문의 작성에 실패하였습니다.");
        }

        return "redirect:/hypePop/customerMain?tab=inquiry"; // 작성 후 페이지로 이동
    }

    @GetMapping("/noticeInfo") // 공지 상세보기
    public String noticeInfoList(@RequestParam("noticeNo") int noticeNo, Model model) {
         noticeVO noticeInfo = noticeService.getNoticeInfo(noticeNo);
         model.addAttribute("noticeInfo", noticeInfo);
         return "/customerService/noticeInfo";  
    }
    
    @GetMapping("/inquiryInfo") // 문의 상세보기
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
            // 공지 번호로 해당 공지 삭제
            noticeService.deleteNotice(noticeNo);

            // 성공 메시지 
            redirectAttributes.addFlashAttribute("message", "공지사항이 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            // 예외 처리
            log.error("공지 삭제 중 오류 발생", e);
            redirectAttributes.addFlashAttribute("message", "공지 삭제 중 오류가 발생하였습니다.");
        }
        
        return "redirect:/hypePop/customerMain";
    }
    
    @PostMapping("/updateAnswer")
    @ResponseBody
    public ResponseEntity<String> updateAnswer(@RequestBody qnaVO qna) {
        try {
            noticeService.updateAnswer(qna);
            return ResponseEntity.ok("답변이 성공적으로 수정되었습니다.");
        } catch (Exception e) {
            log.error("답변 수정 중 오류 발생", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("답변 수정 중 오류가 발생하였습니다.");
        }
    }

    @PostMapping("deleteInquiry")
    public String deleteInquiry(@RequestParam("qnaNo") int qnaNo, Model model) {
        try {
            noticeService.deleteInquiry(qnaNo); 
            return "redirect:/hypePop/customerMain?tab=inquiry"; 
        } catch (Exception e) {
            model.addAttribute("errorMessage", "문의 삭제 중 오류가 발생하였습니다.");
            return "errorPage"; 
        }
    }

    // 사용자 1:1 문의 사항 조회 (사용자별)
    @GetMapping(value = "/userInquiry", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getUserInquiryList(
            @RequestParam int userNo, // 사용자 번호 받아오기
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "5") int amount) {

        List<qnaVO> inquiries = noticeService.getUserInquiriesWithPaging(userNo, pageNum, amount);

        if (inquiries == null || inquiries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

       int totalCount = noticeService.getTotalInquiryCountByUser(userNo); // 전체 문의 사항 개수

        Map<String, Object> response = new HashMap<>();
        response.put("inquiries", inquiries);
        response.put("totalCount", totalCount); 
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
