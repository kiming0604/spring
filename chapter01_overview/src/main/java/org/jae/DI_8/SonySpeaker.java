package org.jae.DI_8;

public class SonySpeaker implements Speaker {
	
	public SonySpeaker() {
		System.out.println("===> 일본의 기술력! 소니 스피커를 켭니다");
	}
	@Override
	public void volumeUp() {
		System.out.println("===> 소니 스피커의 소리를 키웁니다");
	}
	@Override
	public void volumeDown() {
		System.out.println("===> 소니 스피커의 소리를 줄입니다");
	}
}
