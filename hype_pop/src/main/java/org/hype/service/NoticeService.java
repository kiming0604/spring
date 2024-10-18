package org.hype.service;

import java.util.List;
import org.hype.domain.noticeVO;
import org.hype.domain.qnaVO;
import org.springframework.stereotype.Service;

@Service
public interface NoticeService {
	public List<noticeVO> getNoticesWithPaging(int pageNum, int amount);
	
	public int getTotalNoticeCount();

	public List<qnaVO> getInquiriesWithPaging(int pageNum, int amount);
	
	public int getTotalInquiryCount();
	
	public boolean createNotice(noticeVO notice);

	public noticeVO getNoticeInfo(int noticeNo);

	public int updateNotice(noticeVO nvo);
	
	public int deleteNotice(int noticeNo);

	public boolean createInquiry(qnaVO qna);

	public qnaVO getInquiryInfo(int qnaNo);

	public int updateAnswer(qnaVO qvo);

	public int deleteInquiry(int qnaNo);
	
}