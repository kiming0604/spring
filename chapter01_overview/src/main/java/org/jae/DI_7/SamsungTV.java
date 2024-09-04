package org.jae.DI_7;

public class SamsungTV implements TV {
	private Speaker Speaker;
	private int price;
	
	public SamsungTV() {
		System.out.println("===> SamsungTV 객체 생성");
	}
	public SamsungTV(Speaker speaker) {
		System.out.println("===> Samsung 스마트TV를 켭니다");
		this.Speaker = speaker;
	}
	public SamsungTV(Speaker speaker, int price) {
		System.out.println("===> Samsung 스마트 비스포크 TV를 켭니다");
		this.Speaker = speaker;
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
