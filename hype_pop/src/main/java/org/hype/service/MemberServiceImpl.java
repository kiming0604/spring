package org.hype.service;

import java.util.List;

import org.hype.domain.mCatVO;
import org.hype.domain.signInVO;
import org.hype.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	public MemberMapper mMapper;

	@Override
	public List<signInVO> getMList() {
		return mMapper.getMList();
	}

	@Override
	public List<signInVO> getListBySearchMs(String searchMs) {
		return mMapper.getListBySearchMs(searchMs);
	}

	@Override
	public signInVO getMembersById(String userId) {
		return mMapper.getMembersById(userId);
	}	
	
	// 윤씨 취합 부분 
    @Override
    public signInVO loginMember(signInVO svo) {
        log.info("login user: {}"+ svo);
        return mMapper.loginMember(svo);
    }

    @Transactional
    @Override
    public int joinMember(signInVO svo, mCatVO mcvo) {
        log.warn("joinMember 호출: " + svo);

        // 회원 정보 삽입 후 userNo 설정
        mMapper.joinMember(svo);
        log.warn("회원가입 후 userNo: " + svo.getUserNo());

        // userNo가 null인지 확인
        if (svo.getUserNo() == 0) {
            throw new RuntimeException("회원가입 후 userNo를 가져오지 못했습니다.");
        }

        // 관심사 삽입
        mcvo.setUserNo(svo.getUserNo());
        return mMapper.insertInterest(mcvo);
        

    }
    
    //아이디 중복 확인
    public boolean checkDuplicateId(String userId) {
        return mMapper.checkDuplicateId(userId) > 0; // userId 직접 전달
    }

	
}