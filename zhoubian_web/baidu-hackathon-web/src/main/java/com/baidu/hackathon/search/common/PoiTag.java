package com.baidu.hackathon.search.common;

import java.util.HashMap;
import java.util.Map;

public class PoiTag {
	
	public static final int COMMON = 0; 	//一般的，或者无法分辨类型的
	
	public static final int FOOD = 1;		//美食
	public static final int HOTEL = 2;		//宾馆
	public static final int KTV = 3;		//KTV
	public static final int SHOPPING = 4;	//购物
	public static final int RENTING = 5;	//房源
	public static final int GAS_STATION = 6;//加油站
	public static final int MOVIE = 7;		//电影
	public static final int SECOND_HAND = 8;//二手
	public static final int EMERENCY = 9;	//紧急
	
	public static Map<Integer, String> TagMap = new HashMap<Integer, String>();
	
	public static String getPoiValue(int key) {
		TagMap.put(COMMON, "无法分辨");
		TagMap.put(FOOD, "美食");
		TagMap.put(HOTEL, "宾馆");
		TagMap.put(KTV, "KTV");
		TagMap.put(SHOPPING, "购物");
		TagMap.put(RENTING, "房源");
		TagMap.put(GAS_STATION, "加油站");
		TagMap.put(MOVIE, "电影");
		TagMap.put(SECOND_HAND, "二手");
		TagMap.put(EMERENCY, "紧急");
		return TagMap.get(key);
	} 
	
}