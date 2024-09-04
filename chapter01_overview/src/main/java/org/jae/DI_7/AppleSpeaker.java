package org.jae.DI_7;

public class AppleSpeaker implements Speaker {
    public AppleSpeaker() {
		System.out.println("===> 천조국의 자랑! 애플 스피커를 켭니다");
	}
	@Override
	public void volumeUp() {
		System.out.println("===> 애플 스피커의 소리를 키웁니다");
	}
	@Override
	public void volumeDown() {
		System.out.println("===> 애플 스피커의 소리를 줄입니다");
	}
}
