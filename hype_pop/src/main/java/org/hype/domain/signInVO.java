package org.hype.domain;

import java.sql.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class signInVO {
	


	 int userNo; // 회원 번호
	 String userId; // 아이디
	 String userPw; // 비밀번호
	 String userEmail; // 이메일
	 String userName; // 이름
	 int userNumber; // 전화번호
	 mCatVO userInterest; // 관심사
	 Date regDate; // 가입일
	 Date lastLoginDate; // 마지막 로그인 날짜
	 boolean enabled; // 계정 활성화 여부
	 int auth; // 권한
	 List<popStoreVO> gLikeList; // 굿즈 좋아요 목록
	 List<goodsVO> pLikeList; // 스토어 좋아요 목록
	 String snsToken; // Access Token
	 String snsType; // sns 종류

}