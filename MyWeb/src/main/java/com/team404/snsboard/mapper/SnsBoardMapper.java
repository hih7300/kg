package com.team404.snsboard.mapper;

import java.util.ArrayList;

import com.team404.command.SnsBoardVO;

public interface SnsBoardMapper {
	public boolean insert(SnsBoardVO vo);
	public ArrayList<SnsBoardVO> getList();
	
}
