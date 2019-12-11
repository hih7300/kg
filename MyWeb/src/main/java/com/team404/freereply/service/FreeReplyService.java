package com.team404.freereply.service;

import com.team404.command.ReplyVO;
import com.team404.util.Criteria;
import com.team404.util.ReplyPageVO;

public interface FreeReplyService {

	public int regist(ReplyVO vo);

//	public ArrayList<ReplyVO> getList(int bno);

	public int pwCheck(ReplyVO vo);

	public int delete(ReplyVO vo);

	public int update(ReplyVO vo);
	
	public ReplyPageVO getList(Criteria cri, int bno);
	
	
}
