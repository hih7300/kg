package com.team404.command;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SnsBoardVO {

	private int bno;
	private String writer; //글쓴이
	private String uploadPath; //fileLoca를 포함한 업로드 경로
	private String fileLoca; // 날짜폴더 경로
	private String fileName; // 변경해서 저장할이름(중복의 경우때문에)
	private String fileRealName; // 원본이름
	private String content; //내용
	private Timestamp regdate; //등록일
	
	
}
