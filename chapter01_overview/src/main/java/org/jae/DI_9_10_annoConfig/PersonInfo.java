package org.jae.DI_9_10_annoConfig;

public class PersonInfo {
	private Person person;

	public PersonInfo() {}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	public void info() {
		System.out.println("이름 : " + person.getName() );
		System.out.println("나이 : " + person.getAge() );
		System.out.println("취미 : " + person.getHobbies() );
	}
	

}
