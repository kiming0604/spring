
package org.hype.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hype.domain.goodsVO;
import org.hype.domain.pCatVO;
import org.hype.domain.pImgVO;
import org.hype.domain.popStoreVO;
import org.hype.domain.signInVO;
import org.hype.service.GoodsService;
import org.hype.service.MemberService;
import org.hype.service.PopUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private PopUpService pservice;
	
	@Autowired
	private GoodsService gservice;
	
	@Autowired
	private MemberService mservice;
	
	@GetMapping("/adminPage")
	public String adminPopUp() {		
		
		System.out.println("관리자 페이지 이동");
		
		return "/admin/adminMain";
	}
	
	@GetMapping("/askList")
	public String askList() {		
		
		System.out.println("문의 리스트 페이지 이동");
		
		return "/admin/askListCheck";
	}
	
	// **관리자 페이지 영역**
	// 팝업스토어 리스트 출력 (header - 공통)
	@ResponseBody
	@GetMapping(value ="/psList",
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,
					   MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<popStoreVO>> getList(@RequestParam(required = false) String searchPs) {
				
		log.info("팝업스토어 리스트 출력 : " );
		
		List<popStoreVO> popStoreList;

	    // 검색어가 있을 경우
	    if (searchPs != null && !searchPs.isEmpty()) {
	        log.info("검색어 : " + searchPs);
	        popStoreList = pservice.getListBySearchPs(searchPs); // 검색 메서드 호출
	    } else {
	        popStoreList = pservice.getList(); // 전체 리스트 호출
	    }
	    
		return new ResponseEntity<List<popStoreVO>>(popStoreList, HttpStatus.OK);
	}
	
	// 팝업스토어 이름 클릭 시 팝업스토어 수정/삭제 페이지로 이동
	@GetMapping("/popUpUpdate")
	public String updatePopUp(@RequestParam("psNo") int psNo, Model model) {
	    log.info("팝업스토어 수정 페이지로 이동: psNo = " + psNo);
	    
	    // 해당 psNo에 대한 팝업스토어 정보 조회
	    popStoreVO popStore = pservice.getPopStoreById(psNo);
	    if (popStore != null) {
	        model.addAttribute("popStore", popStore); // JSP에서 사용하기 위해 모델에 추가
	        return "admin/psUpdateDelete"; // JSP 파일 경로
	    } else {
	        // 해당 ID의 팝업스토어가 없는 경우 처리
	        return "redirect:/admin/psList";
	    }
	}
	
	// 상품 리스트 출력 (header - 공통)
	@ResponseBody
	@GetMapping(value ="/gList",
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,
					   MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<goodsVO>> getGList(@RequestParam(required = false) String searchGs) {
		
		log.info("상품 리스트 출력 : " );
		
		List<goodsVO> goodsList;
		
		// 검색어가 있을 경우
	    if (searchGs != null && !searchGs.isEmpty()) {
	        log.info("검색어 : " + searchGs);
	        goodsList = gservice.getListBySearchGs(searchGs); // 검색 메서드 호출
	    } else {
	    	goodsList = gservice.getGList(); // 전체 리스트 호출
	    }
		return new ResponseEntity<List<goodsVO>>(goodsList, HttpStatus.OK);
	}
	
	// 굿즈 이름 클릭 시 굿즈 정보 수정/삭제 페이지로 이동
	@GetMapping("/goodsUpdate")
	public String updateGoods(@RequestParam("gNo") int gNo, Model model) {
	    log.info("굿즈 정보 수정 페이지로 이동: gNo = " + gNo);
	    
	    // 해당 psNo에 대한 팝업스토어 정보 조회
	    goodsVO goods = gservice.getGoodsById(gNo);
	    if (goods != null) {
	        model.addAttribute("goods", goods); // JSP에서 사용하기 위해 모델에 추가
	        return "admin/gUpdateDelete"; // JSP 파일 경로
	    } else {
	        // 해당 ID의 굿즈가 없는 경우 처리
	        return "redirect:/admin/gList";
	    }
	}
	
	// 회원 리스트 출력 (header - 공통)
	@ResponseBody
	@GetMapping(value ="/mList",
	produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,
			   MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<signInVO>> getMList(@RequestParam(required = false) String searchMs) {
		
		log.info("회원 리스트 출력 : " );
		
		List<signInVO> memberList;
		
		// 검색어가 있을 경우
	    if (searchMs != null && !searchMs.isEmpty()) {
	        log.info("검색어 : " + searchMs);
	        memberList = mservice.getListBySearchMs(searchMs); // 검색 메서드 호출
	    } else {
	    	memberList = mservice.getMList(); // 전체 리스트 호출
	    }
		
		return new ResponseEntity<List<signInVO>>(memberList, HttpStatus.OK);
	}
	
	// 회원 아이디 클릭 시 회원 정보 수정 페이지로 이동
	@GetMapping("/memberUpdate")
	public String updateMembers(@RequestParam("userId") String userId, Model model) {
	    log.info("회원 정보 수정 페이지로 이동: userId = " + userId);
	    
	    // 해당 psNo에 대한 팝업스토어 정보 조회
	    signInVO members = mservice.getMembersById(userId);
	    if (members != null) {
	        model.addAttribute("members", members); // JSP에서 사용하기 위해 모델에 추가
	        return "admin/memberUpdate"; // JSP 파일 경로
	    } else {
	        // 해당 ID의 회원이 없는 경우 처리
	        return "redirect:/admin/mList";
	    }
	}
	
	//***footer 영역***
	// 문의 리스트 확인 버튼 클릭 시 문의 리스트 확인 페이지로 이동
	@GetMapping("/askListCheck")
	public String checkAskList() {
		return "admin/askListCheck"; // JSP 파일 경로
	}
				
	// 상품 상태 조회 버튼 클릭 시 상품 상태 조회 페이지로 이동
	@GetMapping("/goodsState")
	public String checkGoods() {
		return "admin/goodsState"; // JSP 파일 경로
	}
		
	// 등록하기 버튼 클릭 시 이동
	@GetMapping("/popUpRegister")
    public String popUpRegister() {
        return "admin/popUpRegister"; 
    }

    @GetMapping("/goodsRegister")
    public String goodsRegister() {
        return "admin/goodsRegister"; 
    }
    
    //***팝업스토어 등록 페이지 영역***
    // 이미지 파일 등록
 	// 비동기 통신 : @ResponseBody
    @ResponseBody
    @PostMapping(value = "/uploadAsyncAction", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<pImgVO> uploadAsyncPost(@RequestParam("uploadFile") MultipartFile uploadFile) {
        log.info("upload Async post...");

        String uploadFolder = "C:\\upload"; // 기본 저장 경로
        
        File uploadPath = new File(uploadFolder);
        log.info("uploadPath : " + uploadPath);

        // 경로가 없으면 생성
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }

        log.info("-----------------------");
        log.info("Upload File Name : " + uploadFile.getOriginalFilename());
        log.info("Upload File Size : " + uploadFile.getSize());

        String uploadFileName = uploadFile.getOriginalFilename();
        uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
        log.info("only file name : " + uploadFileName);

        UUID uuid = UUID.randomUUID();
        uploadFileName = uuid.toString() + "_" + uploadFileName;

        try {
            File saveFile = new File(uploadPath, uploadFileName);
            uploadFile.transferTo(saveFile);

            pImgVO attachDto = new pImgVO();
            attachDto.setUuid(uuid.toString());
            attachDto.setUploadPath(uploadFolder); // 실제 경로 설정
            attachDto.setFilename(uploadFile.getOriginalFilename());

            return new ResponseEntity<>(attachDto, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 팝업스토어 등록버튼 클릭 시 등록 (insert)   	
    // 현재 에러 나고 잇어서 주석 처리
//	@PostMapping("/psRegister")
//	public String registerPopStore(popStoreVO psvo, pCatVO pcatvo, @RequestParam("uploadFile") MultipartFile imageFile) {
//				
//		log.warn("pcatvo :" + pcatvo.getHealthBeauty());
//		log.warn("pcatvo :" + pcatvo.getCulture());
//		log.warn("pcatvo :" + pcatvo.getShopping());
//		pcatvo.setPsNo(psvo.getPsNo());
//		
//		pImgVO image = new pImgVO();
//	    if (!imageFile.isEmpty()) {
//	    	image.setPsNo(psvo.getPsNo());
//	    	image.setUploadPath("C:\\upload"); // 실제 경로 설정 필요
//	    	image.setUuid(UUID.randomUUID().toString());
//	        psvo.getPsImg().setFilename("Test Filename");
////	        imgVO.setFilename(imageFile.getOriginalFilename());
//
//	        // 파일 업로드 메서드 호출
//	        ResponseEntity<pImgVO> response = uploadAsyncPost(imageFile);
//	        if (response.getStatusCode() == HttpStatus.OK) {
//	        	image = response.getBody(); // 업로드된 이미지 정보
//	        }
//	        
//	        // 파일 저장 로직 추가
//	        try {
//	            saveFile(imageFile, image.getUploadPath(), image.getFilename());
//	        } catch (IOException e) {
//	            log.error("파일 저장 중 오류 발생: " + e.getMessage());
//	            throw new RuntimeException("파일 저장 실패");
//	        }
//	    }
//		    
//		// 등록
//		pservice.psInsert(psvo, pcatvo, imageFile);
//        
//        return "admin/adminMain";
//    }
//	
//	private void saveFile(MultipartFile file, String uploadPath, String filename) throws IOException {
//	    File dest = new File(uploadPath + "\\" + filename);
//	    file.transferTo(dest); // 파일 저장
//	}
    
    // 현재 에러 나고 잇어서 주석 처리
//    @PostMapping("/psRegister")
//    public String registerPopStore(
//            popStoreVO psvo,
//            @RequestParam("psCat") String categories, // 카테고리
//            @RequestParam("imageFile") MultipartFile imageFile) { 
//    	
//    	log.info("registerPopStore....." + categories);
//    	
//    	log.warn("등록하기 이미지 " + psvo.getPsImg().getFilename());
//    	
//    	//psvo.getPsImg().setFilename("Test Filename");
//    	   	
//    	int result = 0;
//    	
//        try {
//            // 카테고리를 문자열 배열로 변환
//            String[] categoryArray = categories.split(",");
//            
//            // 이미지 정보 생성
//            pImgVO imgVO = new pImgVO();
//            if (!imageFile.isEmpty()) {
////                imgVO.setFilename(imageFile.getOriginalFilename());
//                imgVO.setUploadPath("C:\\upload"); // 실제 경로 설정 필요
//                imgVO.setUuid(UUID.randomUUID().toString());
//
//                // 파일 업로드 메서드 호출
//                ResponseEntity<pImgVO> response = uploadAsyncPost(imageFile);
//                if (response.getStatusCode() == HttpStatus.OK) {
//                    imgVO = response.getBody(); // 업로드된 이미지 정보
//                }
//            }
//
//            // 팝업스토어 등록
//            result = pservice.psInsert(psvo, categoryArray, imgVO);
//
//            // 성공적으로 등록된 경우 JSON 응답 반환
//            log.error("팝업스토어 등록이 완료되었습니다. ID : " + result);
//        } catch (Exception e) {
//            // 오류 발생 시 JSON 응답 반환
//        	log.error("팝업스토어 등록 중 오류가 발생했습니다. " + result);
//        }
//        
//        
//        return "/admin/adminMain";
//    }	
    
    // 팝업스토어 수정/삭제 페이지 영역
    // 삭제 버튼 클릭 시 삭제 후 관리자 메인페이지로 이동 
    // 현재 에러 나고 잇어서 주석 처리
//    @PostMapping("/psDelete")
//    public String deletePopStore(
//    		@ModelAttribute popStoreVO psvo,
//            @ModelAttribute pCatVO pcatvo, // 카테고리 정보
//            @ModelAttribute pImgVO image)
//    {
//    	log.info("팝업스토어 이름: " + psvo.getPsName());
//        log.info("카테고리: " + pcatvo.toString());
//        log.info("이미지 UUID: " + image.getUuid());
//        
//        String imagePath = "C:\\upload\\" + image.getUuid() + ".jpg"; // 예시 경로
//        
//        // 이미지 파일 삭제 로직
//        File fileToDelete = new File(imagePath);
//        if (fileToDelete.exists() && fileToDelete.delete()) {
//            log.info("이미지 파일 삭제 성공: " + imagePath);
//        } else {
//            log.warn("이미지 파일 삭제 실패 또는 파일이 존재하지 않음: " + imagePath);
//        }
//
//        // 삭제 로직 수행
//        int result = pservice.psDelete(psvo, pcatvo, image); // 삭제 서비스 호출
//        
//        return "/admin/adminMain";
//    }

}