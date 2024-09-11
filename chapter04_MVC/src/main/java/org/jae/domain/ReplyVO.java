package org.jae.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReplyVO {

	private int rno,bno;
	private String reply,replyer;
	private Date replyDate , updateDate;
    
}
