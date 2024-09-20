package org.jae.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BoardAttachVO {
     private String uuid,uploadPath,fileName;
     private int bno;
     // 게시글의 다운로드 기능을 위한 VO
}