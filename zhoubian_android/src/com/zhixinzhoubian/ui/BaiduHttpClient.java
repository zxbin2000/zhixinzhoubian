package com.zhixinzhoubian.ui;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;
import android.util.Log;

public enum BaiduHttpClient {
	
	INSTANCE;
	
	private final static String TAG = "baidu.httpclient";

	private BaiduHttpClient() {
		
	}

	public String doGet(String url, Map<String, Object> paramMap) {
		String result = "";
		String params = prepareParam(paramMap);
		if (!TextUtils.isEmpty(params)) {
			if (url.indexOf("?") == -1 && url.indexOf("&") == -1) {
				url = "?" + params;
			} else {
				url = "&" + params;
			}
		}
		HttpClient httpClient = new DefaultHttpClient();
		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10 * 1000);
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 10 * 1000);
		try {
			HttpGet httpGet = new HttpGet(url);
			HttpResponse response = httpClient.execute(httpGet);
			if (response != null && HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				HttpEntity httpEntity = response.getEntity();
				if (httpEntity != null) {
					result = EntityUtils.toString(httpEntity, HTTP.UTF_8);
				}
			}
		} catch (Exception e) {
			Log.e(TAG, "do get error:" + e.getMessage());
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return result;
	}

	public String doPost(String url, Map<String, Object> paramMap, boolean isJson, boolean setCookies,
			boolean saveCookies) throws IOException {
		String result = "";
		HttpEntity httpEntity = null;
		if (paramMap != null && !paramMap.isEmpty()) {

			if (isJson) {

				JSONObject jo = new JSONObject();
				for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
					String key = entry.getKey();
					Object value = (entry.getValue() == null ? "" : entry.getValue());
					try {

						jo.put(key, value.toString());

					} catch (JSONException e) {
						e.printStackTrace();
					}
				}

				httpEntity = new StringEntity(jo.toString());

			} else {

				List<NameValuePair> paramList = new ArrayList<NameValuePair>();
				for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
					String key = entry.getKey();
					Object value = (entry.getValue() == null ? "" : entry.getValue());
					NameValuePair pair = new BasicNameValuePair(key, value.toString());
					paramList.add(pair);
				}
				try {
					httpEntity = new UrlEncodedFormEntity(paramList, HTTP.UTF_8);
				} catch (UnsupportedEncodingException e) {
					Log.e(TAG, "do post encode error:" + e.getMessage());
				}

			}

		}
		HttpClient httpClient = new DefaultHttpClient();
		try {
			httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10 * 1000);
			httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 10 * 1000);
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(httpEntity);

			HttpResponse response = httpClient.execute(httpPost);
			// 保存wanba cookies

			if (response != null && HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					result = EntityUtils.toString(entity, HTTP.UTF_8);
				}
			}
		} catch (Exception e) {
			Log.e(TAG, "do post error:" + e.getMessage());
			throw new IOException();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return result;
	}


	public String doPostJSON(String url, JSONObject json) {
		String result = "";
		HttpClient httpClient = new DefaultHttpClient();
		try {
			httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10 * 1000);
			httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 10 * 1000);
			HttpPost httpPost = new HttpPost(url);
			httpPost.addHeader("content-type", "application/json");
			if (json != null) {
				StringEntity httpEntity = new StringEntity(json.toString(), "UTF-8");
				httpPost.setEntity(httpEntity);
			}
			HttpResponse response = httpClient.execute(httpPost);
			if (response != null && HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					result = EntityUtils.toString(entity, HTTP.UTF_8);
				}
			}
		} catch (UnsupportedEncodingException e) {
			Log.e(TAG, "postjson encode error:" + e.getMessage());
		} catch (ClientProtocolException e) {
			Log.e(TAG, "postjson clientProtocolException error:" + e.getMessage());
		} catch (IOException e) {
			Log.e(TAG, "postjson ioException error:" + e.getMessage());
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return result;
	}
	

	/**
	 * 预处理请求参数值
	 * 
	 * @param paramMap
	 * @return
	 */
	private String prepareParam(Map<String, Object> paramMap) {
		StringBuffer buffer = new StringBuffer();
		if (paramMap == null) {
			return buffer.toString();
		}
		for (String key : paramMap.keySet()) {
			try {
				String value = (String) paramMap.get(key);
				if (buffer.length() < 1) {
					buffer.append(URLEncoder.encode(key, "UTF-8"));
					buffer.append("=");
					buffer.append(URLEncoder.encode(value, "UTF-8"));
				} else {
					buffer.append("&");
					buffer.append(URLEncoder.encode(key, "UTF-8"));
					buffer.append("=");
					buffer.append(URLEncoder.encode(value, "UTF-8"));
				}
			} catch (Exception e) {
				Log.e(TAG, "encode error:" + e.getMessage());
			}

		}
		return buffer.toString();
	}

}