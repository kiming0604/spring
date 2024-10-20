package org.hype.mapper;

import java.util.List;

import org.hype.domain.mCatVO;
import org.hype.domain.signInVO;


public interface MemberMapper {
	public List<signInVO> getMList();  // ������ ȸ�� ����Ʈ ��������
	public List<signInVO> getListBySearchMs(String searchMs);  // ������ �˻� ��� �߰�
	public signInVO getMembersById (String userId);  // Ư�� ȸ�� ��ȸ
	
	// ���� ���� �κ�
    public signInVO loginMember(signInVO svo);

	public int joinMember(signInVO svo);
	
	public int insertInterest(mCatVO mcvo);
	
	public int checkDuplicateId(String userId);


//	public void saveUserInterest(signInVO svo);	
//	 @Insert("INSERT INTO users (username, password, user_interest) VALUES (#{username}, #{password}, #{userInterest})")
//	    void joinMember(signInVO svo); 
//	public int joinMember(signInVO svo);
//	
}