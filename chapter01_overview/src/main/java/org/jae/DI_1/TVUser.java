package org.jae.DI_1;

public class TVUser {
	public static void main(String[] args) {
		SamsungTV stv = new SamsungTV();
        stv.powerOn();
        stv.volumeUp();
        stv.volumeDown();
        stv.powerOff();
        
        System.out.println("_______________________");
        
        LgTV ltv = new LgTV();
        
        ltv.powerOn();
        ltv.volumeUp();
        ltv.volumeDown();
        ltv.powerOff();
	}
	
	

}
