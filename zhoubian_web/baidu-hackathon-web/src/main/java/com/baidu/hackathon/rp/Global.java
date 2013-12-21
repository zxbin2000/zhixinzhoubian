package com.baidu.hackathon.rp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
public class Global {
	public static HashMap<String,List<String>> TagArray= new HashMap<String,List<String>>();

	static {
		String foodTagsStr = "美食,中餐,西餐,烧烤,自助,火锅,小吃,甜点";
		TagArray.put("美食",Arrays.asList(foodTagsStr.split(",")));
		String hotelTagsStr = "酒店,旅店,旅馆,宾馆,招待所";
		TagArray.put("宾馆",Arrays.asList(hotelTagsStr.split(",")));
		String shopTagsStr = "购物,商场,,沃尔玛,超市,百货,国美,苏宁";
		TagArray.put("购物",Arrays.asList(shopTagsStr.split(",")));
		String KTVTagsStr = "KTV,钱柜,夜总会,歌舞厅,迪厅";
		TagArray.put("KTV",Arrays.asList(KTVTagsStr.split(",")));
		String houseTagsStr = "房源,地产";
		TagArray.put("房源",Arrays.asList(houseTagsStr.split(",")));
		String petrolTagsStr = "加油站";
		TagArray.put("加油站",Arrays.asList(petrolTagsStr.split(",")));
		String movieTagsStr = "电影,影院";
		TagArray.put("电影", Arrays.asList(movieTagsStr.split(",")));
		String secondHandTagsStr = "二手";
		TagArray.put("二手", Arrays.asList(secondHandTagsStr.split(",")));
		String urgentTagsStr = "紧急";
		TagArray.put("紧急", Arrays.asList(urgentTagsStr.split(",")));
	}
	
}
