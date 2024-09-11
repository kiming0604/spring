package org.jae.mapper;

import java.util.List;

import org.jae.domain.ReplyVO;

public interface ReplyMapper {
	
	// 댓글 삽입
	public int insert(ReplyVO rvo);
	// 댓글 목록
	public List<ReplyVO> getList(int bno); // 특정 게시물의 댓글들을 가져와야 하므로 int bno를 붙여준다
	//댓글 읽기
	public ReplyVO read(int rno);
	//댓글 삭제 
	public int delete(int rno);
	//댓글 수정
	public int update(ReplyVO rvo);

}
