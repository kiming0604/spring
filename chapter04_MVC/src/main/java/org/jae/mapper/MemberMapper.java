package org.jae.mapper;

import org.jae.domain.AuthVO;
import org.jae.domain.MemberVO;

public interface MemberMapper {
	
	public MemberVO read(String userId);

	public int insert(MemberVO vo);

	public int insertAuth(AuthVO auth);


}
