package com.baidu.hackathon.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.hackathon.domain.BaseResult;
import com.baidu.hackathon.domain.PoiSearchParam;
import com.baidu.hackathon.domain.SearchForUI;
import com.baidu.hackathon.domain.SearchForUIData;
import com.baidu.hackathon.search.constant.ReturnCode;
import com.baidu.hackathon.service.MapSearchService;
import com.baidu.hackathon.utils.HttpUtils;
import com.google.gson.Gson;

@Controller
@RequestMapping("/poiSearch")
public class SearchController {
	@Autowired
	private MapSearchService mapSearchService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public String doGetTask(HttpServletRequest request, HttpServletResponse response) {
		return "test";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public String search(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		String json = HttpUtils.readParams(request);
		Gson gson = new Gson();
		BaseResult<List<SearchForUIData>> result = new BaseResult<List<SearchForUIData>>();
		if(StringUtils.isBlank(json)) {
			result.setCode(ReturnCode.ERROR);
			result.setMessage("请求参数不能为空");
			result.setData(null);
			return gson.toJson(result);
		}
		PoiSearchParam params = gson.fromJson(json, PoiSearchParam.class);
		String latY = String.valueOf(params.getLatY());
		String lngX = String.valueOf(params.getLngX());
		String scope = String.valueOf(params.getDistance());
		SearchForUI searchResult = mapSearchService.getNearByInfo(latY, lngX, scope, params.getTags());
		result.setCode(ReturnCode.SUCCESS);
		result.setMessage("success");
		result.setData(searchResult.getData());
		return gson.toJson(result);
	}



}
