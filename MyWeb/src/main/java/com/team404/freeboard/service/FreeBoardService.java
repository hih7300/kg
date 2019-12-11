package com.team404.freeboard.service;

import java.util.ArrayList;

import com.team404.command.FreeBoardVO;
import com.team404.util.Criteria;

public interface FreeBoardService {
	
	public void regist(FreeBoardVO vo); // 등록
	//public ArrayList<FreeBoardVO> getList(Criteria cri); // 목록
	public ArrayList<FreeBoardVO> getList(Criteria cri);
	public int getTotal(Criteria cri);
	
	public FreeBoardVO content(int bno);
	
	public boolean update(FreeBoardVO vo);
	public boolean delete(int bno);
	//public int getTotal();
}
