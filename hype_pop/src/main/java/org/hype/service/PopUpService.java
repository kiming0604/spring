package org.hype.service;

import java.util.List;
import java.util.Map;

import org.hype.domain.likeVO;
import org.hype.domain.popStoreVO;
import org.hype.domain.psReplyVO;

public interface PopUpService {
    public List<popStoreVO> getPopularPopUps();
    public Map<String, List<popStoreVO>> getTopStoresByInterests(int userno);
    public popStoreVO getStoreInfoByName(String storeName);
    public likeVO likeCount(int psNo, int userNo);
	public Integer getLikeCount(int psNo);
}
