package com.baidu.hackathon.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.baidu.hackathon.domain.SearchForUI;
import com.baidu.hackathon.domain.SearchForUIData;
import com.baidu.hackathon.domain.SearchQueryObject;
import com.baidu.hackathon.domain.SearchResult;
import com.baidu.hackathon.rp.UserIndex;
import com.baidu.hackathon.search.common.PoiSearchTool;
import com.baidu.hackathon.search.constant.GlobalConstant;

public class SearchPoiService {

	/**
	 * @param args
	 */
/*	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Location location = new Location();
		location.setLat(39.937732);
		location.setLng(116.422666);
//		SearchQueryObject obj = SearchInterfaceForOriginalPoiData("����", location, "500", 100);
		
		SearchForUI obj = GetPoiResultForUI("����", "39.937732", "116.422666", "5000", 50);
		String result = JSON.toJSONString(obj);
		System.out.println(result);
	}*/
	
//	public static void SearchInterfaceForOutter(String tag , Location location, String radius, int recordMax) {
//		SearchQueryObject obj = SearchTool.getAllSearchResult(tag, tag, "", "distance", "1", "", "", "", "20", "131", location, radius,GlobalConstant.ak, "2", recordMax);
//		//System.out.println(obj.toString());
//		obj.setTotal(obj.getResults().size());
//		String result = JSON.toJSONString(obj);
//		System.out.println("---");
//		System.out.println(result);
//	}
	/*
	 * ����ṹ�����ڸ�ʤ����һ������Ȩֵ���㷨�ṩ���
	 */
	public static List<UserIndex> SearchInterfaceForRp(String tag , String latitude, String longitude, String radius, int recordMax) {
		SearchQueryObject obj = PoiSearchTool.getAllPoiSearchResult(tag, tag, "", "distance", "1", "", "", "", "20", "131", latitude, longitude, radius,GlobalConstant.SearchAK, "2", recordMax);
		List<SearchResult> results  = obj.getResults();
		List<UserIndex> poiList = new ArrayList<UserIndex>();
		for(SearchResult result:results) {
			if(result.getDetail_info().getTag()==null) {
				result.getDetail_info().setTag("");
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
	
	public static SearchQueryObject SearchInterfaceForOriginalPoiData(String tag , String latitude, String longitude, String radius, int recordMax) {
		SearchQueryObject obj = PoiSearchTool.getAllPoiSearchResult(tag, tag, "", "distance", "1", "", "", "", "20", "131", latitude, longitude, radius,GlobalConstant.SearchAK, "2", recordMax);
		return obj;
	}
	
	public static SearchForUI GetPoiResultForUI(String tag , String latitude, String longitude, String radius, int recordMax) {
		SearchQueryObject obj = PoiSearchTool.getAllPoiSearchResult(tag, tag, "", "distance", "1", "", "", "", "20", "131", latitude, longitude, radius,GlobalConstant.SearchAK, "2", recordMax);
		SearchForUI searchForUi = new SearchForUI();
		searchForUi.setCode(obj.getStatus()!=0?1:0);
		searchForUi.setMessage(obj.getMessage());
		List<SearchForUIData> data = new ArrayList<SearchForUIData>();
		for(SearchResult result : obj.getResults()) {
			SearchForUIData uiData = new SearchForUIData();
			uiData.setPoiId(result.getUid());
			uiData.setNickName(result.getName());
			uiData.setMessageType(2);
			//uiData.setPhotoPath(PoiHtmlParse.getDetailImgUrl(result.getDetail_info().getDetail_url()));
			uiData.setPhotoPath("");
			uiData.setPoiTag(result.getDetail_info().getTag());
			uiData.setLngX(result.getLocation().getLng());
			uiData.setLatY(result.getLocation().getLat());
			uiData.setDistance(result.getDetail_info().getDistance());
			//feedbackId
			//Message
			//beginTime
			//endTime
			data.add(uiData);
		}
		searchForUi.setData(data);
		return searchForUi;
	}
}
