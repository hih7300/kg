package com.team404.user.service;

import com.team404.command.UserVO;

public interface UserService {
	
	public int idConfirm(UserVO vo);
	public int join(UserVO vo);
	public int login(UserVO vo);
	public UserVO getInfo(String userId);
	public int update(UserVO vo);
	
}
