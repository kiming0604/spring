package org.hype.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hype.domain.popStoreVO;
import org.hype.mapper.PopUpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PopUpServiceImpl implements PopUpService {
    
    @Autowired
    PopUpMapper mapper;

    public List<popStoreVO> getPopularPopUps() {
        return mapper.getPopularPopUps();
    }

    // 관심사에 따른 상위 스토어 조회
    public Map<String, List<popStoreVO>> getTopStoresByInterests(int userno) {
        List<String> interests = mapper.getUserInterests(userno);
        Map<String, List<popStoreVO>> results = new HashMap<>();

        if (interests != null && !interests.isEmpty()) {
            for (String interest : interests) {
                List<popStoreVO> topStores = mapper.getTopStoresByInterest(interest);
                results.put(interest, topStores); // 관심사와 관련된 상위 스토어 목록을 Map에 추가
            }
        }
        return results;
    }
    @Override
    public popStoreVO getStoreInfoByName(String storeName) {
    	
    	popStoreVO result = mapper.getStoreInfoByName(storeName);
    	
    	return result;
    }
}
