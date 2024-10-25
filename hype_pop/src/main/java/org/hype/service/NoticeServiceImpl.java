package org.hype.service;

import java.util.List;
import org.hype.domain.noticeVO;
import org.hype.domain.qnaVO;
import org.hype.mapper.SupportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	SupportMapper supportmapper;
	
	@Override
	public List<noticeVO> getNoticesWithPaging(int pageNum, int amount) {
	    int startRow = (pageNum - 1) * amount;
	    int endRow = pageNum * amount; 
	    return supportmapper.getNoticesWithPaging(startRow, endRow);
	}


	@Override
	public List<qnaVO> getInquiriesWithPaging(int pageNum, int amount, int userNo) {
		int startRow = (pageNum - 1) * amount;
	    int endRow = pageNum * amount; 
	    return supportmapper.getInquiriesWithPaging(startRow, endRow, userNo);
	}

	@Override
	public boolean createNotice(noticeVO notice) {
		return supportmapper.insertNotice(notice);
	}

	@Override
	public noticeVO getNoticeInfo(int noticeNo) {
		
		return supportmapper.getNoticeInfo(noticeNo);
	}

	@Override
	public int updateNotice(noticeVO nvo) {
		
		return supportmapper.updateNotice(nvo);
	}

	@Override
	public int deleteNotice(int noticeNo) {
		
		return supportmapper.deleteNotice(noticeNo);
	}

	@Override
	public boolean createInquiry(qnaVO qna) {

		return supportmapper.insertQna(qna);
	}

	@Override
	public qnaVO getInquiryInfo(int qnaNo) {
		
		return supportmapper.getInquiryInfo(qnaNo);
	}

	@Override
	public int updateAnswer(qnaVO qvo) {
		
		return supportmapper.updateAnswer(qvo);
	}

	@Override
	public int deleteInquiry(int qnaNo) {

		return supportmapper.deleteInquiry(qnaNo);
	}


	@Override
	public int getTotalNoticeCount() {
		// TODO Auto- method stub
		return supportmapper.getTotalNoticeCount();
	}


	@Override
	public int getTotalInquiryCount(int userNo) {
		
		return supportmapper.getTotalInquiryCount(userNo);
	}
}