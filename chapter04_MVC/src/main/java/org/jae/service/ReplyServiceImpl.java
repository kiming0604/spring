package org.jae.service;

import java.util.List;

import org.jae.domain.ReplyVO;
import org.jae.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;

@Log4j
@Service   // 서비스는 반드시 반드시 반드시 어노테이션 달아주기
public class ReplyServiceImpl implements ReplyService {
	@Autowired
	private ReplyMapper mapper;
	
	@Override
	public int register(ReplyVO rvo) {
	   log.info("register..." + rvo);
		return mapper.insert(rvo);
	}
	@Override
	public List<ReplyVO> getList(int bno) {
		log.info("getList...");
		return mapper.getList(bno);
	}
	@Override
	public ReplyVO get(int rno) {
		log.info("get...");
		return mapper.read(rno);
	}
	@Override
	public int modify(ReplyVO rvo) {
		log.info("modify...");
		return mapper.update(rvo);
	}
	@Override
	public int remove(int rno) {
		log.info("remove.......");
		return mapper.delete(rno);
	}
}
