package org.hype.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hype.domain.likeVO;
import org.hype.domain.popStoreVO;
import org.hype.domain.psReplyVO;
import org.hype.mapper.PopUpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    
    @Transactional
    @Override
    public likeVO likeCount(int psNo, int userNo) {
        // 좋아요 목록에서 현재 사용자의 좋아요 상태 확인
        Map<String, Integer> params = new HashMap<>();
        params.put("psNo", psNo);
        params.put("userNo", userNo);
        
        int likeStatus = mapper.checkLikeStatus(params);
        
        if (likeStatus == 0) {
            // 좋아요가 없는 경우, 팝업 스토어의 좋아요 수 증가
            mapper.incrementLikeCount(psNo); // 좋아요 수 1 증가
            
            // 좋아요 목록에 새로운 좋아요 기록 추가
            likeVO newLike = new likeVO();
            newLike.setPsNo(psNo);
            newLike.setUserNo(userNo);
            mapper.insertLike(newLike); // 새로운 좋아요 추가
            
            return newLike; // 새로운 좋아요 정보 반환
        } else {
            // 좋아요가 이미 있는 경우, 팝업 스토어의 좋아요 수 감소
            mapper.decrementLikeCount(psNo); // 좋아요 수 1 감소
            
            // 좋아요 목록에서 해당 기록 삭제
            likeVO likeInfo = new likeVO();
            likeInfo.setPsNo(psNo);
            likeInfo.setUserNo(userNo);
            mapper.deleteLike(likeInfo); // 좋아요 삭제
            
            return null; // 삭제된 경우 null 반환
        }
    }

@Override
public Integer getLikeCount(int psNo) {
	Integer result = mapper.getLikeCount(psNo);
	
	return result;
}

}
