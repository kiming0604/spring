package org.hype.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LikedPopImgVO {

	
	private int userNo; // 회원번호
	private int psNo; // 팝업스토어 번호
	private Date likeDate; // 좋아요 날짜
	private String uuid; // uuid
	private String uploadPath; // 업로드 경로
	private String fileName; // 파일 이름
	private String psName; // 팝업스토어 이름
}