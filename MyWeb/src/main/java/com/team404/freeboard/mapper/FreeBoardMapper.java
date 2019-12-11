package com.team404.freeboard.mapper;

import java.util.ArrayList;

import com.team404.command.FreeBoardVO;
import com.team404.util.Criteria;

public interface FreeBoardMapper {
	public void regist(FreeBoardVO vo); // 등록
	
	//일반 페이징
//	public ArrayList<FreeBoardVO> getList(Criteria cri); 
//	public int getTotal();
	
	// 검색 페이징
	public ArrayList<FreeBoardVO> getList(Criteria cri); //일반
	public int getTotal(Criteria cri);
	
	public FreeBoardVO content(int bno);
	public boolean update(FreeBoardVO vo);
	public boolean delete(int bno);
	
	
}