package org.jae.DI_7;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVuser{
	public static void main(String[] args) {
	    
		//1. Spring 컨테이너 구동
		AbstractApplicationContext ctx = 
				 new GenericXmlApplicationContext("applicationContext7.xml");
		
		TV stv1 = (SamsungTV)ctx.getBean("stv");
		stv1.powerOn();
		stv1.volumeUp();
		stv1.volumeDown();
		stv1.powerOff();
		
		System.out.println("_______________________");
		
		TV stv2 = (SamsungTV)ctx.getBean("stv2");
		stv2.powerOn();
		stv2.volumeUp();
		stv2.volumeDown();	
		stv2.powerOff();
		
		System.out.println(stv1 == stv2 ? "같은 객체" : "다른 객체");

	}
   

}
