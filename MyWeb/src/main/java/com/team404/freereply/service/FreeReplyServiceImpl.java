package com.team404.freereply.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team404.command.ReplyVO;
import com.team404.freereply.mapper.FreeReplyMapper;
import com.team404.util.Criteria;
import com.team404.util.ReplyPageVO;

@Service("freereplyService")
public class FreeReplyServiceImpl implements FreeReplyService {
	
	@Autowired
	private FreeReplyMapper freeReplyMapper;
	
	public int regist(ReplyVO vo) {		
		return freeReplyMapper.regist(vo);
	}

	@Override
	public ReplyPageVO getList(Criteria cri, int bno) {
		// 1. 페이징된 List를 구해옴.
		// 2. 전체게시글 수를 구함(각각 다른 2개의 데이터를 마이바티스 전송할때 @Param 어노테이션 사용)
		// 3. list와 replyCount를 VO에 저장 
		ArrayList<ReplyVO> list = freeReplyMapper.getList(cri, bno);
		int replyCount = freeReplyMapper.getTotal(bno);

		return new ReplyPageVO(list, replyCount);
	}

	@Override
	public int pwCheck(ReplyVO vo) {
		
		return freeReplyMapper.pwCheck(vo);
	}

	@Override
	public int delete(ReplyVO vo) {
		return freeReplyMapper.delete(vo);
	}

	@Override
	public int update(ReplyVO vo) {
		
		return freeReplyMapper.update(vo);
	}
	
	
}
