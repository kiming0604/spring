package org.jae.mapper;


import java.util.List;


import org.jae.domain.ReplyVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class )
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml") // 이걸로 빈이 있는 위치를 잡아놓고
public class ReplyMapperTest {
//
	@Autowired
	private ReplyMapper mapper;
	
//	@Test
//	public void testGetList() {
//	  	List<ReplyVO> list  = mapper.getList(1);
//	  	
//	  	for (ReplyVO vo : list) {
//			log.warn(vo);
//		}
//		
//	}
//	@Test
//	public void insert() {
//		ReplyVO rvo = new ReplyVO();
//		rvo.setBno(1);
//		rvo.setReply("배부르다");
//		rvo.setReplyer("김정은");
//		z
//	    mapper.insert(rvo);
//	 	List<ReplyVO> list  = mapper.getList(1);
//	  	
//	  	for (ReplyVO vo : list) {
//			log.warn(vo);
//		}
//	}
	@Test
	public void read() {
	    // 1번 댓글을 읽어서 rvo에 저장
	    ReplyVO rvo = mapper.read(5); // mapper.read(1)은 단일 ReplyVO 객체를 반환

	    // for-each는 필요 없음, 단일 객체를 바로 출력
	    log.warn(rvo);
	}

//	@Test
//	public void delete() {
//		ReplyVO rvo = new ReplyVO();
//		rvo.setRno(4);
//	    mapper.delete(rvo.getRno());
//	    
//	 	List<ReplyVO> list  = mapper.getList(1);
//	  	
//	  	for (ReplyVO vo : list) {
//			log.warn(vo);
//		}
//	
//	}
//	@Test
//	public void update() {
//		ReplyVO rvo = new ReplyVO();
//		rvo.setRno(5);
//		rvo.setReply("피자 10판 먹은 썰 푼다 ㅋㅋㅋㅋ");
//		
//	
//	    mapper.update(rvo);
//	    
//	    List<ReplyVO> list  = mapper.getList(1);
//	  	
//	    for (ReplyVO vo : list) {
//			log.warn(vo);
//		}
//	
//	}
}
