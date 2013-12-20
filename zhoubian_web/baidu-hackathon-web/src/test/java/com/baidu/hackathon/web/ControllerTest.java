package com.baidu.hackathon.web;

import com.baidu.hackathon.utils.HttpUtils;

public class ControllerTest {

	public static void main(String[] args) {
		ReplyInfoTest();
	}
	
	private static void ReplyInfoTest() {
		String params = "{'userId':1, 'poiId':123456, 'message':'testMessage'}";
		String rs = HttpUtils.doPost("http://www.zhoubangbang.com:8090/reply", params);
	}
	
	private static void SubscibeTest() {
		String params = "{'userId':1,'tagList':[1,2,3,4],'distance':1000,'frequency':100}";
		String rs = HttpUtils.doPost("http://www.zhoubangbang.com:8090/subscibe", params);
		System.out.println(rs);
	}
	
	private static void SearchTest() {
		String params = "{'userId':1111,'lngX':121.00,'latY':90,'distance':100,'tagList':'餐馆'}";
		String rs = HttpUtils.doPost("http://www.zhoubangbang.com:8090/poiSearch", params);
		System.out.print(rs);
	}
	
	private static void UserInfoTest() {
		String params = "{'userId':0,'deviceId':'1234567890','nickName':'测试用户', 'telephone':'18210000'}";
		String rs = HttpUtils.doPost("http://www.zhoubangbang.com:8090/submit", params);
		System.out.print(rs);
	}
	
	private static void PostMessageTest() {
		String params = "{'userId':1,'message':'测试需求','messageType':1,'photoPath':['http://zhoubangbang.duapp.com/test'],'poiTag':1,'lngX':16.401837,'latY':39.914412,'distance':100,'beginTime':'2013-12-21 02:19:00','endTime':'2013-12-21 06:19:00'}";
		String rs = HttpUtils.doPost("http://www.zhoubangbang.com:8090/postMessage", params);
		System.out.print(rs);
	}

}
