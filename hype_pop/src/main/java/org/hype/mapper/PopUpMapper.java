package org.hype.mapper;

import java.util.List;
import java.util.Map; // 추가된 import
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.hype.domain.likeVO;
import org.hype.domain.popStoreVO;
import org.hype.domain.psReplyVO;

public interface PopUpMapper {
   public List<popStoreVO> getPopularPopUps();

   public List<String> getUserInterests(int userno);

   public List<popStoreVO> getTopStoresByInterest(@Param("value") String interest);
    
   public popStoreVO getStoreInfoByName(String storeName);

   int checkLikeStatus(Map<String, Integer> params);
   
   // 좋아요 추가
   public void insertLike(likeVO newLike);
   
   // 좋아요 수 증가
   public void incrementLikeCount(int psNo);
   
   // 좋아요 수 감소
   public void decrementLikeCount(int psNo);
   
   // 좋아요 삭제
   public void deleteLike(likeVO likeInfo);

   public Integer getLikeCount(int psNo);


}
