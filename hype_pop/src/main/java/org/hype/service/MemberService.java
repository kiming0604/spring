package org.hype.service;

import java.util.List;

import org.hype.domain.mCatVO;
import org.hype.domain.signInVO;

public interface MemberService {
	public List<signInVO> getMList();  // 관리자 회원 리스트 가져오기
	public List<signInVO> getListBySearchMs(String searchMs);  // 관리자 검색 기능 추가
	public signInVO getMembersById (String userId);  // 특정 회원 조회
	
	
	// 윤씨 취합 부분
	public signInVO loginMember(signInVO svo);

	public int joinMember(signInVO svo, mCatVO mcvo);
	
	public boolean checkDuplicateId(String userId);
}