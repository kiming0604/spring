package org.jae.service;

import java.util.List;

import org.jae.domain.BoardVO;
import org.jae.mapper.BoardMapperTests;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class )
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml") // 이걸로 빈이 있는 위치를 잡아놓고
public class BoardServiceTests {

	@Autowired
	private BoardService service;
	
//	@Test
//	public void testGetList() {
//	  	List<BoardVO> list  = service.getList();
//	  	
//	  	for (BoardVO vo : list) {
//			log.warn(vo);
//		}
//		
//	}
//	
//	@Test
//	public void insert() {
//		BoardVO bvo = new BoardVO();
//		bvo.setTitle("성냥팔이 양치기");
//		bvo.setContent("양사세요~ 양사세요~ 성냥팔이가 외쳤어요");
//		bvo.setWriter("거브나 잼이써");
//		
//	    service.register(bvo);
//	 	List<BoardVO> list  = service.getList();
//	  	
//	  	for (BoardVO vo : list) {
//			log.warn(vo);
//		}
//	
//	}
//	@Test
//	public void update() {
//		BoardVO bvo = new BoardVO();
//		bvo.setBno(7);
//		bvo.setTitle("선녀와 심봉사");
//		bvo.setContent("중립국...");
//		bvo.setWriter("기부니 나빠스");
//	
//	    service.modify(bvo);
//	    
//	    List<BoardVO> list  = service.getList();
//	  	
//	    for (BoardVO vo : list) {
//			log.warn(vo);
//		}
//
//	}
//	@Test
//	public void delete() {
//		BoardVO bvo = new BoardVO();
//		bvo.setBno(7);
//	    service.remove(bvo.getBno());
//	    
//	 	List<BoardVO> list  = service.getList();
//	  	
//	  	for (BoardVO vo : list) {
//			log.warn(vo);
//		}
//	
//	}
	@Test
	public void read() {
	    BoardVO bvo = new BoardVO();
	    bvo.setBno(21);
	    BoardVO vo = service.get(bvo.getBno()); // 단일 객체 반환

	    if (vo != null) {
	        log.warn(vo);
	    }
	}

}
