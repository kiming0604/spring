package org.hype.controller;

import java.util.List;

import org.hype.domain.SearchRequest;
import org.hype.domain.gCatVO;
import org.hype.domain.goodsVO;
import org.hype.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequestMapping("/goodsStore")
public class GoodsRestController {
	
    @Autowired
    private GoodsService gService;

    @PostMapping(value = "/loadMoreGoods", consumes = "application/json", produces = "application/json")
    public List<goodsVO> loadMoreGoods(@RequestBody SearchRequest searchRequest) {
        String searchText = searchRequest.getSearchText();
        int page = searchRequest.getPage();

        int offset = (page - 1) * 10;
        List<goodsVO> voList = gService.getSearchList(searchText, offset, 10);

        for (goodsVO vo : voList) {
            gCatVO voCat = gService.getCategory(vo.getGno());
            vo.setGcat(voCat);
        }

        return voList;
    }
    
    @GetMapping(value = "/chkLike/{gno}/{userNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> chkLike(@PathVariable("gno") int gno, @PathVariable("userNo") int userNo){
    	int result = gService.getLikeChk(userNo, gno);
    	return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @GetMapping(value = "/changeLike/{gno}/{userNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> changeLike(@PathVariable("gno") int gno, @PathVariable("userNo") int userNo){
    	int result = gService.updateLike(userNo, gno);
    	log.info("À¯Àú No : " + userNo + "gno = " + gno);
    	return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @GetMapping(value = "/getLikeCount/{gno}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> getLikeCount(@PathVariable("gno") int gno){
    	int result = gService.getLikeCount(gno);
    	return new ResponseEntity<>(result, HttpStatus.OK);
    }
}