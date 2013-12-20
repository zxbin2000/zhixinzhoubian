package com.baidu.hackathon.web;

import java.lang.reflect.Type;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.hackathon.domain.BaseResult;
import com.baidu.hackathon.domain.MessageInfoJson;
import com.baidu.hackathon.domain.PoiCreateStatusObj;
import com.baidu.hackathon.domain.UserInfo;
import com.baidu.hackathon.search.common.PoiTag;
import com.baidu.hackathon.search.constant.ReturnCode;
import com.baidu.hackathon.service.MapSearchService;
import com.baidu.hackathon.service.UserInfoService;
import com.baidu.hackathon.utils.HttpUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("/postMessage")
public class PostMessageController {
	
	@Autowired
	private MapSearchService mapSearchService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public String doGetTask(HttpServletRequest request, HttpServletResponse response) {
		return "test";
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public String doPostTask(HttpServletRequest request, HttpServletResponse response) {
		String json = HttpUtils.readParams(request);
		Gson gson = new Gson();
		BaseResult<String> result = new BaseResult<String>();
		Type type = new TypeToken<BaseResult<String>>(){}.getType();
		if(StringUtils.isBlank(json)) {
			result.setCode(ReturnCode.ERROR);
			result.setMessage("param is null.");
			return gson.toJson(result);
		}
		Type pType = new TypeToken<MessageInfoJson>(){}.getType();
		MessageInfoJson param = gson.fromJson(json, pType);
		UserInfo user = userInfoService.selectOne(param.getUserId());
		StringBuilder builder = new StringBuilder();
		for(String url : param.getPhotoPath()) {
			builder.append(url);
			builder.append(";");
		}
		String photoPath = builder.toString();
		String poiTag = PoiTag.getPoiValue(param.getPoiTag());
		PoiCreateStatusObj obj = mapSearchService.createPoi(user.getNickName(), poiTag, param.getLatY(), param.getLngX(), new Long(user.getUserId()),
				param.getMessage(), String.valueOf(param.getMessageType()), photoPath.substring(0, photoPath.length() - 1),
				param.getDistance(), param.getBeginTime().getTime(), param.getEndTime().getTime());
		result.setCode(ReturnCode.SUCCESS);
		result.setMessage("success");
		result.setData(obj.getId());
		return gson.toJson(result, type);
	}
	
}
