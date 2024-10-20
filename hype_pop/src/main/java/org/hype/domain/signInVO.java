package org.hype.domain;

import java.sql.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class signInVO {
	


	 int userNo; // ȸ�� ��ȣ
	 String userId; // ���̵�
	 String userPw; // ��й�ȣ
	 String userEmail; // �̸���
	 String userName; // �̸�
	 int userNumber; // ��ȭ��ȣ
	 mCatVO userInterest; // ���ɻ�
	 Date regDate; // ������
	 Date lastLoginDate; // ������ �α��� ��¥
	 boolean enabled; // ���� Ȱ��ȭ ����
	 int auth; // ����
	 List<popStoreVO> gLikeList; // ���� ���ƿ� ���
	 List<goodsVO> pLikeList; // ����� ���ƿ� ���
	 String snsToken; // Access Token
	 String snsType; // sns ����

}