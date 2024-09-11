package org.jae.mapper;


import java.util.List;

import org.jae.domain.BoardVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class )
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml") // 이걸로 빈이 있는 위치를 잡아놓고
public class BoardMapperTests {

	@Autowired
	private BoardMapper mapper;
	
//	@Test
//	public void testGetList() {
//	  	List<BoardVO> list  = mapper.getList();
//	  	
//	  	for (BoardVO vo : list) {
//			log.warn(vo);
//		}
//		
//	}
//	@Test
//	public void insert() {
//		BoardVO bvo = new BoardVO();
//		bvo.setTitle("성냥팔이 양치기");
//		bvo.setContent("양사세요~ 양사세요~ 성냥팔이가 외쳤어요");
//		bvo.setWriter("거브나 잼이써");
//		
//	    mapper.insert(bvo);
//	 	List<BoardVO> list  = mapper.getList();
//	  	
//	  	for (BoardVO vo : list) {
//			log.warn(vo);
//		}
//	
//	  
//		
//	}
//	@Test
//	public void read() {
//		BoardVO bvo = new BoardVO();
//		bvo.setBno(1);
//		List<BoardVO> list  = mapper.read(bvo);
//	  	
//	  	for (BoardVO vo : list) {
//			log.warn(vo);
//		}
//		
//	}
//	
//	@Test
//	public void delete() {
//		BoardVO bvo = new BoardVO();
//		bvo.setBno(8);
//	    mapper.delete(bvo);
//	    
//	 	List<BoardVO> list  = mapper.getList();
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
//		bvo.setTitle("알리바바와 40인의 톰 소여");
//		bvo.setContent("옛날옛날 호랑이가 살았어요");
//		bvo.setWriter("기부니 조아스");
//	
//	    mapper.update(bvo);
//	    
//	    List<BoardVO> list  = mapper.getList();
//	  	
//	    for (BoardVO vo : list) {
//			log.warn(vo);
//		}
//	
//	}
}
