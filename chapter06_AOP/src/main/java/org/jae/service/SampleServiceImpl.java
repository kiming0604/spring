package org.jae.service;

import org.springframework.stereotype.Service;

@Service
public class SampleServiceImpl implements SampleService {
	
	@Override
	public int doAdd(String str1, String st2) throws Exception {
		
		return Integer.parseInt(str1) + Integer.parseInt(st2);
	}

}
