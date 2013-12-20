package com.baidu.hackathon.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.baidu.hackathon.dao.UserInfoDao;
import com.baidu.hackathon.domain.UserInfo;

public class UserInfoService {
	private Logger logger = Logger.getLogger(UserInfoService.class);
	@Autowired
	private UserInfoDao userInfoDao;
	
	public UserInfo selectOne(Integer userId) {
		UserInfo item = new UserInfo();
		item.setUserId(userId);
		try {
			item = userInfoDao.selectOne(item);
		} catch(Exception e) {
			logger.error("userInfo select error:", e);
		}
		return item;
	}
	
	public Integer addItem(String deviceId, String nickName, String telephone) {
		UserInfo item =  new UserInfo();
		item.setDeviceId(deviceId);
		item.setNickName(nickName);
		item.setTelephone(telephone);
		try {
			userInfoDao.addItem(item);
			return item.getUserId();
		} catch (Exception e) {
			logger.error("userInfo insert error:", e);
		}
		return 0;
	}
}
