package com.baidu.hackathon.web;

import java.lang.reflect.Type;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.hackathon.domain.BaseResult;
import com.baidu.hackathon.domain.SearchForUI;
import com.baidu.hackathon.domain.SearchForUIData;
import com.baidu.hackathon.search.constant.ReturnCode;
import com.baidu.hackathon.service.MapSearchService;
import com.baidu.hackathon.utils.HttpUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("/msgQuery")
public class MessageQueryController {
	@Autowired
	private MapSearchService mapSearchService;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	@ResponseBody
	public String doGetTask(HttpServletRequest request, HttpServletResponse response) {
		return "test";
	}
	
	@RequestMapping(value="", method = RequestMethod.POST)
	@ResponseBody
	public String doPostTask(HttpServletRequest request, HttpServletResponse response) {
		String json = HttpUtils.readParams(request);
		Gson gson = new Gson();
		BaseResult<String> result = new BaseResult<String>();
		if(StringUtils.isBlank(json)) {
			result.setCode(ReturnCode.ERROR);
			result.setMessage("param is null");
			return gson.toJson(result);
		}
		Type type = new TypeToken<Map<String,String>>(){}.getType();
		Map<String,String> params = gson.fromJson(json, type);
		SearchForUI data = mapSearchService.getPersonalInfo(params.get("userId"), params.get("msgType"));
		Type searchResultType = new TypeToken<SearchForUI>(){}.getType();
		return gson.toJson(data, searchResultType);
	}
	
}
