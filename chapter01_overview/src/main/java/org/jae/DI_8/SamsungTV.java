package org.jae.DI_8;

public class SamsungTV implements TV {
	private Speaker Speaker;
	private int price;
	
	public SamsungTV() {
		System.out.println("===> SamsungTV 객체 생성");
	}
	
	public void setSpeaker(Speaker speaker) {
		System.out.println("===> 리모컨을 눌러 스피커를 켭니다");
		Speaker = speaker;
	}

	public void setPrice(int price) {
		System.out.println("===> 가격을 확인합니다");
		this.price = price;
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
		Speaker.volumeUp();
	}
	@Override
	public void volumeDown() {
		Speaker.volumeDown();
	}
}
