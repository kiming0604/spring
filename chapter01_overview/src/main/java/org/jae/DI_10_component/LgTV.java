package org.jae.DI_10_component;

import org.jae.DI_10_component.TV;
import org.springframework.stereotype.Component;

/*
 *  java => @Component
 *  xml => <bean class="org.jae.DI_10_component.LgTV>
 *  
 *  java => @Component("tv")
 *  xml => <bean id="tv" class="org.jae.DI_10_component.LgTV>
 * 
 *  @Component 와 @Configuration/@Bean은 기능이 비슷
 *  @Component는 클래스 단위
 *  @Bean은 메서드 단위
 * */
@Component("tv")
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
