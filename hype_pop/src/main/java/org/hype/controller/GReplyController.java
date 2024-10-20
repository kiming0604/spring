package org.hype.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hype.domain.gReplyVO;
import org.hype.service.GReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequestMapping("/gReply/")
public class GReplyController {
    
    @Autowired
    private GReplyService gService;
    
    @PostMapping(value = "/new", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> replyInsert(@RequestBody gReplyVO vo) {
        // �α׿� ��û ������ ���
        log.info("--Controller.insertGReply--" + vo);
        log.info("gNo: " + vo.getGno());
        log.info("userNo: " + vo.getUserNo());
        log.info("gComment: " + vo.getGcomment());
        log.info("gScore: " + vo.getGscore());
        int resultInsert = gService.insertGReply(vo);
		return resultInsert == 1 ?
				new ResponseEntity<String>("success", HttpStatus.OK) : 
				new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // ��α��� ���¿��� ��� ��� ��� �ҷ�����
//    @GetMapping(value = "/goodsDetails/{gNo}", produces = MediaType.TEXT_PLAIN_VALUE)
//    public 
    
    @GetMapping(value = "/{gno}/{userNo}/{page}/{size}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getReplyListWithPaging(
            @PathVariable("gno") int gno, 
            @PathVariable("userNo") int userNo,
            @PathVariable("page") int page, 
            @PathVariable("size") int size) {
        
        Map<String, Object> response = new HashMap<>();
        log.info("--Controller.getReplyListWithPaging gno:" + gno + " userNo:" + userNo + " page:" + page + " size:" + size);
        
        // �� ��۰� �ٸ� ����� ����� ó��
        Object myReply = gService.getMyReply(gno, userNo);
        int offset = (page - 1) * size;
        
        List<gReplyVO> replyList = gService.getAllReplyListWithPaging(gno, userNo, offset, size);
        
        int totalReplies = gService.getReplyCount(gno, userNo);  // ��ü ��� �� (�� ��� ����)
        
        for (gReplyVO vo : replyList) {
            int greplyNo = vo.getGreplyNo();  // getGreplyNo() �޼��带 ȣ���Ͽ� ��� ��ȣ�� ������
            System.out.println("��� ��ȣ: " + greplyNo);
        }
        
        response.put("myReply", myReply);
        response.put("replyList", replyList);
        response.put("totalReplies", totalReplies);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping(value = "/avgStars/{gno}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> goodsAvgStars(@PathVariable("gno") int gno) {
        // gService.getAvgStars()�� ��� ������ ��ȯ�ϴ� �޼���� ����
        double avgStars = gService.getAvgStars(gno);  // ��� ���� ��ȯ �޼���
        String avgStarsString = String.valueOf(avgStars);  // double ���� String���� ��ȯ
        
        return new ResponseEntity<>(avgStarsString, HttpStatus.OK);  // ���ڿ��� ��ȯ
    }
    
    // ����� �޾Ҵ��� Ȯ��
    @GetMapping(value = "/chkReplied", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Integer> chkReplied(@PathVariable("userNo") int userNo){
    	int result = gService.chkReplied(userNo);
    	return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/deleteReply/{gno}/{userNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> deleteReply(@PathVariable("gno") int gno, @PathVariable("userNo") int userNo){
    	
    	int result = gService.deleteReply(gno, userNo);
    	
    	return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @PutMapping(value = "/updateReply", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> updateReply(@RequestBody gReplyVO vo) {
        try {
            int result = gService.updateReply(vo);
            if (result == 1) {
                return ResponseEntity.ok("success");
            } else {
                return ResponseEntity.status(500).body("failure");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("error");
        }
    }
//	@PutMapping(value="/{rno}", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
//	public ResponseEntity<String> replyUpdate(@RequestBody ReplyVO vo, @PathVariable("rno") int rno){
//		log.info("--Controller.replyUpdate--rno:" + rno + "vo : "+ vo);
//		int resultUpdate = service.replyUpdate(vo);
//		log.info("resultUpdate : " + resultUpdate);
//		return resultUpdate == 1 ?
//				new ResponseEntity<String>("success", HttpStatus.OK) : 
//				new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
//	}
}