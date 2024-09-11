package org.jae.service;

import java.util.List;

import org.jae.domain.BoardVO;
import org.jae.domain.Criteria;
import org.jae.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardMapper mapper;
	
	
//	@Override
//public List<BoardVO> getList() {
//	log.info("getList..");
//	return mapper.getList();
//}
@Override
	public int register(BoardVO vo) {
		log.info("regiser..." + vo);
		return mapper.insert(vo);
	}
@Override
	public int modify(BoardVO vo) {
		log.info("modify.." + vo);
		return mapper.update(vo);
	}
@Override
	public int remove(int bno) {
		log.info("remove...." + bno);
		return mapper.delete(bno);
	}
	
	
	@Override
public BoardVO get(int bno) {
	log.info("get..." + bno);
	return mapper.read(bno);
}
	@Override
	public List<BoardVO> getList(Criteria cri) {
	
		return mapper.getList(cri);
	}
	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		return  mapper.getTotal();
	}
}
