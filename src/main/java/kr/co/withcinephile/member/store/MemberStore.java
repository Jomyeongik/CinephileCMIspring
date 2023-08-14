package kr.co.withcinephile.member.store;

import org.apache.ibatis.session.SqlSession;

import kr.co.withcinephile.member.domain.Member;

public interface MemberStore {

	int registerMember(SqlSession sqlSession, Member member);

	int removeMember(SqlSession sqlSession, String memberId);

	int modifyMember(SqlSession sqlSession, Member member);

	Member showOneById(SqlSession sqlSession, String memberId);

	Member memberLoginCheck(SqlSession sqlSession, Member member);

}
