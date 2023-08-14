package kr.co.withcinephile.notice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.withcinephile.notice.domain.Notice;
import kr.co.withcinephile.notice.domain.PageData;
import kr.co.withcinephile.notice.service.NoticeService;

@Controller
public class NoticeController {
	@Autowired
	private NoticeService service;
	
	@RequestMapping(value="/notice/insert.do", method=RequestMethod.GET)
	public String showInsertForm() {
		return "notice/insert";
	}
	
	@RequestMapping(value="/notice/insert.do",method=RequestMethod.POST)
	public String InsertNotice(
			@RequestParam("noticeSubject") String noticeSubject
			,@RequestParam("noticeContent") String noticeContent
			,Model model
			) {
		Notice notice = new Notice(noticeSubject,noticeContent);
		try {
			int result = service.insertNotice(notice);
			if(result > 0) {
				return "redirect:/list.jsp";
			}else{
				model.addAttribute("msg", "공지사항 등록 실패");
				return "common/fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/fail";
		}
		
	}
	
	@RequestMapping(value="notice/delete.do", method=RequestMethod.GET)
	public String DeleteNotice(
			@RequestParam("noticeNo") int noticeNo
			,Model model
			) {
		int result = service.deleteNotice(noticeNo);
		try {
			if(result>0) {
				return "redirect:/list.jsp";
			}else {
				model.addAttribute("msg", "공지사항 삭제 실패");
				return "common/fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/fail";
		}
		
	}
	
	@RequestMapping(value="notice/detail.do", method=RequestMethod.GET)
	public String showDetail(
			@RequestParam("noticeNo") int noticeNo
			,Model model) {
		Notice notice = service.selectOneByNo(noticeNo);
		try {
			if(notice != null) {
				model.addAttribute("notice", notice);
				return "notice/detail";
			}else {
				model.addAttribute("msg", "공지사항 상세 이동 실패");
				return "common/fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/fail";
		}
		
	}
	
	@RequestMapping(value="notice/modify.do", method=RequestMethod.GET)
	public String showNoticeModifyForm(
			@RequestParam("noticeNo") int noticeNo
			,Model model) {
		Notice notice = service.selectOneByNo(noticeNo);		
		try {
			if(notice != null) {
				model.addAttribute("notice", notice);
				return "notice/modify";
			}else {
				model.addAttribute("msg", "공지사항 수정 이동 실패");
				return "common/fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/fail";
		}
	}
	
	@RequestMapping(value="notice/modify.do", method=RequestMethod.POST)
	public String updateNotice(
			@RequestParam("noticeNo") int noticeNo
			,@RequestParam("noticeSubject") String noticeSubject
			,@RequestParam("noticeContent") String noticeContent
			,Model model) {
		Notice notice = new Notice(noticeNo,noticeSubject,noticeContent);
		int result = service.updateNotice(notice);
		try {
			if(result>0) {
				return "redirect:/list.jsp";
			}else {
				model.addAttribute("msg", "공지사항 수정 실패");
				return "common/fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/fail";
		}
		
		
	}

    @RequestMapping(value = "/notice/list.do", method = RequestMethod.GET)
    public String showNoticeList(@RequestParam(name = "currentPage", defaultValue = "1") int currentPage, Model model) {
        PageData pageData = service.selectNoticeList(currentPage);
        try {
        	if(pageData != null) {
        		model.addAttribute("pageData", pageData);
        		return "notice/list";        		
        	}else {
        		model.addAttribute("msg", "리스트 불러오기 실패");
				return "common/fail";
        	}			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/fail";
		}
    }
	
	
}
