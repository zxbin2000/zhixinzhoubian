package com.baidu.hackathon.search.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baidu.hackathon.domain.SearchQueryObject;

/*
 * ����location��radis������
 */
public class PoiSearchTool {
	public PoiSearchTool() {
	}

	public static SearchQueryObject getPoiSearchResult(String query, String tag,
			String industry_type, String sort_name, String sort_rule,
			String price_section, String groupon, String discount,
			String page_size, String page_num, String region,
			String latitude, String longitude, String radius, String ak, String scope) {
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
							+ latitude
							+ ","
							+ longitude + "&radius=" + radius);
			InputStream fis = PoiHttpUtils
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
							+ latitude
							+ ","
							+ longitude + "&radius=" + radius);
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
		if(qb!=null)
			qb.setTotal(qb.getResults().size());
		return qb;
	}

	public static SearchQueryObject getAllPoiSearchResult(String query,
			String tag, String industry_type, String sort_name,
			String sort_rule, String price_section, String groupon,
			String discount, String page_size, String region,
			String latitude, String longitude, String radius, String ak, String scope, int recordMax) {
		SearchQueryObject obj0 = getPoiSearchResult(query, tag, industry_type,
				sort_name, sort_rule, price_section, groupon, discount,
				page_size, "0", region, latitude, longitude, radius, ak, scope);
		if (obj0 != null && obj0.status == 0) {
			int page_from = 1;
			recordMax = obj0.total>recordMax?recordMax:obj0.total + (((recordMax>Integer.parseInt(page_size))&&(recordMax%Integer.parseInt(page_size)!=0))?1:0);
			int page_to = recordMax / Integer.parseInt(page_size) + (((recordMax>Integer.parseInt(page_size))&&(recordMax%Integer.parseInt(page_size)!=0))?1:0);
			SearchQueryObject tmpQueryObject;
			for (int i = page_from; i < page_to; i++) {
				if((Integer.parseInt(page_size)*(i+1))-recordMax>0) {
					int tmppagesize = Integer.parseInt(page_size);
					tmpQueryObject = getPoiSearchResult(query, tag, industry_type,
							sort_name, sort_rule, price_section, groupon, discount,
							tmppagesize + "", i + "", region, latitude, longitude, radius, ak, scope);
					if (tmpQueryObject != null && tmpQueryObject.status == 0) {
						if (tmpQueryObject.getResults() != null
								&& tmpQueryObject.getResults().size() > 0) {
							obj0.getResults().addAll(tmpQueryObject.getResults());
						}
					}
				}
				else {
					int tmppagesize = Integer.parseInt(page_size)*(i+1)-recordMax;
					tmpQueryObject = getPoiSearchResult(query, tag, industry_type,
							sort_name, sort_rule, price_section, groupon, discount,
							tmppagesize + "", i + "", region, latitude, longitude, radius, ak, scope);
					if (tmpQueryObject != null && tmpQueryObject.status == 0) {
						if (tmpQueryObject.getResults() != null
								&& tmpQueryObject.getResults().size() > 0) {
							obj0.getResults().addAll(tmpQueryObject.getResults());
						}
					}
				}
			}
		}
		if(obj0!=null)
			obj0.setTotal(obj0.getResults().size());
		return obj0;
	}	
}