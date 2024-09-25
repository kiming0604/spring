package org.jae.mapper;

import org.jae.domain.MemberVO;

public interface MemberMapper {
	public MemberVO read(String userId);
}
