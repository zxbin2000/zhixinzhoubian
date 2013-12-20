package com.baidu.hackathon.dao;

import com.baidu.hackathon.domain.ReplyInfo;

public interface ReplyInfoDao {
	
	public Integer addItem(ReplyInfo item) throws Exception;
	
	public ReplyInfo selectOne(ReplyInfo item) throws Exception;
	
}