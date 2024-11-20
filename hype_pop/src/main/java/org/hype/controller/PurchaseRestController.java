package org.hype.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hype.domain.cartVO;
import org.hype.domain.gImgVO;
import org.hype.domain.payVO;
import org.hype.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequestMapping("/purchase/api")
public class PurchaseRestController {

	@Autowired
	private PurchaseService purchaseService;

	// �긽�뭹 �궘�젣
	@DeleteMapping("deleteItem/{gno}")
	public ResponseEntity<String> deleteItem(@PathVariable int gno, @RequestParam int userNo) {
		log.info("Deleting item for userNo: " + userNo + ", gno: " + gno);

		purchaseService.deleteItem(userNo, gno);

		try {
			return ResponseEntity.ok("�긽�뭹�씠 �꽦怨듭쟻�쑝濡� �궘�젣�릺�뿀�뒿�땲�떎.");
		} catch (Exception e) {
			return ResponseEntity.status(500).body("�긽�뭹 �궘�젣�뿉 �떎�뙣�븯���뒿�땲�떎."); // �삤瑜� 諛쒖깮 �떆 硫붿떆吏� 諛섑솚
		}

	}

	// �옣諛붽뎄�땲�뿉 �븘�씠�뀥 異붽�
	@RequestMapping(value = "/addCart", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public ResponseEntity<String> addToCart(@RequestBody cartVO cvo) {

		int userNo = cvo.getUserNo();
		int gno = cvo.getGno();

		// �씠誘� �옣諛붽뎄�땲�뿉 �엳�뒗 寃쎌슦
		int alreadyInCart = purchaseService.alreadyInCart(userNo, gno);

		log.info("Adding to cart for userNo: " + userNo + ", gno: " + gno);

		if (alreadyInCart > 0) {
			return new ResponseEntity<>("이미 장바구니에 담긴 상품입니다", HttpStatus.OK); // �삉�뒗 HttpStatus.INTERNAL_SERVER_ERROR
																				// �벑�쓣 �궗�슜�븷 �닔 �엳�쓬
		}
		purchaseService.addToCart(cvo);
		return new ResponseEntity<>("장바구니에 상품이 담겼습니다.", HttpStatus.OK);
	}

	@PostMapping("/addToPayList")
	public ResponseEntity<String> addToPayList(@RequestBody List<payVO> pvoList) {
	    log.info("addToPayList.. " + pvoList);

	    try {
	        // orderId를 무작위로 생성 (UUID 사용)
	        String orderId = UUID.randomUUID().toString();  // UUID로 고유한 orderId 생성
	        for (payVO pvo : pvoList) {
	            pvo.setOrderId(orderId);  // 동일한 orderId 설정
	        }

	        // 데이터 처리 로직
	        purchaseService.addToPayList(pvoList);

	        // 성공적으로 처리된 후 응답할 JSON 데이터를 생성 (여기서 response는 JSON 형식으로 반환)
	        String response = "{\"status\":\"success\", \"message\":\"결제 목록에 추가되었습니다.\"}";
	        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);

	    } catch (Exception e) {
	        log.error("에러 발생:", e);
	        // 서버 오류 발생 시 JSON 형태로 응답
	        String errorResponse = "{\"status\":\"error\", \"message\":\"서버 오류 발생\"}";
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
	    }
	}
	
	
	// 장바구니 데이터 반환 (Spring Controller)
	@GetMapping("/getCartItems")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getCartItems(@RequestParam int userNo) {
	    try {
	        // userNo로 장바구니 데이터를 가져오는 로직
	        List<cartVO> cartItems = purchaseService.getCartInfo(userNo);
	        log.info("Get cart info: " + cartItems);

	        // JSON 형태로 반환 (cartVO 객체가 Jackson에 의해 자동 직렬화됨)
	        Map<String, Object> response = new HashMap<>();
	        response.put("cartItems", cartItems);

	        // ResponseEntity로 응답 반환
	        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
	                .body(response);
	    } catch (Exception e) {
	        log.error("장바구니 데이터 가져오기 실패", e);
	        Map<String, Object> errorResponse = new HashMap<>();
	        errorResponse.put("status", "error");
	        errorResponse.put("message", "장바구니 데이터 가져오기 실패");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .contentType(MediaType.APPLICATION_JSON)
	                .body(errorResponse);
	    }
	}
	
	@PostMapping("/updateCartAmount")
	public ResponseEntity<String> updateCartAmount(@RequestBody List<cartVO> cartVOList) {
	    try {
	        // 각 cartVO 객체에 대해 업데이트 실행
	        for (cartVO cvo : cartVOList) {
	            // 수량 업데이트 서비스 호출
	            purchaseService.updateCartAmount(cvo);
	            log.info("cvo:" + cvo);
	        }

	        // 성공 시 200 OK와 함께 메시지 반환
	        return ResponseEntity.status(HttpStatus.OK).body("수량이 성공적으로 업데이트되었습니다.");
	    } catch (Exception e) {
	        // 실패 시 400 Bad Request와 함께 오류 메시지 반환
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("수량 업데이트 실패: " + e.getMessage());
	    }
	}
	
	@GetMapping("/loadMoreItems")
	public ResponseEntity<List<payVO>> loadMoreItems(@RequestParam("userNo") int userNo, @RequestParam("page") int page) {
	    log.info("loadMoreItems...: " + userNo);
	    
	    int pageSize = 5; // 한 번에 불러올 항목 수
	    int offset = (page - 1) * pageSize; // 페이지 시작 위치

	    // 페이지네이션을 위한 데이터 가져오기
	    List<payVO> getPayList = purchaseService.getPayList(userNo, offset, pageSize);
	    
	    for (payVO pay : getPayList) {
	        int gno = pay.getGno();
	        log.info("gnognogno..." + gno);    
	        List<gImgVO> imgList = purchaseService.getPayListImg(gno);
	        log.info("imgList..." + imgList);
	        pay.setGimg(imgList);
	    }

	    return ResponseEntity.ok()
	            .contentType(MediaType.APPLICATION_JSON)
	            .body(getPayList);  // getPayList를 JSON으로 반환
	}

	
	@PostMapping(value = "/deleteCartItems", produces = "application/json")
	public ResponseEntity<?> deleteCartItems(@RequestBody Map<String, Object> params) {
	    try {
	        int userNo = (int) params.get("userNo");
	        List<Integer> gnoList = (List<Integer>) params.get("gnoList");
	        log.info("userNo: " + userNo);
	        log.info("gnoList: " + gnoList);


	        // 장바구니 삭제 서비스 호출
	        int deletedCount = purchaseService.deleteCartItems(gnoList, userNo);

	        // 삭제된 항목 수에 따른 응답 처리
	        if (deletedCount > 0) {
	            return ResponseEntity.ok(Map.of("status", "success", "message", "총 " + deletedCount + "개의 항목이 삭제되었습니다."));
	        } else {
	            return ResponseEntity.ok(Map.of("status", "fail", "message", "삭제할 항목이 없습니다."));
	        }
	    } catch (Exception e) {
	        log.error("장바구니 삭제 중 오류 발생", e); // 오류 로그 추가
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body(Map.of("status", "error", "message", "장바구니 삭제 중 오류 발생."));
	    }
	}
}