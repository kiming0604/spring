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
public class LikedGoodsImgVO {

    private int userNo; // 회원 번호
    private int gno; // 상품 번호
    private Date likeDate; // 좋아요 날짜
    private String gname; // 상품명
    private String uuid; // uuid
    private String uploadPath; // 업로드 경로
    private String fileName; // 파일 이름

    @Override
    public String toString() {
        return "likedGoodsImgVO{" +
                "userNo=" + userNo +
                ", gNo=" + gno +
                ", likeDate=" + likeDate +
                ", gName='" + gname + '\'' +
                ", uuid='" + uuid + '\'' +
                ", uploadPath='" + uploadPath + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}