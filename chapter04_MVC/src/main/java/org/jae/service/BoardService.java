package org.jae.service;

import java.util.List;

import org.jae.domain.BoardAttachVO;
import org.jae.domain.BoardVO;
import org.jae.domain.Criteria;

public interface BoardService {
	// 전체 리스트
//	public List<BoardVO> getOneList();
	// 데이터 삽입
	public int register(BoardVO vo);
	// 데이터 수정
	public int modify(BoardVO vo);
	
	// 데이터 삭제
	public int remove(int bno);
	// 단일 데이터
	public BoardVO get(int bno);
	
	// 전체 보기 페이징
	public List<BoardVO> getList(Criteria cri);
	
	public int getTotal();
	
	// 첨부 파일 리스트
	public List<BoardAttachVO> getAttachList(int bno);
	

}
