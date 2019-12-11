package com.team404.controllerTest;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team404.command.TestVO;

@RestController
@RequestMapping("/restControl")
public class RestBasicController {

	// 1. RestController는 기본적으로 return에 실린값이 리졸버 뷰로 전달되는 것이 아닌
	// 요청된 주소로 반환됩니다.

	@RequestMapping(value = "/getText", produces = "text/html; charset=utf-8")
	public String getText() {
		return "안녕하세요";
	}

	@RequestMapping(value = "/getObject")
	public TestVO getObject() {

		TestVO vo = new TestVO(20, "홍길순", "kkk123");
		return vo;
	}

	// 나 num을 받고, 컬렉션 보낼게
	@RequestMapping(value="/getCollection")
	public ArrayList<TestVO> getCollection(@RequestParam("num") int num){
		
		ArrayList<TestVO> list = new ArrayList<>();
		for(int i = 0; i <= num; i++) {
			TestVO vo = new TestVO(i, "길자"+i, "kkk"+i);
			list.add(vo);
		}
		return list;
	}

	// String으로 값을 2개 받고, Map형태로 반환할게 
	@RequestMapping("/getPath/{id}/{pw}")
	public HashMap<String, TestVO> getPath(@PathVariable("id") String id,
											@PathVariable("pw") String pw
	) {
		
		HashMap<String, TestVO> map = new HashMap<>();
		map.put("info", new TestVO(10, "홍길순", "hhh"));
		
		return map;
	}
	
	// RequestBody Json객체를 자바의 vo에 자동맵핑합니다.
	// 구글확장프로그램 rest Client설치
	
	@RequestMapping(value = "/getJson", produces = "text/html; charset=utf-8")
	public ArrayList<TestVO> getJson(@RequestBody TestVO vo, HttpServletRequest request){
		
		ArrayList<TestVO> list = new ArrayList<>();
		list.add(new TestVO(20, "신사임당", "kkkk123"));
		
		System.out.println("주소: " + request.getRemoteAddr());
		
		
		return list;
	}
	
	// REST API GetMember/값1/값2의 url로 호출해여
	// 두 값이 동일하다면 TestVO에 (40, 값1, 홍길동)을 담아 반환하고, 그렇지 않으면 null을 반환하는 메서드를 생성
	// rest Client호출해서 결과 확인
	@RequestMapping("/GetMember/{var1}/{var2}")
	public TestVO GetMember(@PathVariable("var1") String var1,
							@PathVariable("var2") String var2
	) {
		if(var1.equals(var2)) {
			return new TestVO(40, var1, "홍길동");
		} else {
			return null;
		}
	}
	
//	@RequestMapping("/GetMember/{var1}/{var2}")
//	public TestVO GetMember(@RequestBody TestVO vo, 
//			@PathVariable("var1") String var1, 
//			@PathVariable("var2") String var2
//			) {
//		
//		if(var1.equals(var2)) {
//			return new TestVO(40, var1, "홍길동");
//		} else {
//			return null;
//		}
//	}
	
	
	
	
}
