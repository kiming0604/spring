package org.kys.mapper;

import org.kys.domain.MemberVO;

public interface MemberMapper {
	public MemberVO read(String userId);
}
