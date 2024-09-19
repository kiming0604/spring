package org.jae.service;

import org.jae.mapper.Sample1Mapper;
import org.jae.mapper.Sample2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

// 어노테이션 반드시 붙이기
@Log4j
@Service
public class SampleTxServiceImpl implements SampleTxService {

	@Autowired
	private Sample1Mapper mapper1;

	@Autowired
	private Sample2Mapper mapper2;

	@Transactional
	@Override
	public void addData(String value) {
		log.info("mapper1....");
		mapper1.insertCol1(value);
		log.info("mapper2....");
		mapper2.insertCol2(value);

		log.info("end!");

	}
}
