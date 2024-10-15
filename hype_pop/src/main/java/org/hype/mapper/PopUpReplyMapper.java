package org.hype.mapper;

import java.util.List;
import java.util.Map;

import org.hype.domain.psReplyVO;

public interface PopUpReplyMapper {
     public Integer insertPopUpReply(psReplyVO vo);

	public List<psReplyVO> getUserReviews(Map<String, Integer> params);
}
