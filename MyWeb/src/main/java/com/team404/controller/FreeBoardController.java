package com.team404.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.team404.command.FreeBoardVO;
import com.team404.freeboard.service.FreeBoardService;
import com.team404.util.Criteria;
import com.team404.util.PageVO;

@Controller
@RequestMapping(value = "/freeBoard")
public class FreeBoardController {

	@Autowired
	@Qualifier("freeBoardService")
	private FreeBoardService freeBoardService;
	
	// 등록 화면
	@RequestMapping(value = "/freeRegist", method = RequestMethod.GET)
	public String freeRegist() {
		return "freeBoard/freeRegist";
	}
	
	// 목록 화면
	@RequestMapping(value = "/freeList", method = RequestMethod.GET)
	public String freeList(Model model, Criteria cri) {
		// 서비스에서 getList호출 mapper에서 getList를 호출
		// 컨트롤러에서는 모델에 boardList이름으로 결과를 담아서 화면에서 출력
		
		// 일반
		// model.addAttribute("list", freeBoardService.getList());
		
		// 기본 페이징
//		ArrayList<FreeBoardVO> list = freeBoardService.getList(cri);
//		int total = freeBoardService.getTotal();
//		PageVO pageVO = new PageVO(cri, total); 
		
		System.out.println(cri.toString());
		//검색 페이징
		ArrayList<FreeBoardVO> list = freeBoardService.getList(cri);
		int total = freeBoardService.getTotal(cri);
		PageVO pageVO = new PageVO(cri, total);
		
		model.addAttribute("pageVO", pageVO);  //페이지네이션 정보
		model.addAttribute("list", list); // 게시글 정보
		
		return "freeBoard/freeList";
	}
	
	@RequestMapping(value="/registForm", method = RequestMethod.POST)
	public String registForm(FreeBoardVO vo, RedirectAttributes RA) {
		System.out.println(vo.toString());
		// 서비스의 regist메서드를 생성하고, mapper에 regist메서드를 생성한 후에
		// 마이바티스 xml에서 db에서 insert처리
		freeBoardService.regist(vo);
		
		// 리다이렉트로 1회성 소멸데이터로 리다이렉트로 한번만 사용하고 싶을때
		RA.addFlashAttribute("msg", "게시물이 정상적으로 등록되었습니다.");
		
		// 다시 컨트롤러를 태워서 보냄 redirect:/ 
		return "redirect:/freeBoard/freeList";
	}
	
//	// 상세 화면
//	@RequestMapping(value = "/freeDetail", method = RequestMethod.GET)
//	public String freeDetail(@RequestParam("bno") int bno, Model model) {
//		model.addAttribute("content", freeBoardService.content(bno));
//		
//		return "freeBoard/freeDetail";
//	}
//
//	// 수정 화면
//	@RequestMapping(value = "/freeModify", method = RequestMethod.POST)
//	public String freeModify(@RequestParam("bno") int bno, Model model) {
//		System.out.println(bno);
//		
//		model.addAttribute("content", freeBoardService.content(bno));
//		
//		
//		return "freeBoard/freeModify";
//	}
	
	// 상세보기, 수정화면 (동일한 기능이기 때문에 {}로 묶어서 사용)
	@RequestMapping(value= {"/freeDetail", "/freeModify"})
	public void view(@RequestParam("bno") int bno, Model model) {
		model.addAttribute("content", freeBoardService.content(bno));
		// 요청의 경로로 리졸버 뷰로 전달됩니다.
		
	}
	
	//게시글 수정 요청
	@RequestMapping(value="/freeUpdate")
	public String freeUpdate(FreeBoardVO vo, RedirectAttributes RA) {
		// 서비스의 update메서드 실행, mapper의 update메서드 실행
		// 컨트롤러에는 결과를 반환받아서 수정 성공시 RA에 msg이름으로 "게시물 수정이 정상 처리되었습니다"
		// 수정 실패시 RA에 msg이름으로 "게시물 수정에 실패했습니다."
		// 화면처리를 List화면으로 이동
		System.out.println(freeBoardService.update(vo));
		
		if(freeBoardService.update(vo) == true) {
			RA.addFlashAttribute("msg", "게시물 수정이 정상 처리되었습니다.");
			return "redirect:/freeBoard/freeList";
			
		} else {
			RA.addFlashAttribute("msg", "게시물 수정에 실패했습니다.");
			return "redirect:/freeBoard/freeList";
		}
	}
	
	@RequestMapping("/freeDelete")
	public String freeDelete(@RequestParam("bno") int bno, RedirectAttributes RA) {
		// Service에 boolean delete메서드를 실행, mapper delete메서드 실행
		// 삭제 성공시 msg에 "게시물이 삭제 되었습니다"를 저장
		// 삭제 실패시 msg에 "게시물 삭제에 실패했습니다."를 저장
		// 화면을 List로 이동

		if(freeBoardService.delete(bno) == true) {
			RA.addFlashAttribute("msg", "게시물 삭제가 정상 처리 되었습니다.");
			return "redirect:/freeBoard/freeList";
		} else {
			RA.addFlashAttribute("msg", "게시물 삭제에 실패했습니다.");
			return "redirect:/freeBoard/freeList";
		}
		
	}
	
	

}
