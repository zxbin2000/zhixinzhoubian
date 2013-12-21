package com.baidu.hackathon.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.baidu.hackathon.domain.PoiCreateStatusObj;
import com.baidu.hackathon.domain.SearchForUI;
import com.baidu.hackathon.domain.SearchForUIData;
import com.baidu.hackathon.rp.Rp;
import com.baidu.hackathon.rp.UserIndex;
import com.baidu.hackathon.search.common.CloudTool;

public class MapSearchService {
	
	@Autowired
	private SearchCloudService searchCloudService;
	@Autowired
	private SearchPoiService searchPoiService;
	
	//lbs poi 50
		//lbs cloud poi 50
		public static final int cloudmax = 50;
		public static final int poimax = 50;
		public static final int rpcloudmax = 3;
		
		//搜索附近信息，data字段数据不进行排序	
		public SearchForUI getNearByInfo(String latitude, String longitude, String scope, String tags) {
			SearchForUI cloudObj = new SearchForUI();
			for(String tag : tags.split(" ")) {
				SearchForUI tmpcloudObj = searchCloudService.CloudSearchForUi(longitude, latitude, scope, tag, cloudmax/tags.split(" ").length + 1);
				cloudObj.getData().addAll(tmpcloudObj.getData());
			}
			SearchForUI searchObj = new SearchForUI();
			for(String tag : tags.split(" ")) {
				SearchForUI tmpsearchObj = searchPoiService.GetPoiResultForUI(tag, latitude, longitude, scope, poimax/tags.split(" ").length + 1);
				searchObj.getData().addAll(tmpsearchObj.getData());
			}
			cloudObj.getData().addAll(searchObj.getData());
			return cloudObj;
		}
		
		//获取推荐信息
		public SearchForUI getNearByRpInfo(String latitude, String longitude, String scope, String tags) {
			SearchForUI cloudObj = searchCloudService.CloudSearchForUi(longitude, latitude, scope, tags, cloudmax);
			return cloudObj;
		}
		
		//获取个人发布的需求或服务
		public SearchForUI getPersonalInfo(String userId, String messageType) {
			SearchForUI obj = searchCloudService.CloudPersonalSearchForUi(userId, messageType);
			return obj;
		}
		
		//云搜索详细信息
		public SearchForUI getDetailInfo(String poidId) {
			SearchForUI obj = searchCloudService.CloudDetailSearchForUi(poidId);
			return obj;
		}
		
		//搜索附近信息，data字段数据进行排序
		public SearchForUI getLbsCloudRpInfo( String latitude, String longitude, String scope, String tags) {
			SearchForUI obj = getNearByInfo(latitude, longitude, scope, tags);
			HashMap<String, SearchForUIData> rpResultMap = new HashMap<String, SearchForUIData>();
			List<SearchForUIData> rpdata = new ArrayList<SearchForUIData>();
			UserIndex user = new UserIndex();
			user.setTagList(Arrays.asList(tags.split(" ")));
			List<UserIndex> users = new ArrayList<UserIndex>();
			for(SearchForUIData item: obj.getData()) {
				rpResultMap.put(item.getPoiId(), item);
				UserIndex bakUser = new UserIndex();
				bakUser.setUid(item.getPoiId());
				bakUser.setTagList(Arrays.asList((item.getPoiTag().split(" "))));
				users.add(bakUser);
			}
			List<Map.Entry<String, Double>> relatedValue = Rp.correlationCalculation(user, users);
			for(int i=0; i<relatedValue.size(); i++) {
				rpdata.add(rpResultMap.get(relatedValue.get(i).getKey()));
			}
			obj.setData(rpdata);
			return obj;
		}
		
		//创建lbs云 poi数据
		public PoiCreateStatusObj createPoi(String title, String tags,
				double latitude, double longitude, long userId, String message,
				String messageType, String photoPath, int scope, long beginTime,
				long endTime) {
			PoiCreateStatusObj obj = CloudTool.getPoiCreateStatusObj(title, tags, latitude, longitude, userId, message, messageType, photoPath, scope, beginTime, endTime);
			return obj;
		}
		
		public SearchForUI getCloudRpInfo( String latitude, String longitude, String scope, String tags) {
			SearchForUI obj = getNearByRpInfo(latitude, longitude, scope, tags);
			HashMap<String, SearchForUIData> rpResultMap = new HashMap<String, SearchForUIData>();
			List<SearchForUIData> rpdata = new ArrayList<SearchForUIData>();
			UserIndex user = new UserIndex();
			user.setTagList(Arrays.asList(tags.split(" ")));
			List<UserIndex> users = new ArrayList<UserIndex>();
			for(SearchForUIData item: obj.getData()) {
				rpResultMap.put(item.getPoiId(), item);
				UserIndex bakUser = new UserIndex();
				bakUser.setUid(item.getPoiId());
				bakUser.setTagList(Arrays.asList((item.getPoiTag().split(" "))));
				users.add(bakUser);
			}
			List<Map.Entry<String, Double>> relatedValue = Rp.correlationCalculation(user, users);
			int rpCount = (relatedValue.size()<rpcloudmax)?relatedValue.size():rpcloudmax;
			for(int i=0; i<rpCount; i++) {
				rpdata.add(rpResultMap.get(relatedValue.get(i).getKey()));
			}
			obj.setData(rpdata);
			return obj;
		}

}