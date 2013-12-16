package com.baidu.hackathon.web;

import com.alibaba.fastjson.JSON;

public class TestDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Object obj =  JSON.parse("{'name':'123','password':'456'}");
		System.out.print(obj.toString());
	}


}
