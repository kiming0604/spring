package org.jae.domain;

import lombok.Setter;

import lombok.Getter;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Ticket {
	public int tno;
	private String owner, grade;

}
