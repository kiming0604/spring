package org.hype.controller;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.hype.domain.goodsVO;
import org.hype.domain.likeVO;
import org.hype.domain.mCatVO;
import org.hype.domain.pCatVO;
import org.hype.domain.pImgVO;
import org.hype.domain.popStoreVO;
import org.hype.domain.psReplyVO;
import org.hype.service.PopUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/hypePop/*")
public class PopUpController {
    @Autowired
    PopUpService service;
    
    @RequestMapping(value = "/popUpMain", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        int userno = 5; // 임시 사용자 번호 (예: 로그인된 사용자의 번호로 변경)

        // 인기있는 팝업 스토어 목록 조회
        List<popStoreVO> popularPopUps = service.getPopularPopUps(); 

        // 각 팝업 스토어에 대한 이미지 조회 후 설정
        for (popStoreVO popUp : popularPopUps) {
            pImgVO imgVo = service.getImageByStoreId(popUp.getPsNo());
            if (imgVo != null) {
                popUp.setPsImg(imgVo); // pImgVO를 팝업 스토어 객체에 설정
            }
        }
        model.addAttribute("popularPopUps", popularPopUps); // 인기 있는 팝업 스토어 목록

        // 사용자 관심사 기반의 상위 팝업 스토어 목록 조회
        Map<String, List<popStoreVO>> topStoresByInterest = service.getTopStoresByInterests(userno);

        // 각 팝업 스토어에 대한 이미지 조회 후 설정
        for (List<popStoreVO> storeList : topStoresByInterest.values()) {
            for (popStoreVO popUp2 : storeList) {
                pImgVO imgVo = service.getImageByStoreId(popUp2.getPsNo());
                if (imgVo != null) {
                    popUp2.setPsImg(imgVo); // pImgVO를 팝업 스토어 객체에 설정
                }
            }
        }
        model.addAttribute("topStoresByInterestMap", topStoresByInterest); // 관심사 기반 상위 팝업 스토어 목록

        // 카테고리별 인기 팝업 스토어 목록 조회
        Map<String, List<popStoreVO>> topCategoriesByLikes = service.getTopCategoriesByLikes();
        for (List<popStoreVO> storeList : topCategoriesByLikes.values()) {
            for (popStoreVO popUp3 : storeList) {
                pImgVO imgVo = service.getImageByStoreId(popUp3.getPsNo());
                if (imgVo != null) {
                    popUp3.setPsImg(imgVo); // pImgVO를 팝업 스토어 객체에 설정
                }
            }
        }
        model.addAttribute("topCategoriesByLikesMap", topCategoriesByLikes); // 카테고리별 인기 팝업 스토어 목록

        return "popUp/popUpMainPage"; // JSP 페이지로 포워딩
    }

    @GetMapping("/search") // URL로 전달된 검색 데이터에 대한 처리
    public String search(@RequestParam("searchData") String searchData, Model model) {
        // 검색 데이터 출력
        System.out.println("검색된 데이터: " + searchData);
        
        // DB에서 검색된 데이터 목록 조회
        List<popStoreVO> vo = service.popUpSearchByData(searchData);
       
        for (popStoreVO store : vo) {
            // 관심사 목록 조회
            List<Map<String, Object>> interestsList = service.getInterestsByPsNo(store.getPsNo());

            // 관심사 문자열 구성
            StringBuilder interestsBuilder = new StringBuilder();
            for (Map<String, Object> interest : interestsList) {
                if (interestsBuilder.length() > 0) {
                    interestsBuilder.append(", "); // 구분자 추가
                }
                log.info("관심사 : " + interestsBuilder);
                interestsBuilder.append(interest.get("INTERESTS"));
            }
            store.setInterest(interestsBuilder.toString());
            
            // 평균 평점 계산
            double averageRating = service.calculateAverageRating(store.getPsNo());
            store.setAvgRating(averageRating); // 팝업 스토어에 평균 평점 설정

            System.out.println("관심사: " + store.getInterest());
            System.out.println("------------------------------");
        }
        
        // 검색 결과 JSP에 전달
        model.addAttribute("searchData", vo);    
        
        return "/popUp/searchResultPage"; // 검색 결과 페이지로 포워딩
    }

    // 검색된 데이터가 없을 경우 처리
    @GetMapping("/search/noData")
    public String searchWithoutData(Model model) {
        List<popStoreVO> vo = service.getAllPopUpData();
        
        for (popStoreVO store : vo) {
            System.out.println("팝업 스토어 번호: " + store.getPsNo());
            System.out.println("팝업 스토어 이름: " + store.getPsName());
            System.out.println("주소: " + store.getPsAddress());
            System.out.println("설명: " + store.getPsExp());
            System.out.println("좋아요 수: " + store.getLikeCount());
            System.out.println("평균 평점: " + store.getAvgRating());
            
            // 관심사 목록 조회
            List<Map<String, Object>> interestsList = service.getInterestsByPsNo(store.getPsNo());

            // 관심사 문자열 구성
            StringBuilder interestsBuilder = new StringBuilder();
            for (Map<String, Object> interest : interestsList) {
                if (interestsBuilder.length() > 0) {
                    interestsBuilder.append(", "); // 구분자 추가
                }
                interestsBuilder.append(interest.get("INTERESTS"));
            }

            // 관심사 설정
            store.setInterest(interestsBuilder.toString());
            // 평균 평점 계산
            double averageRating = service.calculateAverageRating(store.getPsNo());
            store.setAvgRating(averageRating); // 팝업 스토어에 평균 평점 설정

            System.out.println("관심사: " + store.getInterest());
            System.out.println("------------------------------");
        }
        
        // 데이터 전달
        model.addAttribute("searchData", vo);
        
        return "/popUp/searchResultPage"; // 검색 결과 페이지로 포워딩
    }

    @GetMapping("/popUpDetails")
    public String popUpDetails(@RequestParam("storeName") String storeName, Model model) {
        // storeName에 해당하는 팝업 스토어 정보 조회
        System.out.println("팝업 스토어 이름: " + storeName);
        
        // DB에서 팝업 스토어 정보 조회
        popStoreVO vo = service.getStoreInfoByName(storeName);
        
        System.out.println("조회된 팝업 스토어 번호 : " + vo.getPsNo());
        
        // 팝업 스토어 이미지 조회
        pImgVO imgVo = service.getImageByStoreId(vo.getPsNo());
        vo.setPsImg(imgVo);
        
        // 상품 정보 조회
        List<goodsVO> gvo = service.getGoodsInfoByName(storeName);
        
        for (goodsVO goods : gvo) {
            System.out.println("상품명: " + goods.getGname() + ", 가격: " + goods.getGprice() + "원, 상품 번호: " + goods.getGno());
        }
        
        // 평균 평점 계산
        double avgRating = service.getAvgRating(vo.getPsNo());
        
        // 팝업 스토어에 평균 평점 설정
        vo.setAvgRating(avgRating);  
        
        // 팝업 스토어 정보와 상품 정보 JSP로 전달
        model.addAttribute("storeInfo", vo);
        model.addAttribute("goodsInfo", gvo);
        
        return "/popUp/popUpDetailsPage"; // 상세 페이지로 포워딩
    }

    // 캘린더 페이지로 이동
    @RequestMapping("/calendar")
    public String showCalendarPage() {
        return "/popUpCalendar/calendarMain";  
    }
}
