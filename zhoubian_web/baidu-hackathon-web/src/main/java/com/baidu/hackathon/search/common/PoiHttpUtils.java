package com.baidu.hackathon.search.common;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PoiHttpUtils {

	/**
	 * �ӷ��������json�ļ�������
	 * 
	 * @param path
	 * @return
	 */
	public static InputStream getJsonStream(String path) {
		InputStream inputStream = null;
		try {
			URL url = new URL(path);
			if (url != null) {
				HttpURLConnection connection = (HttpURLConnection) url
						.openConnection();
				connection.setConnectTimeout(3000);
				connection.setDoInput(true);
				connection.setRequestMethod("GET");
				int code = connection.getResponseCode();
				if (code == 200) {
					inputStream = connection.getInputStream();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return inputStream;
	}
	
	public static String getHtml(String path) {
		InputStream fis = PoiHttpUtils.getJsonStream(path);
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
	
	public static void main(String[] args) {
		String path = "http://map.baidu.com/detail?qt=ninf&uid=9d705f80a9f36ae0f03cba1f&detail=hotel";
		String result = PoiHttpUtils.getHtml(path);
		System.out.println(result);
		
	}
}
