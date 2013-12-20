package com.baidu.hackathon.dao;

import com.baidu.hackathon.domain.SubscibeInfo;

public interface SubscibeDao {
	
	public SubscibeInfo selectOne(SubscibeInfo item) throws Exception;
	
	public Integer addItem(SubscibeInfo item) throws Exception;
}
