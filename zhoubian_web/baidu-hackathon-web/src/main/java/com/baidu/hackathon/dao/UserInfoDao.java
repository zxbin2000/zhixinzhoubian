package com.baidu.hackathon.dao;

import com.baidu.hackathon.domain.UserInfo;

/**
 * 用户信息DAO层接口
 */
public interface UserInfoDao {
	
	public UserInfo selectOne(UserInfo user) throws Exception;
	
	public Integer addItem(UserInfo user) throws Exception;
	
}
