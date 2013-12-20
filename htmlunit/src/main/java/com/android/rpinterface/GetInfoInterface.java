package com.android.rpinterface;

import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import rp2.UserIndex;

import com.alibaba.fastjson.JSON;
import com.android.cloudsearch.common.CloudTool;
import com.android.cloudsearch.constant.MessageType;
import com.android.cloudsearch.obj.CloudItemObj;
import com.android.cloudsearch.obj.CloudSearchObj;
import com.android.cloudsearch.obj.PoiCreateStatusObj;
import com.android.cloudsearch.tool.SearchCloudInterface;
import com.android.search.obj.SearchForUI;
import com.android.search.obj.SearchForUIData;
import com.android.search.obj.SearchQueryObject;
import com.android.search.tool.SearcPoiInterface;

public class GetInfoInterface {
	//lbs poi 50
	//lbs cloud poi 50
	public static final int cloudmax = 50;
	public static final int poimax = 50;
	public static SearchForUI getNearByInfo(String latitude, String longitude, String scope, String tags) {
		SearchForUI cloudObj = SearchCloudInterface.CloudSearchForUi(longitude, latitude, scope, tags, cloudmax);
		SearchForUI searchObj = SearcPoiInterface.GetPoiResultForUI(tags, latitude, longitude, scope, poimax);
		cloudObj.getData().addAll(searchObj.getData());
		return cloudObj;
	}
	
	public static SearchForUI getPersonalInfo(String userId, String messageType) {
		SearchForUI obj = SearchCloudInterface.CloudPersonalSearchForUi(userId, messageType);
		return obj;
	}
	
	public static SearchForUI getDetailInfo(String poidId) {
		SearchForUI obj = SearchCloudInterface.CloudDetailSearchForUi(poidId);
		return obj;
	}
	
	public static SearchForUI getLbsCloudRpInfo( String latitude, String longitude, String scope, String tags) {
		SearchForUI obj = GetInfoInterface.getNearByInfo(latitude, longitude, scope, tags);
		HashMap<String, SearchForUIData> rpResultMap = new HashMap<String, SearchForUIData>();
		List<SearchForUIData> rpdata = new ArrayList<SearchForUIData>();
		UserIndex user = new UserIndex();
		user.setTagList(Arrays.asList(tags.split(",")));
		List<UserIndex> users = new ArrayList<UserIndex>();
		for(SearchForUIData item: obj.getData()) {
			rpResultMap.put(item.getPoiId(), item);
			UserIndex bakUser = new UserIndex();
			bakUser.setUid(item.getPoiId());
			bakUser.setTagList(Arrays.asList((item.getPoiTag().split(","))));
			users.add(bakUser);
		}
		List<Map.Entry<String, Double>> relatedValue = rp2.Rp.correlationCalculation(user, users);
		for(int i=0; i<relatedValue.size(); i++) {
			rpdata.add(rpResultMap.get(relatedValue.get(i).getKey()));
		}
		obj.setData(rpdata);
		return obj;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//SearchForUI xx = GetInfoInterface.getNearByInfo("39.914412" ,"116.401837", "99999", "KTV");
		SearchForUI xx = GetInfoInterface.getLbsCloudRpInfo("39.914412" ,"116.401837", "99999", "购物");
		System.out.println(JSON.toJSONString(xx));
		//CloudTool.createCloudPoiJson("title1", "KTV 歌唱", 39.915178, 116.405126, 1L, "我想在这里唱歌", MessageType.request, "http://img10.360buyimg.com/tuangou/g15/M03/1D/13/rBEhWlKy4cUIAAAAAAEvurdyRGMAAG_ZgHjOHwAAS_S183.jpg", 1000, 1387531210728L, 1387617610728L);
		//System.out.println(new Date().getTime());
		//PoiCreateStatusObj statusObj = CloudTool.getPoiCreateStatusObj("title1", "tags1 tags2 tags3", 0.0, 0.0, 18612281999L, "message", MessageType.request, "http://map.baidu.com/maps/services/thumbnails?width=314&height=220&quality=100&src=http%3A%2F%2Fhiphotos.baidu.com%2Flbsugc%2Fpic%2Fitem%2Ff636afc379310a550ed7d68fb74543a9832610dd.jpg", 1000, 99999999, 9999999);
//		PoiCreateStatusObj statusObj = CloudTool.getPoiCreateStatusObj("title2", "食品 KTV", 39.914412 ,116.401837, 18612281999L, "message", MessageType.request, "http://map.baidu.com/maps/services/thumbnails?width=314&height=220&quality=100&src=http%3A%2F%2Fhiphotos.baidu.com%2Flbsugc%2Fpic%2Fitem%2Ff636afc379310a550ed7d68fb74543a9832610dd.jpg", 1000, 99999999, 9999999);
//		System.out.println(statusObj.toString());
		return;
	}

}
