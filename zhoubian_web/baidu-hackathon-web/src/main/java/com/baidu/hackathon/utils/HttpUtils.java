package com.baidu.hackathon.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class HttpUtils {
	
	private static final Logger logger = Logger.getLogger(HttpUtils.class);
	
	public static String doPost(String strUrl, String params) {
		StringBuilder builder = new StringBuilder();
		try {
			URL url = new URL(strUrl);
			System.out.println("=================URL====" + url);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setAllowUserInteraction(false);
			con.setUseCaches(false);
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
			bout.write(params);
			bout.flush();
			bout.close();
			BufferedReader bin = new BufferedReader(new InputStreamReader(con.getInputStream()));
			while (true) {
				String line = bin.readLine();
				if (line == null) {
					break;
				} else {
					builder.append(line);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
	
	/**
	 * 读取请求参数
	 * @param request
	 * @return
	 */
	public static String readParams(HttpServletRequest request) {
		String params = "";
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
			StringBuffer buffer = new StringBuffer();
			String tmpStr = null;
			while ((tmpStr = reader.readLine()) != null) {
				buffer.append(tmpStr);
			}
			params = buffer.toString();
		} catch (Exception e) {
			logger.error("解析请求参数失败:", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					logger.error("关闭读取流异常：", e);
				}
			}
		}
		return params;
	}

}
