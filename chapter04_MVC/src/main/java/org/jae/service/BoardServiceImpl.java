package org.jae.service;

import java.util.List;

import org.jae.domain.BoardAttachVO;
import org.jae.domain.BoardVO;
import org.jae.domain.Criteria;
import org.jae.mapper.BoardAttachMapper;
import org.jae.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper mapper;

	@Autowired
	private BoardAttachMapper attachmapper;

//	@Override
//	public List<BoardVO> getOneList() {
//		log.info("getOneList..");
//		return mapper.getOneList();
//	}
	
	
	// 여러개의 mapper를 동시에 써야하니 트랜젝션 써줘야합니다
	@Transactional
	@Override
	public int register(BoardVO vo) {
	
		
		int result = mapper.insert(vo);

		// 3. 첨부 파일 등록 (게시글 번호가 필요)
	    if (vo.getAttachList() != null && !vo.getAttachList().isEmpty()) {
	        vo.getAttachList().forEach(attach -> {
	            attach.setBno(vo.getBno());  // 게시글 번호 설정
	            attachmapper.insert(attach); // 첨부 파일 등록
	        });
	    }
	    return result;
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
		return mapper.getTotal();
	}
	@Override
	public List<BoardAttachVO> getAttachList(int bno) {
		log.info("getAttachList... " + bno  );
		return attachmapper.findByBno(bno);
	}
}
