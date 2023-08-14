package kr.co.withcinephile.member.store.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.withcinephile.member.domain.Member;
import kr.co.withcinephile.member.store.MemberStore;

@Repository
public class MemberStoreLogic implements MemberStore{

	@Override
	public int registerMember(SqlSession sqlSession, Member member) {
		int result = sqlSession.insert("MemberMapper.registerMember", member);
		return result;
	}

	@Override
	public int removeMember(SqlSession sqlSession, String memberId) {
		int result = sqlSession.delete("MemberMapper.removeMember",memberId);
		return result;
	}

	@Override
	public int modifyMember(SqlSession sqlSession, Member member) {
		int result = sqlSession.update("MemberMapper.modifyMember", member);
		return result;
	}

	@Override
	public Member showOneById(SqlSession sqlSession, String memberId) {
		Member mOne = sqlSession.selectOne("MemberMapper.showOneById",memberId);
		return mOne;
	}

	@Override
	public Member memberLoginCheck(SqlSession sqlSession, Member member) {
		Member mOne = sqlSession.selectOne("MemberMapper.memberLoginCheck",member);
		return mOne;
	}

}
