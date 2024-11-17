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
public class likedGoodsImgVO {

    private int userNo; // ???? ï¿????
    private int gno; // ???? ï¿????
    private Date likeDate; // ï¿?????? ??ï¿??
    private String gname; // ????ï¿??
    private String uuid; // uuid
    private String uploadPath; // ??ï¿???? ê²½ï¿½?
    private String fileName; // ???? ?ï¿½ï¿½?

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