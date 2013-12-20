package com.android.cloudsearch.common;


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import com.android.cloudsearch.constant.GlobalConstant;
import com.android.cloudsearch.constant.MessageType;
import com.android.cloudsearch.constant.RequestUrl;
import com.android.cloudsearch.obj.PoiCreateStatusObj;

public class CloudHttpUtils {

	/**
	 * 从服务器获得json文件返回流
	 * 
	 * @param path
	 * @return
	 */
	public static InputStream getCloudCreatePoiJsonStream(String title, String tags, double latitude, double longitude, long userId, String message, String messageType, String photoPath, int scope, long beginTime, long endTime) {
		//coord_type
		//geotable_id
		//ak
		InputStream inputStream = null;
		try {
			URL url = new URL(RequestUrl.poi_create);
			if (url != null) {
				HttpURLConnection connection = (HttpURLConnection) url
						.openConnection();
				connection.setConnectTimeout(3000);
				connection.setDoInput(true);
				connection.setDoOutput(true);
				
				connection.setRequestMethod("POST");
				connection.setRequestProperty
		        ("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
				connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				connection.connect();
				StringBuffer params = new StringBuffer();
				CloudHttpUtils.addParamToBuffer(params, "title", title, true);
				CloudHttpUtils.addParamToBuffer(params, "tags", java.net.URLEncoder.encode(tags, "UTF-8"), false);
				CloudHttpUtils.addParamToBuffer(params, "latitude", ""+latitude, false);
				CloudHttpUtils.addParamToBuffer(params, "longitude", ""+longitude, false);
				CloudHttpUtils.addParamToBuffer(params, "coord_type", GlobalConstant.coord_type, false);
				CloudHttpUtils.addParamToBuffer(params, "geotable_id", GlobalConstant.geotable_id, false);
				CloudHttpUtils.addParamToBuffer(params, "ak", GlobalConstant.ak, false);
				CloudHttpUtils.addParamToBuffer(params, "userId", ""+userId, false);
				CloudHttpUtils.addParamToBuffer(params, "message", java.net.URLEncoder.encode(message,"UTF-8"), false);
				CloudHttpUtils.addParamToBuffer(params, "messageType", messageType, false);
				CloudHttpUtils.addParamToBuffer(params, "photoPath", java.net.URLEncoder.encode(photoPath,"UTF-8"), false);
				CloudHttpUtils.addParamToBuffer(params, "scope", ""+scope, false);
				CloudHttpUtils.addParamToBuffer(params, "beginTime", ""+beginTime, false);
				CloudHttpUtils.addParamToBuffer(params, "endTime", ""+endTime, false);
				//System.out.println(params);
				byte[] bytes = params.toString().getBytes();
				connection.getOutputStream().write(bytes);
				int code = connection.getResponseCode();
				if (code == 200) {
					inputStream = connection.getInputStream();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inputStream;
	}
	
	public static void addParamToBuffer(StringBuffer buffer, String key, String value, boolean isFirst) {
		if(isFirst)
			buffer.append(key).append("=").append(value);
		else
			buffer.append("&").append(key).append("=").append(value);
	}
	
	public static void main(String[] args) {
		//example create record on lby cloud
		//PoiCreateStatusObj statusObj = CloudTool.getPoiCreateStatusObj("title1", "tags1 tags2 tags3", 0.0, 0.0, 18612281999L, "message", MessageType.request, "http://map.baidu.com/maps/services/thumbnails?width=314&height=220&quality=100&src=http%3A%2F%2Fhiphotos.baidu.com%2Flbsugc%2Fpic%2Fitem%2Ff636afc379310a550ed7d68fb74543a9832610dd.jpg", 1000, 99999999, 9999999);
		//PoiCreateStatusObj statusObj = CloudTool.getPoiCreateStatusObj("title1", "tags1 tags2 tags3", 39.914412 ,116.401837, 18612281999L, "message", MessageType.request, "http://map.baidu.com/maps/services/thumbnails?width=314&height=220&quality=100&src=http%3A%2F%2Fhiphotos.baidu.com%2Flbsugc%2Fpic%2Fitem%2Ff636afc379310a550ed7d68fb74543a9832610dd.jpg", 1000, 99999999, 9999999);
		//System.out.println(statusObj.toString());
		
	}
}
