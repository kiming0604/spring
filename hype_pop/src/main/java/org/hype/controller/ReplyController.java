//package org.hype.controller;
//
//import java.util.List;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import lombok.extern.log4j.Log4j;
//
//@Log4j
//@RestController
//@RequestMapping("/reply")
//public class ReplyController {
//
//    @Autowired
//    private ReplyService service;
//
//    // 1. 댓글 등록
//    @PostMapping(value = "/new", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
//    public ResponseEntity<String> create(@RequestBody ReplyVO rvo) {
//        log.info("replyVO : " + rvo);
//        
//        int insertCount = service.register(rvo);
//        
//        log.info("insertCount : " + insertCount);
//        return insertCount == 1 ? 
//            new ResponseEntity<>("success", HttpStatus.OK) :
//            new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    // 2. 댓글 목록 조회
//    @GetMapping(value = "pages/{psNo}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
//    public ResponseEntity<List<ReplyVO>> getList(@PathVariable("bno") int bno) {
//        log.info("getList...." + bno);
//        List<ReplyVO> replies = service.getList(bno);
//        return new ResponseEntity<>(replies, HttpStatus.OK);
//    }
//      




//    복사해서 자유롭게 댓글 불러오기에 쓰시면 됩니다! 조금 수정이 필요할거에요






//    // 3. 내 댓글 조회
//    @GetMapping(value = "/{userNo}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
//    public ResponseEntity<ReplyVO> get(@PathVariable("userNo") int userNo) {
//        log.info("get......." + userNo);
//        ReplyVO reply = service.get(userNo);
//        return new ResponseEntity<>(reply, HttpStatus.OK);
//    }
//
//    // 4. 댓글 삭제
//    @DeleteMapping(value = "/{userNo}", produces = MediaType.TEXT_PLAIN_VALUE)
//    public ResponseEntity<String> remove(@PathVariable("userNo") int userNo) {
//        log.info("remove......." + psNo);
//        return service.remove(psNo) == 1 ?
//            new ResponseEntity<>("success", HttpStatus.OK) :
//            new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    // 5. 댓글 수정
//    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}, value = "/{userNo}", produces = MediaType.TEXT_PLAIN_VALUE, consumes = "application/json")
//    public ResponseEntity<String> modify(@PathVariable("userNo") int userNo, @RequestBody psReplyVO rvo) {
//        log.info("rvo : " + rvo);
//        log.info("rno : " + rno);
//        
//        int modifyCount = service.modify(rvo);
//        
//        log.info("modifyCount : " + modifyCount);
//        
//        return modifyCount == 1 ? 
//            new ResponseEntity<>("success", HttpStatus.OK) :
//            new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}
