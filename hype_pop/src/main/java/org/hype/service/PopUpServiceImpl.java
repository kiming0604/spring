package org.hype.service;

import java.util.List;

import org.hype.domain.popStoreVO;
import org.hype.mapper.PopUpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PopUpServiceImpl implements PopUpService{
	
	@Autowired
	PopUpMapper mapper;
	

	@Override
	public List<popStoreVO> getPopularPopUps() {
		List<popStoreVO> popUps = mapper.getPopularPopUps();
		return popUps;
	}
}
