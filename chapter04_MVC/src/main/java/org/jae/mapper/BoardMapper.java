package org.jae.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jae.domain.BoardVO;
import org.jae.domain.Criteria;

public interface BoardMapper {
	 // 전체 데이터 조회
//     public List<BoardVO> getList();
     // 데이터 삽입 
//	 public List<BoardVO> getOneList();
	
     public int insert(BoardVO bvo);
     // 단일 데이터 - bno 값으로 데이터 조회
     public BoardVO read(int bno);  
     // 데이터 삭제 bno 값으로 삭제
     public int delete(int bno);
     // 데이터 수정 특정 bno의 title contect writer 수정
     public int update(BoardVO bvo);
	 
     public List<BoardVO> getList(Criteria cri);
	// 전체 게시글 수
     public int getTotal();
     // 댓글 수 수정
     public int updateReplyCnt(@Param("amount") int amount, @Param("bno") int bno);
	


}
