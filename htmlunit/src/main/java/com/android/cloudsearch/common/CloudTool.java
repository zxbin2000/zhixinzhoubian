package com.android.cloudsearch.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.android.cloudsearch.constant.GlobalConstant;
import com.android.cloudsearch.constant.RequestUrl;
import com.android.cloudsearch.constant.SearchType;
import com.android.cloudsearch.obj.CloudSearchObj;
import com.android.cloudsearch.obj.PoiCreateStatusObj;
import com.android.search.common.PoiHttpUtils;

public class CloudTool {

	public static String createCloudPoiJson(String title, String tags,
			double latitude, double longitude, long userId, String message,
			String messageType, String photoPath, int scope, long beginTime,
			long endTime) {
		InputStream fis = CloudHttpUtils.getCloudCreatePoiJsonStream(title,
				tags, latitude, longitude, userId, message, messageType,
				photoPath, scope, beginTime, endTime);
		String result = "";
		byte data[] = new byte[1024];
		int len = 0;
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		if (fis != null) {
			try {
				while ((len = fis.read(data, 0, 1024)) != -1) {
					outputStream.write(data, 0, len);
				}
				result = new String(outputStream.toByteArray(), "utf-8");
			} catch (IOException e) {
				e.printStackTrace();
				return "";
			}
		}
		return result;
	}

	public static PoiCreateStatusObj getPoiCreateStatusObj(String title,
			String tags, double latitude, double longitude, long userId,
			String message, String messageType, String photoPath, int scope,
			long beginTime, long endTime) {
		String json = CloudTool.createCloudPoiJson(title, tags, latitude,
				longitude, userId, message, messageType, photoPath, scope,
				beginTime, endTime);
		PoiCreateStatusObj obj = JSON.parseObject(json,
				new TypeReference<PoiCreateStatusObj>() {
				});
		return obj;
	}

