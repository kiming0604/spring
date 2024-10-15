package org.hype.service;

import java.util.List;

import org.hype.domain.psReplyVO;

public interface ReplyService {
	public Integer insertPopUpReply(psReplyVO vo);
	public List<psReplyVO> getUserReviews(int psNo, int userNo);

}
