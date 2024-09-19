package org.jae.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BoardVO {
     
	private int bno , replycnt;
	private String title,content,writer;
	private Date regdate, updatedate;	 
}
