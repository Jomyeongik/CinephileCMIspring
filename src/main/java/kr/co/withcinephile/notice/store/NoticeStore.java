package kr.co.withcinephile.notice.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.withcinephile.notice.domain.Notice;

public interface NoticeStore {

	int registerNotice(SqlSession sqlsession, Notice notice);

	int removeNotice(SqlSession sqlsession, int noticeNo);

	int modifyNotice(SqlSession sqlsession, Notice notice);

	Notice showOneByNo(SqlSession sqlsession, int noticeNo);

	List<Notice> showNoticeList(SqlSession sqlsession, int startIdx, int recordsPerPage);

	int getTotalCount(SqlSession sqlsession);

}
