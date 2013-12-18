package com.android.search.tool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.android.search.common.Location;
import com.android.search.common.SearchTool;
import com.android.search.constant.GlobalConstant;
import com.android.search.obj.SearchQueryObject;
import com.android.search.obj.SearchResult;
import com.android.search.rp.UserIndex;

public class SearcPoiInterface {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Location location = new Location();
		location.setLat(39.937732);
		location.setLng(116.422666);
//		List<UserIndex> lists = SearchInterfaceForRp("宾馆", location, "500", 100);
//		String result = JSON.toJSONString(lists);
//		System.out.println("---");
//		System.out.println(result);
		
		SearchQueryObject obj = SearchInterfaceForOriginalPoiData("宾馆", location, "500", 100);
		String result = JSON.toJSONString(obj);
		System.out.println(result);
	}
	
//	public static void SearchInterfaceForOutter(String tag , Location location, String radius, int recordMax) {
//		SearchQueryObject obj = SearchTool.getAllSearchResult(tag, tag, "", "distance", "1", "", "", "", "20", "131", location, radius,GlobalConstant.ak, "2", recordMax);
//		//System.out.println(obj.toString());
//		obj.setTotal(obj.getResults().size());
//		String result = JSON.toJSONString(obj);
//		System.out.println("---");
//		System.out.println(result);
//	}
	/*
	 * 这个结构给用于给胜鹏的一个计算权值的算法提供数据
	 */
	public static List<UserIndex> SearchInterfaceForRp(String tag , Location location, String radius, int recordMax) {
		SearchQueryObject obj = SearchTool.getAllSearchResult(tag, tag, "", "distance", "1", "", "", "", "20", "131", location, radius,GlobalConstant.ak, "2", recordMax);
		List<SearchResult> results  = obj.getResults();
		List<UserIndex> poiList = new ArrayList<UserIndex>();
		for(SearchResult result:results) {
			if(result.getDetail_info().getTag()==null) {
				continue;
			}
			UserIndex userIndex = new UserIndex();
			userIndex.setName(result.getName());
			userIndex.setTagList(Arrays.asList(result.getDetail_info().getTag().split(",")));
			userIndex.setUid(result.getUid());
			poiList.add(userIndex);
		}
		return poiList;
	}
	
	public static SearchQueryObject SearchInterfaceForOriginalPoiData(String tag , Location location, String radius, int recordMax) {
		SearchQueryObject obj = SearchTool.getAllSearchResult(tag, tag, "", "distance", "1", "", "", "", "20", "131", location, radius,GlobalConstant.ak, "2", recordMax);
		return obj;
	}
}
