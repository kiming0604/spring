package org.hype.domain;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class popStoreVO {

	private int psNo; // 팝업스토어 번호
	private String psName; // 팝업스토어 이름
	private pCatVO psCat; // 팝업스토어 카테고리
	private Date psStartDate; // 팝업스토어 시작일
	private Date psEndDate; // 팝업스토어 종료일
	private String psAddress; // 팝업스토어 주소]
	private double latitude; // 팝업스토어 위도
	private double longitude; // 팝업스토어 경도
	private String psExp; // 팝업스토어 설명글
	private List<pImgVO> psImg; // 팝업스토어 사진 데이터
	private int likeCount; // 팝업스토어 좋아요 수
	private String snsAd; // SNS 주소
	private String comInfo; // 주최사 정보
	private String transInfo; // 교통편
	private String parkinginfo; // 주차장 정보
	private double avgRating; // 평균 별점
}
