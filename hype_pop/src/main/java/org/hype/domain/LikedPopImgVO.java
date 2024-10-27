package org.hype.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class likedPopImgVO {

	
	private int userNo; // ????ë²???
	private int psNo; // ?????¤í???? ë²???
	private Date likeDate; // ì¢????? ??ì§?
	private String uuid; // uuid
	private String uploadPath; // ??ë¡??? ê²½ë?
	private String fileName; // ???? ?´ë?
	private String psName; // ?????¤í???? ?´ë?
}