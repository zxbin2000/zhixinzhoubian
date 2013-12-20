package com.baidu.hackathon.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.baidu.hackathon.dao.SubscibeDao;
import com.baidu.hackathon.domain.SubscibeInfo;

public class SubscibeService {
	private static final Logger logger = Logger.getLogger(SubscibeService.class);
	@Autowired
	private SubscibeDao subscibeDao;
	
	public Integer addItem(SubscibeInfo item) {
		try {
			subscibeDao.addItem(item);
			return item.getSubscibeId();
		} catch(Exception e) {
			logger.error("subscibe insert error:", e);
		}
		return 0;
	}
	
	public SubscibeInfo selectOne(Integer userId) {
		SubscibeInfo item = new SubscibeInfo();
		item.setUserId(userId);
		try {
			item = subscibeDao.selectOne(item);
			return item;
		} catch(Exception e) {
			logger.error("subscibe select error:", e);
		}
		return null;
	}
	
}