	// public static CloudSearchObj cloudInterfaceForOriginalPoiData() {
	public static CloudSearchObj cloudInterfaceForOriginalPoiData(
			String latitude, String longitude, String radius, String userId,
			String messageType, String tags, String now, String page_size,
			String page_index, String region, int searchType, String poiId) {
		// String userId, String messageType, String tags, String beginTime,
		// String endTime, String now
		// http://api.map.baidu.com/geosearch/v2/nearby?ak=jDMxT8I1tedt2ZCrxzt0WDqZ&geotable_id=45999&q=&location=108.638264,31.960853&
		// radius=99&filter=messageType:1,1|userId=1,11111111112
		// ak geotable_id q latitude longitude radius filter-messageType-endTime
		// userId userId
		String result = "";
		CloudSearchObj qb = null;
		try {
			StringBuffer buffer = new StringBuffer();
			switch (searchType) {
			case SearchType.nearbySearch:
				buffer.append(RequestUrl.poi_search_nearby).append("?");
				CloudHttpUtils.addParamToBuffer(buffer, "ak", GlobalConstant.ak,
						true);
				CloudHttpUtils.addParamToBuffer(buffer, "geotable_id",
						GlobalConstant.geotable_id, false);
				CloudHttpUtils.addParamToBuffer(buffer, "location", longitude
						+ "," + latitude, false);
				CloudHttpUtils
						.addParamToBuffer(buffer, "radius", radius, false);
				CloudHttpUtils.addParamToBuffer(buffer, "page_size", page_size,
						false);
				CloudHttpUtils.addParamToBuffer(buffer, "page_index", page_index,
						false);
				CloudHttpUtils.addParamToBuffer(buffer, "tags", tags, false);
				CloudHttpUtils.addParamToBuffer(buffer, "filter", "", false);
				/*if (now != null && now != "" && (userId == null || userId == ""))
					buffer.append("endTime:").append(now).append(",")
							.append(GlobalConstant.timeMax).append("|");*/
				break;
			case SearchType.personalSearch:
				buffer.append(RequestUrl.poi_search_region).append("?");
				CloudHttpUtils.addParamToBuffer(buffer, "ak", GlobalConstant.ak,
						true);
				CloudHttpUtils.addParamToBuffer(buffer, "geotable_id",
						GlobalConstant.geotable_id, false);
				CloudHttpUtils.addParamToBuffer(buffer, "q",
						"", false);
				CloudHttpUtils.addParamToBuffer(buffer, "page_size", page_size,
						false);
				CloudHttpUtils.addParamToBuffer(buffer, "page_index", page_index,
						false);
				CloudHttpUtils.addParamToBuffer(buffer, "filter", "", false);
				if (userId != null && userId != "")
					buffer.append("userId:").append(userId).append(",")
							.append(userId).append("|");
				if (messageType != null && messageType != "")
					buffer.append("messageType:").append(messageType).append(",")
							.append(messageType).append("|");
				break;
			case SearchType.regionSearch:
				buffer.append(RequestUrl.poi_search_region).append("?");
				CloudHttpUtils.addParamToBuffer(buffer, "ak", GlobalConstant.ak,
						true);
				CloudHttpUtils.addParamToBuffer(buffer, "geotable_id",
						GlobalConstant.geotable_id, false);
				CloudHttpUtils.addParamToBuffer(buffer, "q",
						"", false);
				CloudHttpUtils
				.addParamToBuffer(buffer, "region", region, false);
				CloudHttpUtils.addParamToBuffer(buffer, "page_size", page_size,
						false);
				CloudHttpUtils.addParamToBuffer(buffer, "page_index", page_index,
						false);
				CloudHttpUtils.addParamToBuffer(buffer, "tag", tags, false);
				CloudHttpUtils.addParamToBuffer(buffer, "filter", "", false);
				if (now != null && now != "" && (userId == null || userId == ""))
					buffer.append("endTime:").append(now).append(",")
							.append(GlobalConstant.timeMax).append("|");
				break;
			case SearchType.detailSearch:
				buffer.append(RequestUrl.poi_search_detail).append("?");
				CloudHttpUtils.addParamToBuffer(buffer, "ak", GlobalConstant.ak,
						true);
				CloudHttpUtils.addParamToBuffer(buffer, "geotable_id",
						GlobalConstant.geotable_id, false);
				CloudHttpUtils.addParamToBuffer(buffer, "id",
						poiId, false);
			break;
			default:
				System.err.println("error of searchtype");
				return new CloudSearchObj();
			}
			System.out.println(buffer.toString());
			InputStream fis = PoiHttpUtils.getJsonStream(buffer.toString());
			byte data[] = new byte[1024];
			int len = 0;
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			if (fis != null) {
				try {
					while ((len = fis.read(data, 0, 1024)) != -1) {
						outputStream.write(data, 0, len);
					}
					result = new String(outputStream.toByteArray(), "utf-8");
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			}
			qb = JSON.parseObject(result, new TypeReference<CloudSearchObj>() {
			});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		if (qb != null)
			qb.setTotal(qb.getContents().size());
		return qb;
	}

	public static CloudSearchObj getAllCloudSearchObjSearchResult(
			String latitude, String longitude, String radius, String userId,
			String messageType, String tags, String now, String page_size,
			int recordMax, String region, int searchType) {
		CloudSearchObj obj0 = CloudTool.cloudInterfaceForOriginalPoiData(
				latitude, longitude, radius, userId, messageType, tags, now,
				page_size, "0", region, searchType, "");
		if (obj0 != null && obj0.status == 0) {
			int page_from = 1;
			recordMax = obj0.total > recordMax ? recordMax : obj0.total;
			int page_to = recordMax
					/ Integer.parseInt(page_size)
					+ (((recordMax > Integer.parseInt(page_size)) && (recordMax
							% Integer.parseInt(page_size) != 0)) ? 1 : 0);
			CloudSearchObj tmpQueryObject;
			for (int i = page_from; i < page_to; i++) {
				if ((Integer.parseInt(page_size) * (i + 1)) - recordMax > 0) {
					int tmppagesize = Integer.parseInt(page_size);
					tmpQueryObject = CloudTool
							.cloudInterfaceForOriginalPoiData(latitude,
									longitude, radius, userId, messageType,
									tags, now, tmppagesize + "", i + "",
									region, searchType, "");
					if (tmpQueryObject != null && tmpQueryObject.status == 0) {
						if (tmpQueryObject.getContents() != null
								&& tmpQueryObject.getContents().size() > 0) {
							obj0.getContents().addAll(
									tmpQueryObject.getContents());
						}
					}
				} else {
					int tmppagesize = Integer.parseInt(page_size) * (i + 1)
							- recordMax;
					tmpQueryObject = CloudTool
							.cloudInterfaceForOriginalPoiData(latitude,
									longitude, radius, userId, messageType,
									tags, now, tmppagesize + "", i + "",
									region, searchType, "");
					if (tmpQueryObject != null && tmpQueryObject.status == 0) {
						if (tmpQueryObject.getContents() != null
								&& tmpQueryObject.getContents().size() > 0) {
							obj0.getContents().addAll(
									tmpQueryObject.getContents());
						}
					}
					break;
				}
			}
		}
		if (obj0 != null)
			obj0.setTotal(obj0.getContents().size());
		return obj0;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CloudSearchObj obj = CloudTool.getAllCloudSearchObjSearchResult("0",
				"0", "100", "", "1", "tags1", "1", "50", 50, "131",
				SearchType.nearbySearch);
		System.out.println(obj.toString());
		System.out.println(JSON.toJSONString(obj));
	}

}
