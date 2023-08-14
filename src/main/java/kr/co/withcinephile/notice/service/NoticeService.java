package kr.co.withcinephile.notice.service;

import org.springframework.stereotype.Service;

import kr.co.withcinephile.notice.domain.Notice;
import kr.co.withcinephile.notice.domain.PageData;

@Service
public interface NoticeService {
	
	public int insertNotice(Notice notice);
	public int deleteNotice(int noticeNo);
	public int updateNotice(Notice notice);
	public Notice selectOneByNo(int noticeNo);
	PageData selectNoticeList(int currentPage);
}
