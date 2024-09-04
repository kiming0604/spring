package org.jae.DI_9_10_annoConfig;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 *  sts3 버전에서 사용 시 해당 프로젝트에 CGLIB 라이브러리 추가
 *  
 *  <dependency>
     <groupId>cglib</groupId>
       <artifactId>cglib</artifactId>
     <version>2.2.2</version>
   </dependency>
 *  
 * */
@Configuration // == application Context와 같은 역할
public class AnnoConfig {

	 @Bean // 이걸로 bean을 만든다 (human1 이라는 id를 가지는 bean을 만듦)
	 public Person human1() {
		 Set<String> hobbies = new HashSet<String>();
		 hobbies.add("할머니 리어카 부수기");
		 hobbies.add("할아버지 홍삼캔디 압수하기");
		 hobbies.add("우는 아이 사탕 뺏기");
		 hobbies.add("템플스테이");
		 
		 Person person = new Person();
		 person.setName("애기요셉");
		 person.setAge(10);
		 person.setHobbies(hobbies);
		 
		 return person;
	 }
	 
	 @Bean(name="human2") // bean이름을 지정하면 메서드 이름을 막 지어도 상관 x (human2 라는 id를 가지는 bean을 만듦)
	 public Person abc() {
		 Set<String> hobbies = new HashSet<String>();
		 hobbies.add("잉어킹 회뜨기");
		 hobbies.add("피카츄한테 아이폰 충전 짬 때리기");
		 hobbies.add("웅이 요리 맛없다고 하기");

		 
		 Person person = new Person();
		 person.setName("지우");
		 person.setAge(14);
		 person.setHobbies(hobbies);
		 
		 return person;
	 }
	 
	 @Bean(name="pInfo") // bean이름을 지정하면 메서드 이름을 막 지어도 상관 x (human2 라는 id를 가지는 bean을 만듦)
	 public PersonInfo pInfo() {
		 Set<String> hobbies = new HashSet<String>();
		 hobbies.add("이슬이 누나 보러가기");
		 hobbies.add("봉미선 삼겹살 배라고 놀리기");
		 hobbies.add("신형만 발냄새 맡고 냄새난다고 놀리기");

		 
		 Person person = new Person();
		 person.setName("짱구");
		 person.setAge(5);
		 person.setHobbies(hobbies);
		 
		 PersonInfo info = new PersonInfo();
		 info.setPerson(person);
		 return info;
	 }
}
