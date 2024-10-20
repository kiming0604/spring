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
	
	// ���� ���� �κ� 
    @Override
    public signInVO loginMember(signInVO svo) {
        log.info("login user: {}"+ svo);
        return mMapper.loginMember(svo);
    }

    @Transactional
    @Override
    public int joinMember(signInVO svo, mCatVO mcvo) {
        log.warn("joinMember ȣ��: " + svo);

        // ȸ�� ���� ���� �� userNo ����
        mMapper.joinMember(svo);
        log.warn("ȸ������ �� userNo: " + svo.getUserNo());

        // userNo�� null���� Ȯ��
        if (svo.getUserNo() == 0) {
            throw new RuntimeException("ȸ������ �� userNo�� �������� ���߽��ϴ�.");
        }

        // ���ɻ� ����
        mcvo.setUserNo(svo.getUserNo());
        return mMapper.insertInterest(mcvo);
        

    }
    
    //���̵� �ߺ� Ȯ��
    public boolean checkDuplicateId(String userId) {
        return mMapper.checkDuplicateId(userId) > 0; // userId ���� ����
    }

	
}