package org.hype.controller;

import org.hype.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController

@RequestMapping("/member/api")
public class MemberRestController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/checkUserId")
    public ResponseEntity<String> checkUserId(String userId) {
        log.info("userId : " + userId);
        
        boolean isDuplicate = memberService.checkDuplicateId(userId);
        
        log.info("isDuplicate : " + isDuplicate);
        
        if (isDuplicate) {
            return new ResponseEntity<>("no", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("ok", HttpStatus.OK);
        }
    }

    @GetMapping("/getPolicyContent")
    public ResponseEntity<String> getPolicyContent(@RequestParam String type) {
        String content = ""; // Initialize content

        switch (type) {
            case "privacy":
                content += "<h2>개인정보 처리방침</h2>"
                        + "<p>본 개인정보 처리방침은 귀하의 개인정보가 어떻게 수집, 사용, 저장 및 보호되는지를 설명합니다.</p>"
                        + "<h3>1. 개인정보의 수집</h3>"
                        + "<p>우리는 회원가입 및 서비스 이용 과정에서 다음과 같은 개인정보를 수집합니다:</p>"
                        + "<ul>"
                        + "<li>이름</li>"
                        + "<li>이메일 주소</li>"
                        + "<li>전화번호</li>"
                        + "<li>사용자 ID 및 비밀번호</li>"
                        + "</ul>"
                        + "<h3>2. 개인정보의 사용</h3>"
                        + "<p>우리는 수집된 개인정보를 다음과 같은 목적으로 사용합니다:</p>"
                        + "<ul>"
                        + "<li>회원 관리 및 서비스 제공</li>"
                        + "<li>이벤트 및 프로모션 정보 제공</li>"
                        + "<li>고객 상담 및 민원 처리</li>"
                        + "</ul>"
                        + "<h3>3. 개인정보의 보관 및 보호</h3>"
                        + "<p>우리는 귀하의 개인정보를 안전하게 보호하며, 정해진 기간 동안만 보관합니다.</p>"
                        + "<h3>4. 개인정보의 제3자 제공</h3>"
                        + "<p>우리는 귀하의 동의 없이 개인정보를 제3자에게 제공하지 않습니다.</p>"
                        + "<h3>5. 개인정보 보호 책임자</h3>"
                        + "<p>개인정보 보호와 관련하여 궁금한 사항은 다음의 담당자에게 문의해 주시기 바랍니다:</p>"
                        + "<p>담당자: hypepop</p>"
                        + "<p>연락처: hypepop@hypepop.com</p>"
                        + "<h3>6. 본 방침의 변경</h3>"
                        + "<p>본 개인정보 처리방침은 변경될 수 있으며, 변경 사항은 즉시 공지합니다.</p>"
                        + "<p>마지막 업데이트: 2024년 10월 18일</p>";
                break;
            case "location":
                content += "<div id='locationModal' class='modal'>" +
                        "<div class='modal-content'>" +
                        "<span class='close' onclick=\"closeModal('locationModal')\">&times;</span>" +
                        "<div id='modalContent'>" +
                        "<h1>위치기반 서비스 이용약관</h1>" +
                        "<h2>제 1 조 (목적)</h2>" +
                        "<p>이 약관은 hypepop(이하 “회사”)가 제공하는 위치기반서비스와 관련하여 회사와 개인위치정보주체와의 권리, 의무 및 책임사항, 기타 필요한 사항을 규정함을 목적으로 합니다.</p>" +
                        // ... (Add remaining content here)
                        "</div></div></div>";
                break;
            case "notification":
                content += "<h2>마케팅 알림 동의</h2>"
                        + "<p>회사는 귀하에게 마케팅 관련 알림을 보내기 위해 사전에 귀하의 동의를 받습니다. 법률에 따라 동의가 필요한 경우, 우리는 귀하의 개인 정보를 보호하고 안전하게 처리합니다.</p>"
                        + "<h3>1. 마케팅 알림의 수신 동의</h3>"
                        + "<p>마케팅 알림은 이메일, SMS, 앱 알림 등의 방법으로 전송될 수 있습니다. 귀하의 동의가 없이는 마케팅 알림을 전송하지 않습니다.</p>"
                        + "<h3>2. 동의 철회</h3>"
                        + "<p>귀하는 언제든지 마케팅 알림 수신에 대한 동의를 철회할 수 있으며, 이 경우 더 이상 마케팅 알림을 받지 않게 됩니다.</p>"
                        + "<h3>3. 문의</h3>"
                        + "<p>마케팅 알림과 관련하여 문의사항이 있을 경우, 아래의 연락처로 문의해 주시기 바랍니다:</p>";
                break;
            default:
                content = "유효하지 않은 요청입니다.";
                break;
        }

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf("text/html; charset=UTF-8")) // 적절한 미디어 타입 설정
                .body(content);
    }
}