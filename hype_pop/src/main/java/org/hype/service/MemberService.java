package org.hype.service;

import java.util.List;

import org.hype.domain.mCatVO;
import org.hype.domain.signInVO;

public interface MemberService {
	public List<signInVO> getMList();  // ������ ȸ�� ����Ʈ ��������
	public List<signInVO> getListBySearchMs(String searchMs);  // ������ �˻� ��� �߰�
	public signInVO getMembersById (String userId);  // Ư�� ȸ�� ��ȸ
	
	
	// ���� ���� �κ�
	public signInVO loginMember(signInVO svo);

	public int joinMember(signInVO svo, mCatVO mcvo);
	
	public boolean checkDuplicateId(String userId);
}