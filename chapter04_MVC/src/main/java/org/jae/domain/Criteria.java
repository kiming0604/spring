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
public class Criteria {
	private int pageNum;  // 페이지 번호
	private int amount;  // 가져올 게시글 수

}
