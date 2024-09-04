package org.jae.DI_4;

public class SamsungTV implements TV {
	private SonySpeaker sonySpeaker;
	
	public SamsungTV() {
		System.out.println("===> SamsungTV 객체 생성");
	}
	@Override
	public void powerOn() {
		System.out.println("===> TV 키고 드라마 틀어");
	}
	@Override
	public void powerOff() {
		System.out.println("===> 드라마 끝났네 자자");
	}
	@Override
	public void volumeUp() {
		sonySpeaker = new SonySpeaker();
		sonySpeaker.volumeUp();
	}
	@Override
	public void volumeDown() {
		sonySpeaker = new SonySpeaker();
		sonySpeaker.volumeDown();
	}
}
