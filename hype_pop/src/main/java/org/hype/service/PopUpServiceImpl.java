package org.hype.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hype.domain.goodsVO;
import org.hype.domain.likeVO;
import org.hype.domain.pCatVO;
import org.hype.domain.popStoreVO;
import org.hype.domain.psReplyVO;
import org.hype.mapper.AttachMapper;
import org.hype.mapper.PopUpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class PopUpServiceImpl implements PopUpService {
	
	
	@Autowired
	public AttachMapper amapper;
	
    
    @Autowired
    PopUpMapper mapper;

    public List<popStoreVO> getPopularPopUps() {
        return mapper.getPopularPopUps();
    }

    // 관심사에 따른 상위 스토어 조회
    public Map<String, List<popStoreVO>> getTopStoresByInterests(int userno) {
        List<String> interests = mapper.getUserInterests(userno);
        Map<String, List<popStoreVO>> results = new HashMap<>();

        if (interests != null && !interests.isEmpty()) {
            for (String interest : interests) {
                List<popStoreVO> topStores = mapper.getTopStoresByInterest(interest);
                results.put(interest, topStores); // 관심사와 관련된 상위 스토어 목록을 Map에 추가
            }
        }
        return results;
    }
    @Override
    public popStoreVO getStoreInfoByName(String storeName) {
    	
    	popStoreVO result = mapper.getStoreInfoByName(storeName);
    	
    	return result;
    }
    
    @Transactional
    @Override
    public likeVO likeCount(int psNo, int userNo) {
        // 좋아요 목록에서 현재 사용자의 좋아요 상태 확인
        Map<String, Integer> params = new HashMap<>();
        params.put("psNo", psNo);
        params.put("userNo", userNo);
        
        int likeStatus = mapper.checkLikeStatus(params);
        
        if (likeStatus == 0) {
            // 좋아요가 없는 경우, 팝업 스토어의 좋아요 수 증가
            mapper.incrementLikeCount(psNo); // 좋아요 수 1 증가
            
            // 좋아요 목록에 새로운 좋아요 기록 추가
            likeVO newLike = new likeVO();
            newLike.setPsNo(psNo);
            newLike.setUserNo(userNo);
            mapper.insertLike(newLike); // 새로운 좋아요 추가
            
            return newLike; // 새로운 좋아요 정보 반환
        } else {
            // 좋아요가 이미 있는 경우, 팝업 스토어의 좋아요 수 감소
            mapper.decrementLikeCount(psNo); // 좋아요 수 1 감소
            
            // 좋아요 목록에서 해당 기록 삭제
            likeVO likeInfo = new likeVO();
            likeInfo.setPsNo(psNo);
            likeInfo.setUserNo(userNo);
            mapper.deleteLike(likeInfo); // 좋아요 삭제
            
            return null; // 삭제된 경우 null 반환
        }
    }

@Override
public Integer getLikeCount(int psNo) {
	Integer result = mapper.getLikeCount(psNo);
	
	return result;
}
@Override
public boolean checkUserLike(int psNo, int userNo) {
	 Map<String, Integer> params = new HashMap<>();
     params.put("psNo", psNo);
     params.put("userNo", userNo);
     
     int likeStatus = mapper.checkLikeStatus(params);
     
     if (likeStatus == 1) {
		return true;
	}
     else {
    	 return false;
		
	}
}

// 해당 팝업스토어의 굿즈 정보들 받아오기
@Transactional
@Override
public List<goodsVO> getGoodsInfoByName(String storeName) {
	
	int psNo = mapper.getPsNo(storeName);
	
	System.out.println("psNo: " + psNo); 
	
	List<goodsVO> result = mapper.getGoodsInfoByName(psNo);
	System.out.println("조회된 상품 수: " + result.size());
	
	for (goodsVO goods : result) {
	    System.out.println("상품명: " + goods.getGname() + ", 가격: " + goods.getGprice());
	}
	
	return result;
}
@Override
public List<popStoreVO> popUpSearchByData(String searchData) {
	List<popStoreVO> result = mapper.popUpSearchByData(searchData);
	
	return result;
}
@Override
public List<Map<String, Object>> getInterestsByPsNo(int psNo) {
	List<Map<String, Object>> result = mapper.getInterestsByPsNo(psNo);
	
	return result;
}
@Override
public double calculateAverageRating(int psNo) {
    // 쿼리 결과가 이미 평균을 반환하므로, 바로 리턴
    return mapper.findRatingsByPsNo(psNo); // 쿼리에서 이미 평균을 계산함
}
@Override
public List<popStoreVO> getAllPopUpData() {
	List<popStoreVO> result = mapper.showCalendar();
	return result;
}

@Override
public List<popStoreVO> findNearbyStores(double lat, double lng, double radius) {
    Map<String, Object> params = new HashMap<>();
    params.put("lat", lat);
    params.put("lng", lng);
    params.put("radius", radius);
    
    return mapper.findNearbyStores(params);
}

// 요셉이거 병합 부분
@Override
	public List<popStoreVO> showCalendar() {
	    return mapper.showCalendar();
	}

@Override
	public List<pCatVO> getCategoryData() {
		
		return mapper.getCategoryData();
	}
	// 서연씨 병합 부분 
	
	
	// 관리자 팝업스토어 리스트 가져오기
		@Override
		public List<popStoreVO> getList() {
			log.info("getList...");
			return mapper.getList();
		}

		// 관리자 검색 기능 추가
		@Override
		public List<popStoreVO> getListBySearchPs(String searchPs) {
			return mapper.getListBySearchPs(searchPs);
		}

		// 특정 팝업스토어 조회
		@Override
		public popStoreVO getPopStoreById(int psNo) {
			return mapper.getPopStoreById(psNo);
		}
		
		// 팝업스토어 등록 (현재 에러 나고 있어서 주석처리)
//		@Transactional
//		@Override
//		public int psInsert(popStoreVO psvo, pCatVO pcatvo, MultipartFile imageFile) {
//			log.warn("팝업스토어 정보 호출: " + psvo);
//			
//			// 팝업스토어 정보 삽입 후 psNo 설정
//	        mapper.psInsert(psvo);
//	        log.warn("정보삽입 후 psNo: " + psvo.getPsNo());
	//
//	        // psNo가 null인지 확인
//	        if (psvo.getPsNo() == 0) {
//	            throw new RuntimeException("회원가입 후 userNo를 가져오지 못했습니다.");
//	        }
	//
//	        // 카테고리 삽입
//	        pcatvo.setPsNo(psvo.getPsNo());
//	        mapper.catInsert(pcatvo);        
//	        
//	        // 이미지 삽입 
//	        if (!imageFile.isEmpty()) {
//	            pImgVO image = new pImgVO();
//	            image.setUploadPath("C:\\upload"); // 실제 경로 설정
//	            image.setPsNo(psvo.getPsNo());
//	            image.setUuid(UUID.randomUUID().toString());
//	            psvo.getPsImg().setFilename("Test Filename");
	//
//	            
//	            try {
//	                // 파일을 지정된 경로에 저장하는 메서드 호출
//	                saveFile(imageFile, image.getUploadPath(), image.getFilename());
//	            } catch (IOException e) {
//	                log.error("파일 저장 중 오류 발생: " + e.getMessage());
//	                throw new RuntimeException("파일 저장 실패");
//	            }
	//
//	            return amapper.imgInsert(image); // 이미지 정보 DB에 삽입
//	        }
//	        pImgVO image = new pImgVO();
//	        image.setPsNo(psvo.getPsNo());
//	        image.setUploadPath("C:\\upload"); // 실제 경로 설정
//	        image.setUuid(UUID.randomUUID().toString());
//	        return amapper.imgInsert(image);		
//		}
	//	
//		// 파일저장
//		private void saveFile(MultipartFile file, String uploadPath, String filename) throws IOException {
//		    File dest = new File(uploadPath + "\\" + filename);
//		    file.transferTo(dest); // 파일 저장
//		}
		
		
//		@Transactional  (현재 에러 나고 있어서 주석처리)
//	    @Override
//	    public int psInsert(popStoreVO psvo, String[] categories, pImgVO image) {        
//	        // 팝업스토어 삽입
//			// get으로 다 찍어보기
//			log.warn("팝업스토어 정보: ");
//			log.warn("번호: " + psvo.getPsNo());
//		    log.warn("이름: " + psvo.getPsName());
//		    log.warn("시작일: " + psvo.getPsStartDate());
//		    log.warn("종료일: " + psvo.getPsEndDate());
//		    log.warn("주소: " + psvo.getPsAddress());
//		    log.warn("위도: " + psvo.getLatitude());
//		    log.warn("경도: " + psvo.getLongitude());
//		    log.warn("설명: " + psvo.getPsExp());
//		    log.warn("좋아요 수: " + psvo.getLikeCount());
//		    log.warn("SNS 주소: " + psvo.getSnsAd());
//		    log.warn("주최사 정보: " + psvo.getComInfo());
//		    log.warn("교통편: " + psvo.getTransInfo());
//		    log.warn("주차 정보: " + psvo.getParkinginfo());
//		    log.warn("평균 평점: " + psvo.getAvgRating());
//		    
//		    for (String category : categories) {
//		    	log.warn(category);
//		    }
//		    
////	    	log.warn("팝업스토어 이미지: ");
////	    	log.warn("번호: " + image.getPsNo());
////	    	log.warn("유효아이디: " + image.getUuid());
////	    	log.warn("업로드 경로: " + image.getUploadPath());
////	    	log.warn("파일명: " + image.getFilename());
////			
//	        int result1 = mapper.psInsert(psvo);  
////	        log.warn("result1은 : " + result1);
//	        
//	        
//	        if(result1 == 1) {
//	        	int psNo = psvo.getPsNo();
	//   	
//	        	// vo 생성 (vo로 카테고리 빼오기)
//	        	pCatVO pcatvo = new pCatVO();
//	        	
//	        	pcatvo.setPsNo(psNo);
//	       	
////	        	// 카테고리 설정
//	        	for (String category : categories) {
//	        		log.warn(category);
//	        		switch (category) {
//	        		case "healthBeauty":
//	        			pcatvo.setHealthBeauty(1);
//	        			break;
//	        		case "game":
//	        			pcatvo.setGame(1);
//	        			break;
//	        		case "culture":
//	        			pcatvo.setCulture(1);
//	        			break;
//	        		case "shopping":
//	        			pcatvo.setShopping(1);
//	        			break;
//	        		case "supply":
//	        			pcatvo.setSupply(1);
//	        			break;
//	        		case "kids":
//	        			pcatvo.setKids(1);
//	        			break;
//	        		case "design":
//	        			pcatvo.setDesign(1);
//	        			break;
//	        		case "foods":
//	        			pcatvo.setFoods(1);
//	        			break;
//	        		case "interior":
//	        			pcatvo.setInterior(1);
//	        			break;
//	        		case "policy":
//	        			pcatvo.setPolicy(1);
//	        			break;
//	        		case "character":
//	        			pcatvo.setCharacter(1);
//	        			break;
//	        		case "experience":
//	        			pcatvo.setExperience(1);
//	        			break;
//	        		case "collaboration":
//	        			pcatvo.setCollaboration(1);
//	        			break;
//	        		case "entertainment":
//	        			pcatvo.setEntertainment(1);
//	        			break;
//	        		}
//	        	}        	
//	        	log.warn("팝업스토어 카테고리: ");
//	        	log.warn("번호: " + pcatvo.getPsNo());
//	        	log.warn("헬스 뷰티: "  + pcatvo.getHealthBeauty());
//	        	log.warn("게임: " + pcatvo.getGame());
//	        	log.warn("문화: " + pcatvo.getCulture());
//	        	log.warn("쇼핑: " + pcatvo.getShopping());
//	        	log.warn("문구: " + pcatvo.getSupply());
//	        	log.warn("키즈: " + pcatvo.getKids());
//	        	log.warn("디자인: " + pcatvo.getDesign());
//	        	log.warn("음식: " + pcatvo.getFoods());
//	        	log.warn("인테리어: " + pcatvo.getInterior());
//	        	log.warn("정책: " + pcatvo.getPolicy());
//	        	log.warn("캐릭터: " + pcatvo.getCharacter());
//	        	log.warn("체험: " + pcatvo.getExperience());
//	        	log.warn("콜라보: " + pcatvo.getCollaboration());
//	        	log.warn("방송: " + pcatvo.getEntertainment());
//	        	// 카테고리 삽입
//	        	int result2 = mapper.catInsert(pcatvo); 
	//
//	        	// 이미지 삽입
//	        	if(result2 == 1) {        		
////	        		log.info("result2는" + result2);
//	        	
////		        	log.warn("팝업스토어 이미지: ");
////		        	log.warn("번호: " + image.getPsNo());
////		        	log.warn("유효아이디: " + image.getUuid());
////		        	log.warn("업로드 경로: " + image.getUploadPath());
////		        	log.warn("파일명: " + image.getFilename());
//		        	
//	        		image.setPsNo(psNo);        		
//	        		int result3 = amapper.imgInsert(image); 
////	        		log.info("result3는" + result3);
//	        	}
	//
//	        }
	//
//	        return 1; // 성공적으로 삽입되면 return
//	    }
	    
		
	    // 팝업스토어 삭제 (현재 에러 나고 있어서 주석처리)
//		@Transactional
//	    @Override
//	    public int psDelete(popStoreVO psvo, pCatVO pcatvo, pImgVO image) {
//			
//			int result = 0;
//			
//			log.warn("Image info before deletion: " +
//		            "psNo: " + image.getPsNo() +
//		            ", uuid: " + image.getUuid() +
//		            ", uploadPath: " + image.getUploadPath() +
//		            ", filename: " + image.getFilename());
	//
//			log.warn("Image info before deletion: " +
//		            "psNo: " + (image != null ? image.getPsNo() : "No image") +
//		            ", uuid: " + (image != null ? image.getUuid() : "No image") +
//		            ", uploadPath: " + (image != null ? image.getUploadPath() : "No image") +
//		            ", filename: " + (image != null ? image.getFilename() : "No image"));
//			
//		    // 1단계: 이미지 삭제 
//		    if (image != null) {
//		        result += amapper.imgDelete(image);
//		        log.info("Image deleted: " + image.getUuid());
//		    }
	//
//		    // 2단계: 카테고리 삭제 
//		    if (pcatvo != null) {
//		        // 카테고리를 0으로 설정하여 삭제 의도를 표시
//		        pcatvo.setPsNo(psvo.getPsNo()); // 팝업스토어 번호 설정
//		        result += mapper.catDelete(pcatvo);
//		        log.info("Category deleted for psNo: " + psvo.getPsNo());
//		    }
	//
//		    // 3단계: 팝업스토어 삭제
//		    result += mapper.psDelete(psvo);
//		    log.info("Pop-up store deleted: " + psvo.getPsNo());
	//
//		    return result;
//			
//		}
		
		 //(현재 에러 나고 있어서 주석처리)
//	    public int psDelete(popStoreVO psvo, String[] categories, pImgVO image) {
//	    	
//	    	int result = 0;
//	    	
//	    	log.warn("Image info before deletion: " +
//	                "psNo: " + image.getPsNo() +
//	                ", uuid: " + image.getUuid() +
//	                ", uploadPath: " + image.getUploadPath() +
//	                ", filename: " + image.getFilename());
//	    	
//	    	// 1단계 : 이미지 삭제 
//	    	if (image != null) {
//	            result += amapper.imgDelete(image);
//	        }
//	    	
//	    	// 2단계 : 카테고리 삭제 
//	    	if(categories != null) {
//	    		
//	    		pCatVO pcatvo = new pCatVO();
//	    		pcatvo.setPsNo(psvo.getPsNo());
//	    		
//	    		for (String category : categories) {
//	    			switch (category) {
//	                case "healthBeauty":
//	                    pcatvo.setHealthBeauty(0); // 0으로 설정해 삭제할 의도 표시
//	                    break;
//	                case "game":
//	                    pcatvo.setGame(0);
//	                    break;
//	                case "culture":
//	                    pcatvo.setCulture(0);
//	                    break;
//	                case "shopping":
//	                    pcatvo.setShopping(0);
//	                    break;
//	                case "supply":
//	                    pcatvo.setSupply(0);
//	                    break;
//	                case "kids":
//	                    pcatvo.setKids(0);
//	                    break;
//	                case "design":
//	                    pcatvo.setDesign(0);
//	                    break;
//	                case "foods":
//	                    pcatvo.setFoods(0);
//	                    break;
//	                case "interior":
//	                    pcatvo.setInterior(0);
//	                    break;
//	                case "policy":
//	                    pcatvo.setPolicy(0);
//	                    break;
//	                case "character":
//	                    pcatvo.setCharacter(0);
//	                    break;
//	                case "experience":
//	                    pcatvo.setExperience(0);
//	                    break;
//	                case "collaboration":
//	                    pcatvo.setCollaboration(0);
//	                    break;
//	                case "entertainment":
//	                    pcatvo.setEntertainment(0);
//	                    break;
//	            }
//	                result += mapper.catDelete(pcatvo);
//	                pcatvo = new pCatVO(); // 초기화
//	                pcatvo.setPsNo(psvo.getPsNo());
//	            }
//	    	}
//	    	
//	    	// 3단계 : 팝업스토어 삭제
//	    	result += mapper.psDelete(psvo);
//	    	
//			return result;    	
//	    }

}
