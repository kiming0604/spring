package org.hype.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hype.domain.gCatVO;
import org.hype.domain.goodsVO;
import org.hype.domain.rankVO;
import org.hype.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class GoodsServiceImpl implements GoodsService{
	
	@Autowired
	public GoodsMapper mapper;

	@Override
	public List<goodsVO> getGList() {
		return mapper.getGList();
	}

	@Override
	public List<goodsVO> getListBySearchGs(String searchGs) {
		return mapper.getListBySearchGs(searchGs);
	}

	@Override
	public goodsVO getGoodsById(int gNo) {
		return mapper.getGoodsById(gNo);
	}	
    // ��ȯ�� ���� ���� �κ� 
	@Override
	public List<goodsVO> getListByLikeCount() {
		log.info("�α� ���� �������� ��...");
		return mapper.getListByLikeCount();
	}

	// �α��� X �α� ���ɻ�1 ���� 8�� �ҷ�����
	@Override
	public Map<String, Object> getListByInterestOneNotLogin() {
	    List<rankVO> rVo = mapper.getCategoryRankNotLogin();
	    log.info(rVo);
	    log.info("rVo�� 0�� ī�װ��� " + rVo.get(0).getCategory());
	    
	    String category = rVo.get(0).getCategory();  // ī�װ� �� ����
	    Map<String, String> params = new HashMap<>();
	    params.put("key", category);
	    
	    List<goodsVO> vo = mapper.getListByInterestNotLogin(params);
	    vo.forEach(item -> log.info("rVo 0���� vo�� " + item.getGname()));

	    Map<String, Object> result = new HashMap<>();
	    result.put("category", category);
	    result.put("goodsList", vo);
	    
	    return result;
	}
	
	// �α��� X �α� ���ɻ�2 ���� 8�� �ҷ�����
	@Override
	public Map<String, Object> getListByInterestTwoNotLogin() {
		List<rankVO> rVo = mapper.getCategoryRankNotLogin();
		log.info("rVo�� 1�� ī�װ��� " + rVo.get(1).getCategory());
		
	    String category = rVo.get(1).getCategory();
	    Map<String, String> params = new HashMap<>();
	    params.put("key", category);
	    
        List<goodsVO> vo = mapper.getListByInterestNotLogin(params);
        vo.forEach(item -> log.info("rVo 1���� vo�� " + item.getGname()));
        
	    Map<String, Object> result = new HashMap<>();
	    result.put("category", category);
	    result.put("goodsList", vo);
        
		return result;
	}

	// �α��� X �α� ���ɻ�3 ���� 8�� �ҷ�����	
	@Override
	public Map<String, Object> getListByInterestThreeNotLogin() {
		List<rankVO> rVo = mapper.getCategoryRankNotLogin();
		log.info("rVo�� 2�� ī�װ��� " + rVo.get(2).getCategory());
		
	    String category = rVo.get(2).getCategory();
	    Map<String, String> params = new HashMap<>();
	    params.put("key", category);
	    
        List<goodsVO> vo = mapper.getListByInterestNotLogin(params);
        vo.forEach(item -> log.info("rVo 2���� vo�� " + item.getGname()));
        
	    Map<String, Object> result = new HashMap<>();
	    result.put("category", category);
	    result.put("goodsList", vo);
	    
		return result;
	}

	@Override
	public List<goodsVO> getListByInterestOneLogined() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<goodsVO> getListByInterestTwoLogined() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<goodsVO> getListByInterestThreeLogined() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public goodsVO getOneByGno(int gno) {
		return mapper.getOneByGno(gno);
	}
	
	@Override
    public List<goodsVO> getSearchList(String searchText, int offset, int limit) {
        return mapper.getSearchList(searchText, offset, limit);
    }

    @Override
    public gCatVO getCategory(int gno) {
        return mapper.getCategory(gno);
    }
    @Override
	public int getUpdatehit(goodsVO vo) {
		return mapper.getUpdatehit(vo);
	}
    
    @Override
    public int updateLike(int userNo, int gno) {
    	int countLike = mapper.getLike(userNo, gno);
    	if (countLike == 0) {
    		mapper.insertLike(userNo, gno);
    		mapper.updateLikeCountPlus(gno);
    		return 0; 
    	}else {
    		mapper.deleteLike(userNo, gno);
    		mapper.updatetLikeCountMinus(gno);
    		return 1;
    	}
    }
    
    @Override
    public int getLikeCount(int gno) {
    	return mapper.getLikeCount(gno);
    }
    
    @Override
    public int getLikeChk(int userNo, int gno) {
    	return mapper.getLike(userNo, gno);
    }
		
}