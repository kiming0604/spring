package org.hype.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hype.domain.psReplyVO;
import org.hype.mapper.PopUpReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService{
	
	@Autowired
	PopUpReplyMapper mapper;
	
	@Override
	public Integer insertPopUpReply(psReplyVO vo) {
		Integer result = mapper.insertPopUpReply(vo);
		return result;
	}
	@Override
	public List<psReplyVO> getUserReviews(int psNo, int userNo) {
	    Map<String, Integer> params = new HashMap<>();
	    params.put("psNo", psNo);
	    params.put("userNo", userNo);
	    
	    List<psReplyVO> result = mapper.getUserReviews(params);
	    return result;
	}

}
