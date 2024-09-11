package org.jae.service;

import java.util.List;

import org.jae.domain.ReplyVO;

public interface ReplyService {
	public int register(ReplyVO rvo);
	public List<ReplyVO> getList(int bno);
	public ReplyVO get(int rno);
	public int modify(ReplyVO rvo);
	public int remove(int rno); 

}
