package kr.co.withcinephile.member.service;

import org.springframework.stereotype.Service;

import kr.co.withcinephile.member.domain.Member;
import kr.co.withcinephile.notice.domain.PageData;

@Service
public interface MemberService {

	public int insertMember(Member member);
	
	public int deleteMember(String memberId);
	
	public int updateMember(Member member);
	
	public Member selectOneById(String memberId);
	
	public Member selectCheckLogin(Member member);
	
}
