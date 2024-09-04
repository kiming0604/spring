package org.jae.DI_2;

import org.jae.DI_2.SamsungTV;

public class TVuser{
	public static void main(String[] args) {
		TV ltv = (TV) new org.jae.DI_2.LgTV();
		TV stv = (TV) new SamsungTV();
		
		ltv.powerOn();
		ltv.volumeUp();
		ltv.volumeDown();
		ltv.powerOff();
		
		System.out.println("_______________________");
		
		stv.powerOn();
		stv.volumeUp();
		stv.volumeDown();
		stv.powerOff();
		
	}
   

}
