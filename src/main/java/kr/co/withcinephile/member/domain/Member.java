package kr.co.withcinephile.member.domain;

import java.sql.Timestamp;

public class Member {
	
	private String memberName;
	private String memberId;
	private String memberPw;
	private String memberEmail;
	private String memberPhone;
	private Timestamp enrollDate;
	
	
	
	public Member() {
		super();
	}
	
	
	public Member(String memberName, String memberId, String memberPw, String memberEmail, String memberPhone) {
		super();
		this.memberName = memberName;
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberEmail = memberEmail;
		this.memberPhone = memberPhone;
	}


	public Member(String memberId, String memberPw, String memberEmail, String memberPhone) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberEmail = memberEmail;
		this.memberPhone = memberPhone;
	}


	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public Timestamp getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(Timestamp enrollDate) {
		this.enrollDate = enrollDate;
	}
	@Override
	public String toString() {
		return "회원정보 [이름=" + memberName + ", 아이디=" + memberId + ", 비밀번호=" + memberPw
				+ ", 이메일=" + memberEmail + ", 전화번호=" + memberPhone + ", 가입일=" + enrollDate + "]";
	}
	
	
}
