package org.jae.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Log4j
@Component
@Aspect
public class logAdvice {
	
	@Before("execution(* org.jae.service.SampleService*.*(..))")
	public void logBefore() {
		log.info("=======================");
	}

}
