package com.team404.freereply.mapper;


import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.team404.command.ReplyVO;
import com.team404.util.Criteria;

public interface FreeReplyMapper {
	public int regist(ReplyVO vo);
	// public ArrayList<ReplyVO> getList(int bno);
	public ArrayList<ReplyVO> getList(@Param("cri") Criteria cri, @Param("bno") int bno);
	public int getTotal(int bno);
	
	public int pwCheck(ReplyVO vo);
	public int delete(ReplyVO vo);
	public int update(ReplyVO vo);
	
}
