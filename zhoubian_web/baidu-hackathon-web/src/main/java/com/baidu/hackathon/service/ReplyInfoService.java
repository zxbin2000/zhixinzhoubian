package com.baidu.hackathon.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.baidu.hackathon.dao.ReplyInfoDao;
import com.baidu.hackathon.domain.ReplyInfo;

public class ReplyInfoService {
	@Autowired
	private ReplyInfoDao replyInfoDao;
	
	public Integer addItem(ReplyInfo item) {
		try {
			replyInfoDao.addItem(item);
			return item.getReplyId();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public ReplyInfo selectOne(ReplyInfo item) {
		try {
			return replyInfoDao.selectOne(item);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
