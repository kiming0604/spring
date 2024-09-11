package org.jae.controller;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.jae.domain.ReplyVO;
import org.jae.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequestMapping("/reply")
public class ReplyController {

	
	@Autowired
	private ReplyService service;
	
	/*
	    * 동작에 따른 url 방법(http 전송 방식)
	    * 1. 등록 - /reply/new - POST
	    * 2. 조회 - /reply/:rno - GET
	    * 3. 삭제 - /reply/:rno - DELETE
	    * 4. 수정 - /reply/:rno - PUT or PATCH
	    * 5. 전체 댓글 - /reply/pages/:bno - GET
	    *             : 은 경로에 데이터 실어서 보내겠다는 뜻 즉 bno를 보내겠다
	    * 
	    * == REST 방식으로 설계할 땐 PK 기준으로 작성하는 것이 좋다. PK 만으로 CRUD가 가능하기 때문
	    * == 다만 댓글 목록은 PK 만으론 안되고 bno와 페이지 번호 정보가 필요
	    */
	
	// 1.등록
	                                    //전달 받은 데이터의 타입  json타입이라는 뜻                 // 응답하는 데이터의 타입
	@PostMapping(value = "/new" , consumes = "application/json" , produces = MediaType.TEXT_PLAIN_VALUE)
	                                      // 전달받은 json을 자바에서 쓰기위해 받을때 바로 변환 매우 편함
	public ResponseEntity<String> create(@RequestBody ReplyVO rvo) {
		log.info("replyVO : " + rvo);
		
		int insertCount = service.register(rvo);
		
		log.info("insertCount : " + insertCount);
		                                 //response엔티티를 사용하면 success와같은 상태를 보내줄수있다
		return insertCount == 1 ? new ResponseEntity<String>("success" , HttpStatus.OK) :
								  new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 2.댓글 목록                 // bno값을 받겠다는 의미
	@GetMapping(value = "pages/{bno}" ,   //미디어 타입으로 응답 타입을 xml와 json둘다 하겠다는 뜻
			    produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,
				            MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<ReplyVO>> getList(
			@PathVariable("bno") int bno) {
		   log.info("getList...." + bno);
		return new ResponseEntity<List<ReplyVO>> (service.getList(bno) , HttpStatus.OK) ;
	}
	
	
	// 3.댓글 조회
	// 맨 위 주석을 보면 	    * 2. 조회 - /reply/:rno - GET 따라서 위 처럼  pages는 필요없음
	@GetMapping(value = "/{rno}" ,
			    produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,
			                MediaType.APPLICATION_XML_VALUE})
	 // replyVO 타입만 있으면 되니까 List는 빼주고
	public ResponseEntity<ReplyVO> get(
			                       // rno 받아준다
			                      @PathVariable("rno") int rno) {
		log.info("get......." + rno);
		
		ResponseEntity<ReplyVO> result = new ResponseEntity<ReplyVO> (service.get(rno) , HttpStatus.OK);
		
		return result;
		
	}
	// 4. 삭제  /reply/:rno - DELETE
     // 딜리트 매핑으로 바로 딜리트 동작 수행 가능
	@DeleteMapping(value = "/{rno}", produces = MediaType.TEXT_PLAIN_VALUE )
	public ResponseEntity<String> remove(@PathVariable("rno") int rno){
		log.info("remove......." + rno);
		return service.remove(rno) == 1 ?
				  // 1이면 성공 보내주고
				  new ResponseEntity<String>("success" , HttpStatus.OK) :
				  // 아니면 서버에러 띄워준다
				  new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 5. 수정 /reply/:rno - PUT or PATCH
	
	@RequestMapping(method = {RequestMethod.PUT , RequestMethod.PATCH},
					value = "/{rno}" ,
					produces = MediaType.TEXT_PLAIN_VALUE ,
					consumes = "application/json")
	public ResponseEntity<String> modify(
		   @PathVariable("rno") int rno ,
		   @RequestBody ReplyVO rvo) {
		log.info("rvo : " + rvo);
		log.info("rno : " + rno);
		
		int modifyCount = service.modify(rvo);
		
		log.info("modifyCount : " + modifyCount);
		
		return modifyCount == 1 ? 
				  // 1이면 성공 보내주고
				  new ResponseEntity<String>("success" , HttpStatus.OK) :
				  // 아니면 서버에러 띄워준다
				  new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
