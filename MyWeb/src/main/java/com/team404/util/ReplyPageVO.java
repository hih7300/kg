package com.team404.util;

import java.util.ArrayList;

import com.team404.command.ReplyVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyPageVO {

	private ArrayList<ReplyVO> list;
	private int replyCount;
	
	
	
}
