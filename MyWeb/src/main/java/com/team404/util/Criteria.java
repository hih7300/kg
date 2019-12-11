package com.team404.util;

import lombok.Data;

@Data // getter, setter 자동 생성
public class Criteria {

	private int pageNum; //현재 조회하는 페이지 번호
	private int amount; // 한 페이지에서 몇개의 데이터를 보여줄 것인가
	
	private String searchType; // 검색 타입
	private String searchName; // 검색이름
	
	public Criteria() { // 첫 페이지 진입시 생성될 클래스
		this(1, 10);
	}
	
	public Criteria(int pageNum, int amount) { //페이지 번호를 클릭시 생성될 생성자
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	
	
	
	
	
}
