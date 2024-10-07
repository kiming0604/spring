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
	    * �룞�옉�뿉 �뵲瑜� url 諛⑸쾿(http �쟾�넚 諛⑹떇)
	    * 1. �벑濡� - /reply/new - POST
	    * 2. 議고쉶 - /reply/:rno - GET
	    * 3. �궘�젣 - /reply/:rno - DELETE
	    * 4. �닔�젙 - /reply/:rno - PUT or PATCH
	    * 5. �쟾泥� �뙎湲� - /reply/pages/:bno - GET
	    *             : �� 寃쎈줈�뿉 �뜲�씠�꽣 �떎�뼱�꽌 蹂대궡寃좊떎�뒗 �쑜 利� bno瑜� 蹂대궡寃좊떎
	    * 
	    * == REST 諛⑹떇�쑝濡� �꽕怨꾪븷 �븧 PK 湲곗��쑝濡� �옉�꽦�븯�뒗 寃껋씠 醫뗫떎. PK 留뚯쑝濡� CRUD媛� 媛��뒫�븯湲� �븣臾�
	    * == �떎留� �뙎湲� 紐⑸줉�� PK 留뚯쑝濡� �븞�릺怨� bno�� �럹�씠吏� 踰덊샇 �젙蹂닿� �븘�슂
	    */
	
	// 1.�벑濡�
	                                    //�쟾�떖 諛쏆� �뜲�씠�꽣�쓽 ���엯  json���엯�씠�씪�뒗 �쑜                 // �쓳�떟�븯�뒗 �뜲�씠�꽣�쓽 ���엯
	@PostMapping(value = "/new" , consumes = "application/json" , produces = MediaType.TEXT_PLAIN_VALUE)
	                                      // �쟾�떖諛쏆� json�쓣 �옄諛붿뿉�꽌 �벐湲곗쐞�빐 諛쏆쓣�븣 諛붾줈 蹂��솚 留ㅼ슦 �렪�븿
	public ResponseEntity<String> create(@RequestBody ReplyVO rvo) {
		log.info("replyVO : " + rvo);
		
		int insertCount = service.register(rvo);
		
		log.info("insertCount : " + insertCount);
		                                 //response�뿏�떚�떚瑜� �궗�슜�븯硫� success��媛숈� �긽�깭瑜� 蹂대궡以꾩닔�엳�떎
		return insertCount == 1 ? new ResponseEntity<String>("success" , HttpStatus.OK) :
								  new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 2.�뙎湲� 紐⑸줉                 // bno媛믪쓣 諛쏄쿋�떎�뒗 �쓽誘�
	@GetMapping(value = "pages/{bno}" ,   //誘몃뵒�뼱 ���엯�쑝濡� �쓳�떟 ���엯�쓣 xml�� json�몮�떎 �븯寃좊떎�뒗 �쑜
			    produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,
				            MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<ReplyVO>> getList(
			@PathVariable("bno") int bno) {
		   log.info("getList...." + bno);
		return new ResponseEntity<List<ReplyVO>> (service.getList(bno) , HttpStatus.OK) ;
	}
	
	
	// 3.�뙎湲� 議고쉶
	// 留� �쐞 二쇱꽍�쓣 蹂대㈃ 	    * 2. 議고쉶 - /reply/:rno - GET �뵲�씪�꽌 �쐞 泥섎읆  pages�뒗 �븘�슂�뾾�쓬
	@GetMapping(value = "/{rno}" ,
			    produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,
			                MediaType.APPLICATION_XML_VALUE})
	 // replyVO ���엯留� �엳�쑝硫� �릺�땲源� List�뒗 鍮쇱＜怨�
	public ResponseEntity<ReplyVO> get(
			                       // rno 諛쏆븘以��떎
			                      @PathVariable("rno") int rno) {
		log.info("get......." + rno);
		
		ResponseEntity<ReplyVO> result = new ResponseEntity<ReplyVO> (service.get(rno) , HttpStatus.OK);
		
		return result;
		
	}
	// 4. �궘�젣  /reply/:rno - DELETE
     // �뵜由ы듃 留ㅽ븨�쑝濡� 諛붾줈 �뵜由ы듃 �룞�옉 �닔�뻾 媛��뒫
	@DeleteMapping(value = "/{rno}", produces = MediaType.TEXT_PLAIN_VALUE )
	public ResponseEntity<String> remove(@PathVariable("rno") int rno){
		log.info("remove......." + rno);
		return service.remove(rno) == 1 ?
				  // 1�씠硫� �꽦怨� 蹂대궡二쇨퀬
				  new ResponseEntity<String>("success" , HttpStatus.OK) :
				  // �븘�땲硫� �꽌踰꾩뿉�윭 �쓣�썙以��떎
				  new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 5. �닔�젙 /reply/:rno - PUT or PATCH
	
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
				  // 1�씠硫� �꽦怨� 蹂대궡二쇨퀬
				  new ResponseEntity<String>("success" , HttpStatus.OK) :
				  // �븘�땲硫� �꽌踰꾩뿉�윭 �쓣�썙以��떎
				  new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
