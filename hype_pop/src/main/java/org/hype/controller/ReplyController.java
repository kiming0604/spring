package org.hype.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hype.domain.psReplyVO;
import org.hype.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequestMapping("/reply")
public class ReplyController {

   @Autowired
    private ReplyService service;
   
   @PostMapping("/insertReply")
   @ResponseBody
   public ResponseEntity<Map<String, String>> insertReply(@RequestBody Map<String, Object> requestData) {
       
       // String에서 int로 변환
       int psNo = Integer.parseInt((String) requestData.get("psNo"));
       int psScore = Integer.parseInt((String) requestData.get("rating"));
       String psComment = (String) requestData.get("reviewText");
       int userNo = Integer.parseInt((String) requestData.get("userNo"));

       System.out.println("psNo: " + psNo);
       System.out.println("rating: " + psScore);
       System.out.println("reviewText: " + psComment);
       System.out.println("userNo: " + userNo);
       
       psReplyVO vo = new psReplyVO();
       
       vo.setPsNo(psNo);
       vo.setPsScore(psScore);
       vo.setPsComment(psComment);
       vo.setUserNo(userNo);
       
       // 예시: likeCount를 가져오는 로직 (이 부분은 구현된 서비스에 맞게 수정 필요)
       Integer result = service.insertPopUpReply(vo);
      

       // 응답 데이터 생성
       Map<String, String> response = new HashMap<>();
       if (result != null && result > 0) {
           response.put("status", "success");
           response.put("message", "댓글이 성공적으로 등록되었습니다.");
       } else {
           response.put("status", "failure");
           response.put("message", "댓글 등록에 실패하였습니다.");
       }

       // JSON 응답 전송
       return ResponseEntity.ok()
           .contentType(MediaType.APPLICATION_JSON)
           .body(response);
   }

   @PostMapping("/getUserReviews")
   public ResponseEntity<Map<String, Object>> getUserReviews(@RequestBody Map<String, Integer> request) {
       // 요청 본문에서 psNo와 userNo 추출
       Integer psNo = request.get("psNo");
       Integer userNo = request.get("userNo");
       System.out.println("Received request: " + request);


       // 리뷰 가져오기
       List<psReplyVO> reviews = service.getUserReviews(psNo, userNo);

       // 응답 맵 구성
       Map<String, Object> response = new HashMap<>();
       response.put("status", "success");
       response.put("message", reviews.isEmpty() ? "리뷰가 없습니다." : "리뷰를 불러왔습니다.");
       response.put("reviews", reviews);

       return ResponseEntity.ok()
               .contentType(MediaType.APPLICATION_JSON)
               .body(response);
   }


}
