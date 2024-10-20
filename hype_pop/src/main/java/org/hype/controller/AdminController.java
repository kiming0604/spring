
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
		
		System.out.println("愿�由ъ옄 �럹�씠吏� �씠�룞");
		
		return "/admin/adminMain";
	}
	
	@GetMapping("/askList")
	public String askList() {		
		
		System.out.println("臾몄쓽 由ъ뒪�듃 �럹�씠吏� �씠�룞");
		
		return "/admin/askListCheck";
	}
	
	// **愿�由ъ옄 �럹�씠吏� �쁺�뿭**
	// �뙘�뾽�뒪�넗�뼱 由ъ뒪�듃 異쒕젰 (header - 怨듯넻)
	@ResponseBody
	@GetMapping(value ="/psList",
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,
					   MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<popStoreVO>> getList(@RequestParam(required = false) String searchPs) {
				
		log.info("�뙘�뾽�뒪�넗�뼱 由ъ뒪�듃 異쒕젰 : " );
		
		List<popStoreVO> popStoreList;

	    // 寃��깋�뼱媛� �엳�쓣 寃쎌슦
	    if (searchPs != null && !searchPs.isEmpty()) {
	        log.info("寃��깋�뼱 : " + searchPs);
	        popStoreList = pservice.getListBySearchPs(searchPs); // 寃��깋 硫붿꽌�뱶 �샇異�
	    } else {
	        popStoreList = pservice.getList(); // �쟾泥� 由ъ뒪�듃 �샇異�
	    }
	    
		return new ResponseEntity<List<popStoreVO>>(popStoreList, HttpStatus.OK);
	}
	
	// �뙘�뾽�뒪�넗�뼱 �씠由� �겢由� �떆 �뙘�뾽�뒪�넗�뼱 �닔�젙/�궘�젣 �럹�씠吏�濡� �씠�룞
	@GetMapping("/popUpUpdate")
	public String updatePopUp(@RequestParam("psNo") int psNo, Model model) {
	    log.info("�뙘�뾽�뒪�넗�뼱 �닔�젙 �럹�씠吏�濡� �씠�룞: psNo = " + psNo);
	    
	    // �빐�떦 psNo�뿉 ���븳 �뙘�뾽�뒪�넗�뼱 �젙蹂� 議고쉶
	    popStoreVO popStore = pservice.getPopStoreById(psNo);
	    if (popStore != null) {
	        model.addAttribute("popStore", popStore); // JSP�뿉�꽌 �궗�슜�븯湲� �쐞�빐 紐⑤뜽�뿉 異붽�
	        return "admin/psUpdateDelete"; // JSP �뙆�씪 寃쎈줈
	    } else {
	        // �빐�떦 ID�쓽 �뙘�뾽�뒪�넗�뼱媛� �뾾�뒗 寃쎌슦 泥섎━
	        return "redirect:/admin/psList";
	    }
	}
	
	// �긽�뭹 由ъ뒪�듃 異쒕젰 (header - 怨듯넻)
	@ResponseBody
	@GetMapping(value ="/gList",
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,
					   MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<goodsVO>> getGList(@RequestParam(required = false) String searchGs) {
		
		log.info("�긽�뭹 由ъ뒪�듃 異쒕젰 : " );
		
		List<goodsVO> goodsList;
		
		// 寃��깋�뼱媛� �엳�쓣 寃쎌슦
	    if (searchGs != null && !searchGs.isEmpty()) {
	        log.info("寃��깋�뼱 : " + searchGs);
	        goodsList = gservice.getListBySearchGs(searchGs); // 寃��깋 硫붿꽌�뱶 �샇異�
	    } else {
	    	goodsList = gservice.getGList(); // �쟾泥� 由ъ뒪�듃 �샇異�
	    }
		return new ResponseEntity<List<goodsVO>>(goodsList, HttpStatus.OK);
	}
	
	// 援우쫰 �씠由� �겢由� �떆 援우쫰 �젙蹂� �닔�젙/�궘�젣 �럹�씠吏�濡� �씠�룞
	@GetMapping("/goodsUpdate")
	public String updateGoods(@RequestParam("gNo") int gNo, Model model) {
	    log.info("援우쫰 �젙蹂� �닔�젙 �럹�씠吏�濡� �씠�룞: gNo = " + gNo);
	    
	    // �빐�떦 psNo�뿉 ���븳 �뙘�뾽�뒪�넗�뼱 �젙蹂� 議고쉶
	    goodsVO goods = gservice.getGoodsById(gNo);
	    if (goods != null) {
	        model.addAttribute("goods", goods); // JSP�뿉�꽌 �궗�슜�븯湲� �쐞�빐 紐⑤뜽�뿉 異붽�
	        return "admin/gUpdateDelete"; // JSP �뙆�씪 寃쎈줈
	    } else {
	        // �빐�떦 ID�쓽 援우쫰媛� �뾾�뒗 寃쎌슦 泥섎━
	        return "redirect:/admin/gList";
	    }
	}
	
	// �쉶�썝 由ъ뒪�듃 異쒕젰 (header - 怨듯넻)
	@ResponseBody
	@GetMapping(value ="/mList",
	produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,
			   MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<signInVO>> getMList(@RequestParam(required = false) String searchMs) {
		
		log.info("�쉶�썝 由ъ뒪�듃 異쒕젰 : " );
		
		List<signInVO> memberList;
		
		// 寃��깋�뼱媛� �엳�쓣 寃쎌슦
	    if (searchMs != null && !searchMs.isEmpty()) {
	        log.info("寃��깋�뼱 : " + searchMs);
	        memberList = mservice.getListBySearchMs(searchMs); // 寃��깋 硫붿꽌�뱶 �샇異�
	    } else {
	    	memberList = mservice.getMList(); // �쟾泥� 由ъ뒪�듃 �샇異�
	    }
		
		return new ResponseEntity<List<signInVO>>(memberList, HttpStatus.OK);
	}
	
	// �쉶�썝 �븘�씠�뵒 �겢由� �떆 �쉶�썝 �젙蹂� �닔�젙 �럹�씠吏�濡� �씠�룞
	@GetMapping("/memberUpdate")
	public String updateMembers(@RequestParam("userId") String userId, Model model) {
	    log.info("�쉶�썝 �젙蹂� �닔�젙 �럹�씠吏�濡� �씠�룞: userId = " + userId);
	    
	    // �빐�떦 psNo�뿉 ���븳 �뙘�뾽�뒪�넗�뼱 �젙蹂� 議고쉶
	    signInVO members = mservice.getMembersById(userId);
	    if (members != null) {
	        model.addAttribute("members", members); // JSP�뿉�꽌 �궗�슜�븯湲� �쐞�빐 紐⑤뜽�뿉 異붽�
	        return "admin/memberUpdate"; // JSP �뙆�씪 寃쎈줈
	    } else {
	        // �빐�떦 ID�쓽 �쉶�썝�씠 �뾾�뒗 寃쎌슦 泥섎━
	        return "redirect:/admin/mList";
	    }
	}
	
	//***footer �쁺�뿭***
	// 臾몄쓽 由ъ뒪�듃 �솗�씤 踰꾪듉 �겢由� �떆 臾몄쓽 由ъ뒪�듃 �솗�씤 �럹�씠吏�濡� �씠�룞
	@GetMapping("/askListCheck")
	public String checkAskList() {
		return "admin/askListCheck"; // JSP �뙆�씪 寃쎈줈
	}
				
	// �긽�뭹 �긽�깭 議고쉶 踰꾪듉 �겢由� �떆 �긽�뭹 �긽�깭 議고쉶 �럹�씠吏�濡� �씠�룞
	@GetMapping("/goodsState")
	public String checkGoods() {
		return "admin/goodsState"; // JSP �뙆�씪 寃쎈줈
	}
		
	// �벑濡앺븯湲� 踰꾪듉 �겢由� �떆 �씠�룞
	@GetMapping("/popUpRegister")
    public String popUpRegister() {
        return "admin/popUpRegister"; 
    }

    @GetMapping("/goodsRegister")
    public String goodsRegister() {
        return "admin/goodsRegister"; 
    }
    
    //***�뙘�뾽�뒪�넗�뼱 �벑濡� �럹�씠吏� �쁺�뿭***
    // �씠誘몄� �뙆�씪 �벑濡�
 	// 鍮꾨룞湲� �넻�떊 : @ResponseBody
    @ResponseBody
    @PostMapping(value = "/uploadAsyncAction", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<pImgVO> uploadAsyncPost(@RequestParam("uploadFile") MultipartFile uploadFile) {
        log.info("upload Async post...");

        String uploadFolder = "C:\\upload"; // 湲곕낯 ���옣 寃쎈줈
        
        File uploadPath = new File(uploadFolder);
        log.info("uploadPath : " + uploadPath);

        // 寃쎈줈媛� �뾾�쑝硫� �깮�꽦
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
            attachDto.setUploadPath(uploadFolder); // �떎�젣 寃쎈줈 �꽕�젙
            attachDto.setFilename(uploadFile.getOriginalFilename());

            return new ResponseEntity<>(attachDto, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // �뙘�뾽�뒪�넗�뼱 �벑濡앸쾭�듉 �겢由� �떆 �벑濡� (insert)   	
    // �쁽�옱 �뿉�윭 �굹怨� �엲�뼱�꽌 二쇱꽍 泥섎━
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
//	    	image.setUploadPath("C:\\upload"); // �떎�젣 寃쎈줈 �꽕�젙 �븘�슂
//	    	image.setUuid(UUID.randomUUID().toString());
//	        psvo.getPsImg().setFilename("Test Filename");
////	        imgVO.setFilename(imageFile.getOriginalFilename());
//
//	        // �뙆�씪 �뾽濡쒕뱶 硫붿꽌�뱶 �샇異�
//	        ResponseEntity<pImgVO> response = uploadAsyncPost(imageFile);
//	        if (response.getStatusCode() == HttpStatus.OK) {
//	        	image = response.getBody(); // �뾽濡쒕뱶�맂 �씠誘몄� �젙蹂�
//	        }
//	        
//	        // �뙆�씪 ���옣 濡쒖쭅 異붽�
//	        try {
//	            saveFile(imageFile, image.getUploadPath(), image.getFilename());
//	        } catch (IOException e) {
//	            log.error("�뙆�씪 ���옣 以� �삤瑜� 諛쒖깮: " + e.getMessage());
//	            throw new RuntimeException("�뙆�씪 ���옣 �떎�뙣");
//	        }
//	    }
//		    
//		// �벑濡�
//		pservice.psInsert(psvo, pcatvo, imageFile);
//        
//        return "admin/adminMain";
//    }
//	
//	private void saveFile(MultipartFile file, String uploadPath, String filename) throws IOException {
//	    File dest = new File(uploadPath + "\\" + filename);
//	    file.transferTo(dest); // �뙆�씪 ���옣
//	}
    
    // �쁽�옱 �뿉�윭 �굹怨� �엲�뼱�꽌 二쇱꽍 泥섎━
//    @PostMapping("/psRegister")
//    public String registerPopStore(
//            popStoreVO psvo,
//            @RequestParam("psCat") String categories, // 移댄뀒怨좊━
//            @RequestParam("imageFile") MultipartFile imageFile) { 
//    	
//    	log.info("registerPopStore....." + categories);
//    	
//    	log.warn("�벑濡앺븯湲� �씠誘몄� " + psvo.getPsImg().getFilename());
//    	
//    	//psvo.getPsImg().setFilename("Test Filename");
//    	   	
//    	int result = 0;
//    	
//        try {
//            // 移댄뀒怨좊━瑜� 臾몄옄�뿴 諛곗뿴濡� 蹂��솚
//            String[] categoryArray = categories.split(",");
//            
//            // �씠誘몄� �젙蹂� �깮�꽦
//            pImgVO imgVO = new pImgVO();
//            if (!imageFile.isEmpty()) {
////                imgVO.setFilename(imageFile.getOriginalFilename());
//                imgVO.setUploadPath("C:\\upload"); // �떎�젣 寃쎈줈 �꽕�젙 �븘�슂
//                imgVO.setUuid(UUID.randomUUID().toString());
//
//                // �뙆�씪 �뾽濡쒕뱶 硫붿꽌�뱶 �샇異�
//                ResponseEntity<pImgVO> response = uploadAsyncPost(imageFile);
//                if (response.getStatusCode() == HttpStatus.OK) {
//                    imgVO = response.getBody(); // �뾽濡쒕뱶�맂 �씠誘몄� �젙蹂�
//                }
//            }
//
//            // �뙘�뾽�뒪�넗�뼱 �벑濡�
//            result = pservice.psInsert(psvo, categoryArray, imgVO);
//
//            // �꽦怨듭쟻�쑝濡� �벑濡앸맂 寃쎌슦 JSON �쓳�떟 諛섑솚
//            log.error("�뙘�뾽�뒪�넗�뼱 �벑濡앹씠 �셿猷뚮릺�뿀�뒿�땲�떎. ID : " + result);
//        } catch (Exception e) {
//            // �삤瑜� 諛쒖깮 �떆 JSON �쓳�떟 諛섑솚
//        	log.error("�뙘�뾽�뒪�넗�뼱 �벑濡� 以� �삤瑜섍� 諛쒖깮�뻽�뒿�땲�떎. " + result);
//        }
//        
//        
//        return "/admin/adminMain";
//    }	
    
    // �뙘�뾽�뒪�넗�뼱 �닔�젙/�궘�젣 �럹�씠吏� �쁺�뿭
    // �궘�젣 踰꾪듉 �겢由� �떆 �궘�젣 �썑 愿�由ъ옄 硫붿씤�럹�씠吏�濡� �씠�룞 
    // �쁽�옱 �뿉�윭 �굹怨� �엲�뼱�꽌 二쇱꽍 泥섎━
//    @PostMapping("/psDelete")
//    public String deletePopStore(
//    		@ModelAttribute popStoreVO psvo,
//            @ModelAttribute pCatVO pcatvo, // 移댄뀒怨좊━ �젙蹂�
//            @ModelAttribute pImgVO image)
//    {
//    	log.info("�뙘�뾽�뒪�넗�뼱 �씠由�: " + psvo.getPsName());
//        log.info("移댄뀒怨좊━: " + pcatvo.toString());
//        log.info("�씠誘몄� UUID: " + image.getUuid());
//        
//        String imagePath = "C:\\upload\\" + image.getUuid() + ".jpg"; // �삁�떆 寃쎈줈
//        
//        // �씠誘몄� �뙆�씪 �궘�젣 濡쒖쭅
//        File fileToDelete = new File(imagePath);
//        if (fileToDelete.exists() && fileToDelete.delete()) {
//            log.info("�씠誘몄� �뙆�씪 �궘�젣 �꽦怨�: " + imagePath);
//        } else {
//            log.warn("�씠誘몄� �뙆�씪 �궘�젣 �떎�뙣 �삉�뒗 �뙆�씪�씠 議댁옱�븯吏� �븡�쓬: " + imagePath);
//        }
//
//        // �궘�젣 濡쒖쭅 �닔�뻾
//        int result = pservice.psDelete(psvo, pcatvo, image); // �궘�젣 �꽌鍮꾩뒪 �샇異�
//        
//        return "/admin/adminMain";
//    }

}