package org.jae.service;

import java.util.List;

import org.jae.domain.ReplyVO;
import org.jae.mapper.BoardMapper;
import org.jae.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

@Log4j
@Service   // 서비스는 반드시 반드시 반드시 어노테이션 달아주기
public class ReplyServiceImpl implements ReplyService {
	@Autowired
	private ReplyMapper mapper;
	
	@Autowired BoardMapper boardMapper;
	

	
	@Transactional
	@Override
	public int register(ReplyVO rvo) {
	   log.info("register..." + rvo);
	   // replycnt 값 증가
	    boardMapper.updateReplyCnt(1, rvo.getBno());
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

	@Transactional
	@Override
	public int remove(int rno) {
	    
	    log.info("remove.......");

	  
	    ReplyVO rvo = mapper.read(rno);
	    
	    if (rvo == null) {
	        return 0;
	    }

	    // 게시글의 댓글 수 감소
	    boardMapper.updateReplyCnt(-1, rvo.getBno());

	    // 댓글 삭제
	    return mapper.delete(rno);
	}

}
