package org.jae.controller;



import org.jae.domain.TestVO;
import org.jae.domain.Ticket;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/test")
@RestController
public class TestController {           // 응답 데이터의 타입 
    @GetMapping(value = "/getText" , produces = "text/plain; charset=utf-8")
	public String getText() {
		log.info("Mime type : " + MediaType.TEXT_PLAIN_VALUE);
		
		return "겁나게 반갑당게요";
	}
    @GetMapping(value = "/getObject" , 
    			produces = {
    					MediaType.APPLICATION_JSON_UTF8_VALUE,
    					MediaType.APPLICATION_XML_VALUE
    			}
    )

public TestVO getObject() {
	return new TestVO(100,"kim");
}
    /*
     * 요청 URL : /test/check
     * 파라미터 : 실수형 age
     * 리턴타입 : TestVO
     * - vo 객체를 생성
     * - no 필드는 0으로 고정
     * - 전달 받은 age를 문자열로 name 필드에 저장
     * - json형태로 TestVO 반환
     * 
     * */
    
    
//    @GetMapping(value = "/check" , 
//			produces = {
//					MediaType.APPLICATION_JSON_UTF8_VALUE,
//					MediaType.APPLICATION_XML_VALUE
//			}
//)
//    public TestVO getAge(double age) {
//    	
//    	TestVO vo = new TestVO();
//    	
//    	vo.setNo(0);
//    	
//    	vo.setName(Double.toString(age));
//    	
//
//  	return vo;
//    	                      // String으로 간단하게 만드는법      ==     "" + 숫자
//    	return new TestVO(0, "" + age);
//    }
// }
public ResponseEntity<TestVO> getAge(double age) {

TestVO vo = new TestVO(0 , "" + age);

ResponseEntity<TestVO> result = null;

if (age > 150) {  //150이 넘어가면 콘솔에 bad gateway가 뜸
	result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
}else {
	 
	result = ResponseEntity.status(HttpStatus.OK).body(vo);
}

return result;
}
         // cat 과 pid에 맞는 친구가 오면 타게 됨
@GetMapping("/product/{cat}/{pid}")
public String[] getPath(    // url과의 매핑과정, 받아온 cat과 pid를 변수로 만들어준다
						@PathVariable("cat") String cat,
						@PathVariable("pid") int pid
		) {  
	return new String[] {"category : " + cat + ", productId : " + pid};
}

@PostMapping("/ticket")
public Ticket convert(@RequestBody Ticket t) {
	log.info("convert ticket : " + t);
	
	String result = new Gson().toJson(t);
	
	log.info(result);
	return t;
}

@GetMapping("/info")
public String makeJson() {
	JsonObject json = new JsonObject();
	
	json.addProperty("name", "kim");
	json.addProperty("age", 1967);
	json.addProperty("job", "돈없는 백수");
	
	JsonArray ja = new JsonArray();
	for (int i = 0; i < 5; i++) {
		JsonObject j = new JsonObject();
		j.addProperty("user" + i, i);
		ja.add(j);
	}
	json.add("users", ja);
	return json.toString();
}

}


