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

    private int psNo; 
    private String psName; 
    private pCatVO psCat; 
    private Date psStartDate; 
    private Date psEndDate; 
    private String psAddress; 
    private double latitude; 
    private double longitude; 
    private String psExp; 
    private List<pImgVO> psImg; 
    private int likeCount; 
    private String snsAd; 
    private String comInfo; 
    private String transInfo; 
    private String parkingInfo; 
    private double avgRating; 

    // 새로운 필드 추가
    private String interest; // 관심사 추가
    private List<popStoreVO> topStores; // 상위 스토어 목록 추가
}
