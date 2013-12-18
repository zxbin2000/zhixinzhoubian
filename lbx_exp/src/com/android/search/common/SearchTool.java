package com.android.search.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.android.search.obj.SearchQueryObject;

/*
 * 基于location和radis的搜索
 */
public class SearchTool {
	public SearchTool() {
	}

	/**
	 * @param args
	 */
//	public static void main(String[] args) {
//
//		Location loc = new Location();
//		loc.setLat(39.920148);
//		loc.setLng(116.417388);
//		SearchQueryObject obj = getAllSearchResult("美食", "", "", "price",
//				"1", "", "1", "", "20", "北京", loc, "10",
//				"6cb3f458a482fcd009414370808ea219", "2",100);
//		System.out.println(obj);
//	}

	public static SearchQueryObject getSearchResult(String query, String tag,
			String industry_type, String sort_name, String sort_rule,
			String price_section, String groupon, String discount,
			String page_size, String page_num, String region,
			Location location, String radius, String ak, String scope) {
		String result = "";
		SearchQueryObject qb = null;
		try {
			System.out.println("http://api.map.baidu.com/place/v2/search?&q="
					+ query
					+ "&tag="
					+ tag
					+ "&filter=industry_type:"
					+ industry_type
					+ "|sort_name:"
					+ sort_name
					+ "|sort_rule:"
					+ sort_rule
					+ "|price_section:"
					+ price_section
					+ "|groupon:"
					+ groupon
					+ "|discount:"
					+ discount
					+ "&page_size="
					+ page_size
					+ "&page_num="
					+ page_num
					+ "&region="
					+ region
					+ "&output=json&ak="
					+ ak
					+ "&scope="
					+ scope
					+ "&location="
					+ location.lat
					+ ","
					+ location.lng + "&radius=" + radius);
			InputStream fis = HttpUtils
					.getJsonStream("http://api.map.baidu.com/place/v2/search?&q="
							+ query
							+ "&tag="
							+ tag
							+ "&filter=industry_type:"
							+ industry_type
							+ "|sort_name:"
							+ sort_name
							+ "|sort_rule:"
							+ sort_rule
							+ "|price_section:"
							+ price_section
							+ "|groupon:"
							+ groupon
							+ "|discount:"
							+ discount
							+ "&page_size="
							+ page_size
							+ "&page_num="
							+ page_num
							+ "&region="
							+ region
							+ "&output=json&ak="
							+ ak
							+ "&scope="
							+ scope
							+ "&location="
							+ location.lat
							+ ","
							+ location.lng + "&radius=" + radius);
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
			qb = JSON.parseObject(result,
					new TypeReference<SearchQueryObject>() {
					});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return qb;
	}

	public static SearchQueryObject getAllSearchResult(String query,
			String tag, String industry_type, String sort_name,
			String sort_rule, String price_section, String groupon,
			String discount, String page_size, String region,
			Location location, String radius, String ak, String scope, int recordMax) {
		SearchQueryObject obj0 = getSearchResult(query, tag, industry_type,
				sort_name, sort_rule, price_section, groupon, discount,
				page_size, "0", region, location, radius, ak, scope);
		if (obj0 != null && obj0.status == 0) {
			int page_from = 1;
			recordMax = obj0.total>recordMax?recordMax:obj0.total;
			int page_to = recordMax / Integer.parseInt(page_size);
			SearchQueryObject tmpQueryObject;
			for (int i = page_from; i < page_to; i++) {
				tmpQueryObject = getSearchResult(query, tag, industry_type,
						sort_name, sort_rule, price_section, groupon, discount,
						page_size, i + "", region, location, radius, ak, scope);
				if (tmpQueryObject != null && tmpQueryObject.status == 0) {
					if (tmpQueryObject.getResults() != null
							&& tmpQueryObject.getResults().size() > 0) {
						obj0.getResults().addAll(tmpQueryObject.getResults());
					}
				}
			}
		}
		return obj0;
	}
}
