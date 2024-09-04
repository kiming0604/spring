package org.jae.DI_1;

public class SamsungTV {
	public SamsungTV() {
		System.out.println("===> SamsungTV 객체 생성");
	}
	public void powerOn() {
		System.out.println("===> TV 키고 드라마 틀어");
	}
	public void powerOff() {
		System.out.println("===> 드라마 끝났네 자자");
	}
	public void volumeUp() {
		System.out.println("===> 설거지 할때 안들려 소리 키워라");
	}
	public void volumeDown() {
		System.out.println("===> 엄마 자게 소리 줄여라");
	}
}
