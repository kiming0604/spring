package org.jae.service;

import org.jae.domain.AuthVO;
import org.jae.domain.MemberVO;
import org.jae.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper mapper;
  

    @Override
    @Transactional // 트랜잭션 적용
    public int register(MemberVO vo) {
        // tbl_member에 데이터 삽입
        int result = mapper.insert(vo);
        
        AuthVO avo = new AuthVO();

        // 삽입된 userId 가져오기
       
        avo.setAuth("ROLE_USER");
        avo.setUserId(vo.getUserId());
        mapper.insertAuth(avo);

        return result;
    }

    // 추가 메소드 (회원 조회, 수정, 삭제 등) 구현 가능
}
