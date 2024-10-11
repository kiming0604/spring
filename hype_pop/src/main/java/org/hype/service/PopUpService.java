package org.hype.service;

import java.util.List;
import java.util.Map;

import org.hype.domain.popStoreVO;

public interface PopUpService {
    public List<popStoreVO> getPopularPopUps();
    public Map<String, List<popStoreVO>> getTopStoresByInterests(int userno);
    public popStoreVO getStoreInfoByName(String storeName);
}
