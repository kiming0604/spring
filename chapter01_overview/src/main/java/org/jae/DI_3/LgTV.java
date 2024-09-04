package org.jae.DI_3;

public class LgTV implements TV {
	public LgTV() {
		System.out.println("===> LgTV 객체 생성");
	}
	@Override
	public void powerOn() {
		System.out.println("===> TV 키고 뉴스 틀어");
	}
	@Override
	public void powerOff() {
		System.out.println("===> 아빠 안잔다");
	}
	@Override
	public void volumeUp() {
		System.out.println("===> 아빠 안들린다 소리 키워라");
	}
	@Override
	public void volumeDown() {
		System.out.println("===> 아빠 귀 터지겠다 소리 줄여라");
	}
}
