package kr.co.withcinephile.notice.store.logic;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.withcinephile.notice.domain.Notice;
import kr.co.withcinephile.notice.store.NoticeStore;

@Repository
public class NoticeStoreLogic implements NoticeStore {

	@Override
	public int registerNotice(SqlSession sqlsession, Notice notice) {
		int result = sqlsession.insert("NoticeMapper.registerNotice", notice);
		return result;
	}

	@Override
	public int removeNotice(SqlSession sqlsession, int noticeNo) {
		int result = sqlsession.delete("NoticeMapper.removeNotice", noticeNo);
		return result;
	}

	@Override
	public int modifyNotice(SqlSession sqlsession, Notice notice) {
		int result = sqlsession.update("NoticeMapper.modifyNotice", notice);
		return result;
	}

	@Override
	public Notice showOneByNo(SqlSession sqlsession, int noticeNo) {
		Notice notice = sqlsession.selectOne("NoticeMapper.showOneByNo", noticeNo);
		return notice;
	}

	@Override
	public List<Notice> showNoticeList(SqlSession sqlsession, int startIdx, int recordsPerPage) {
		RowBounds rowBounds = new RowBounds(startIdx, recordsPerPage);
        return sqlsession.selectList("NoticeMapper.showNoticeList", null, rowBounds);
	}

	@Override
	public int getTotalCount(SqlSession sqlsession) {
		return sqlsession.selectOne("NoticeMapper.getTotalCount");
	}

}
