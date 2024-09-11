package org.jae.mapper;

import java.util.List;

import org.jae.domain.BoardVO;
import org.jae.domain.Criteria;

public interface BoardMapper {
	 // 전체 데이터 조회
//     public List<BoardVO> getList();
     // 데이터 삽입 
     public int insert(BoardVO bvo);
     // 단일 데이터 - bno 값으로 데이터 조회
     public BoardVO read(int bno);  
     // 데이터 삭제 bno 값으로 삭제
     public int delete(int bno);
     // 데이터 수정 특정 bno의 title contect writer 수정
     public int update(BoardVO bvo);
	 
     public List<BoardVO> getList(Criteria cri);
	
     public int getTotal();
}
