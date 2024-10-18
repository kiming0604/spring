package org.hype.mapper;

import java.util.List;
import java.util.Map; // 추가된 import
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.hype.domain.likeVO;
import org.hype.domain.pCatVO;
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

   
  // 요셉이거 통합 부분
   public List<popStoreVO> showCalendar();

   public List<pCatVO> getCategoryData();
   
   // 서연씨 통합 부분 
	public List<popStoreVO> getList();  // 관리자 popup 리스트 가져오기
	public List<popStoreVO> getListBySearchPs(String searchPs);  // 관리자 검색 기능 추가
	public popStoreVO getPopStoreById (int psNo);  // 특정 팝업스토어 조회
//	public int psInsert(popStoreVO psvo); // 팝업스토어 등록
//	public int catInsert(pCatVO pcatvo); // 카테고리 등록
//	public int psDelete(popStoreVO psvo);
//	public int catDelete(pCatVO pcatvo);	

}
