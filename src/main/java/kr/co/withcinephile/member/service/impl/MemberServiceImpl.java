package kr.co.withcinephile.member.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import kr.co.withcinephile.member.domain.Member;
import kr.co.withcinephile.member.service.MemberService;
import kr.co.withcinephile.member.store.MemberStore;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private SqlSession sqlSession; //SqlSessionTemplate»ó¼Ó public class SqlSessionTemplate implements SqlSession, DisposableBean
	@Autowired
	private MemberStore mStore;

	@Override
	public int insertMember(Member member) {
		int result = mStore.registerMember(sqlSession, member);
		return result;
	}

	@Override
	public int deleteMember(String memberId) {
		int result = mStore.removeMember(sqlSession,memberId);
		return result;
	}

	@Override
	public int updateMember(Member member) {
		int result = mStore.modifyMember(sqlSession, member);
		return result;
	}

	@Override
	public Member selectOneById(String memberId) {
		Member mOne= mStore.showOneById(sqlSession,memberId);
		return mOne;
	}

	@Override
	public Member selectCheckLogin(Member member) {
		Member mOne = mStore.memberLoginCheck(sqlSession,member);
		return mOne;
	}

}
