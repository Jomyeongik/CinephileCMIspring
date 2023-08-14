package kr.co.withcinephile.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.withcinephile.member.domain.Member;
import kr.co.withcinephile.member.service.MemberService;

@Controller
@SessionAttributes({"memberId","memberName"})
public class MemberController {
	@Autowired
	private MemberService service;


	@RequestMapping(value="/member/register.do",method=RequestMethod.GET)
	public String showRegisterForm() {		
		return "member/join";	
	}
	

	@RequestMapping(value="/member/register.do", method=RequestMethod.POST)
	public String registerMember(
			@RequestParam("member-id") String memberId
			,@RequestParam("member-pw") String memberPw
			,@RequestParam("member-name") String memberName
			,@RequestParam("member-email") String memberEmail
			,@RequestParam("member-phone") String memberPhone
			,Model model) {

		Member member = new Member(memberId, memberPw, memberName, memberEmail, memberPhone);
		try {
			int result = service.insertMember(member);
			if(result>0) {
				// ����				
				return"redirect:/mainPage.jsp";
			}else {
				// ����
				model.addAttribute("msg","ȸ�������� �Ϸ���� �ʾҽ��ϴ�.");
				return "common/fail";
			}
		} catch(Exception e) {
			e.printStackTrace(); // �ܼ�â�� ���������� �߰���
			model.addAttribute("msg", e.getMessage()); // �ܼ�â�� �ߴ� �޽����� �� �������� �߰���
			return "common/fail";
		}
 }
	@RequestMapping(value="/member/delete.do", method=RequestMethod.GET)
	public String removeMember(
			@RequestParam("memberId") String memberId
			,Model model) {
		try {
			int result = service.deleteMember(memberId);
			if(result>0) {
				
				return "redirect:/member/logout.do";
			}else {
				model.addAttribute("msg", "ȸ�� Ż�� �Ϸ���� �ʾҽ��ϴ�.");
				return "common/fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg",e.getMessage());
			return "common/fail";
		}
		
	}	
	
	@RequestMapping(value="/member/mypage.do",method=RequestMethod.GET)
	public String modifyViewMember(Model model
			,@RequestParam("memberId") String memberId) {
		Member member = service.selectOneById(memberId);
		try {
			if(member != null) {
				model.addAttribute("member", member);
				return "member/mypage";					
			}else {
				model.addAttribute("msg","ȸ�������� �Ϸ���� �ʾҽ��ϴ�.");
				return "common/fail";						
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage()); 
			return "common/fail";
			}
	}
	
	@RequestMapping(value="/member/mypage.do",method=RequestMethod.POST)
	public String modifyMember(
			@RequestParam("member-id") String memberId
			,@RequestParam("member-pw") String memberPw
			,@RequestParam("member-email") String memberEmail
			,@RequestParam("member-phone") String memberPhone
			,Model model
			,RedirectAttributes redirect
			) {
		Member member = new Member(memberId, memberPw, memberEmail, memberPhone);
		System.out.println(member.toString());
		try {
			int result = service.updateMember(member);
			System.out.println(result);
			if(result>0) {
				// ����
				redirect.addAttribute("memberId", memberId);
				return"redirect:/member/mypage.do";
			}else {
				// ����
				model.addAttribute("msg","ȸ�������� �Ϸ���� �ʾҽ��ϴ�.");
				return "common/fail";
			}
		} catch(Exception e) {
			e.printStackTrace(); // �ܼ�â�� ���������� �߰���
			model.addAttribute("msg", e.getMessage()); // �ܼ�â�� �ߴ� �޽����� �� �������� �߰���
			return "common/fail";
		}
	}

	@RequestMapping(value="/member/login.do",method=RequestMethod.GET)
	public String showLoginForm() {		
		return "member/login";	
	}
	
	@RequestMapping(value="/member/login.do", method=RequestMethod.POST)
	public String memberLogin(
			@RequestParam("member-id") String memberId
			,@RequestParam("member-pw") String memberPw
			, Model model) {
		try {
			Member member = new Member();
			member.setMemberId(memberId);
			member.setMemberPw(memberPw);
			Member mOne = service.selectCheckLogin(member);
			if(mOne != null) {

				model.addAttribute("memberId", mOne.getMemberId());
				model.addAttribute("memberName", mOne.getMemberName());
				return"redirect:/mainPage.jsp";
			}else {
				model.addAttribute("msg", "�����Ͱ� ��ȸ���� �ʾҽ��ϴ�.");
				return "common/fail";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/fail";

		}
	}
	
	@RequestMapping(value="/member/logout.do", method=RequestMethod.GET)
	public String memberLogout(
			HttpSession session_prev
			,SessionStatus session
			, Model model) {
		if(session != null) { 
			session.setComplete();
			if(session.isComplete()) {
			}
			return "redirect:/mainPage.jsp";
		}else {
			model.addAttribute("msg", "�α׾ƿ��� �Ϸ����� ���߽��ϴ�.");
			return "common/fail";
		}
	}
	
	@RequestMapping(value="/member/find.do", method=RequestMethod.GET)
	public String FindInfo(Model model) {
		return "member/IdPw";
	}
}