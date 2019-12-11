package com.team404.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // getter, setter
@AllArgsConstructor // 모든변수 초기화 생성
@NoArgsConstructor // 기본생성자
public class TestVO {
	
	private int age;
	private String name;
	private String id;
	
}
