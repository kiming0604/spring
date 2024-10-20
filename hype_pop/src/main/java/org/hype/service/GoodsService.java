package org.hype.service;

import java.util.List;
import java.util.Map;

import org.hype.domain.gCatVO;
import org.hype.domain.goodsVO;

public interface GoodsService {
	public List<goodsVO> getGList();  // 관리자 상품 리스트 가져오기
	public List<goodsVO> getListBySearchGs(String searchGs);  // 관리자 검색 기능 추가
	public goodsVO getGoodsById (int gNo);  // 특정 굿즈(상품) 조회
	
	
	
	
	// 진환이 형님 취합 부분
	public List<goodsVO> getListByLikeCount();
	public Map<String, Object> getListByInterestOneNotLogin();
	public Map<String, Object> getListByInterestTwoNotLogin();
	public Map<String, Object> getListByInterestThreeNotLogin();
	public List<goodsVO> getListByInterestOneLogined();
	public List<goodsVO> getListByInterestTwoLogined();
	public List<goodsVO> getListByInterestThreeLogined();
	public goodsVO getOneByGno(int gno);
	public List<goodsVO> getSearchList(String searchText, int offset, int limit) ;
	public gCatVO getCategory(int gno);
	public int getUpdatehit(goodsVO vo);
	public int updateLike(int userNo, int gno);
	public int getLikeCount(int gno);
	public int getLikeChk(int userNo, int gno);
}