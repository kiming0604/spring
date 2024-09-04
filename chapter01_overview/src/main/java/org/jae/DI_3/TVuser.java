package org.jae.DI_3;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVuser{
	public static void main(String[] args) {
	    
		//1. Spring 컨테이너 구동
		AbstractApplicationContext ctx = 
				 new GenericXmlApplicationContext("applicationContext3.xml");
		TV stv1 = (SamsungTV)ctx.getBean("stv");
		TV stv2 = (SamsungTV)ctx.getBean("stv");
		
		stv1.powerOn();
		stv1.volumeUp();
		stv1.volumeDown();
		stv1.powerOff();
		
		System.out.println("_______________________");
		
		TV ltv1 = (LgTV)ctx.getBean("ltv");
		TV ltv2 = (LgTV)ctx.getBean("ltv");
		
		ltv1.powerOn();
		ltv1.volumeUp();
		ltv1.volumeDown();
		ltv1.powerOff();
		
		System.out.println(stv1 == stv2 ? "같은 객체" : "다른 객체");
		System.out.println(ltv1 == ltv2 ? "같은 객체" : "다른 객체");
	}
   

}
