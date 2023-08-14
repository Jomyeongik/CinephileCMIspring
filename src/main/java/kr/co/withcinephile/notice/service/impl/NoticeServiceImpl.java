package kr.co.withcinephile.notice.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.withcinephile.notice.domain.Notice;
import kr.co.withcinephile.notice.domain.PageData;
import kr.co.withcinephile.notice.service.NoticeService;
import kr.co.withcinephile.notice.store.NoticeStore;

@Service
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	private SqlSession sqlsession;
	@Autowired
	private NoticeStore nStore;
	
	@Override
	public int insertNotice(Notice notice) {
		int result = nStore.registerNotice(sqlsession,notice);
		return result;
	}

	@Override
	public int deleteNotice(int noticeNo) {
		int result = nStore.removeNotice(sqlsession,noticeNo);
		return result;
	}

	@Override
	public int updateNotice(Notice notice) {
		int result = nStore.modifyNotice(sqlsession,notice);
		return result;
	}

	@Override
	public Notice selectOneByNo(int noticeNo) {
		Notice notice = nStore.showOneByNo(sqlsession,noticeNo);
		return notice;
	}

	@Override
	public PageData selectNoticeList(int currentPage) {
		int recordsPerPage = 10; // 한 페이지에 표시할 레코드 수
        int startIdx = (currentPage - 1) * recordsPerPage;

        List<Notice> notices = nStore.showNoticeList(sqlsession, startIdx, recordsPerPage);
        int totalRecordCount = nStore.getTotalCount(sqlsession);

        String pageNavi = generatePageNavi(currentPage, totalRecordCount);

        return new PageData(notices, pageNavi);
	}

	private String generatePageNavi(int currentPage, int totalRecordCount) {
        int recordsPerPage = 10; // 한 페이지에 표시할 레코드 수
        int naviCountPerPage = 5; // 페이징 네비게이션에 표시될 페이지 수
        int totalPageCount = (int) Math.ceil((double) totalRecordCount / recordsPerPage);
        int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
        int endNavi = startNavi + naviCountPerPage - 1;

        if (endNavi > totalPageCount) {
            endNavi = totalPageCount;
        }

        StringBuilder sb = new StringBuilder();
        if (startNavi > 1) {
            sb.append("<a href='/notice/list.do?currentPage=").append(startNavi - 1).append("'>[이전]</a>");
        }

        for (int i = startNavi; i <= endNavi; i++) {
            sb.append("<a href='/notice/list.do?currentPage=").append(i).append("'>").append(i).append("</a>&nbsp;");
        }

        if (endNavi < totalPageCount) {
            sb.append("<a href='/notice/list.do?currentPage=").append(endNavi + 1).append("'>[다음]</a>");
        }

        return sb.toString();
    }	
	
}
