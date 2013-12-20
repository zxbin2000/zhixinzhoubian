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
import com.baidu.hackathon.domain.SubscibeInfo;
import com.baidu.hackathon.domain.SubscibeInfoJson;
import com.baidu.hackathon.search.common.PoiTag;
import com.baidu.hackathon.search.constant.ReturnCode;
import com.baidu.hackathon.service.SubscibeService;
import com.baidu.hackathon.utils.HttpUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("/subscibe")
public class SubscibeController {
	
	@Autowired
	private SubscibeService subscibeService;

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
		BaseResult<Integer> result = new BaseResult<Integer>();
		Type type = new TypeToken<BaseResult<Integer>>(){}.getType();
		if (StringUtils.isBlank(json)) {
			result.setCode(ReturnCode.ERROR);
			result.setMessage("param is null.");
			result.setData(0);
			return gson.toJson(result, type);
		}
		SubscibeInfoJson params = gson.fromJson(json, SubscibeInfoJson.class);
		SubscibeInfo vo = jsonToVo(params);
		Integer sid = subscibeService.addItem(vo);
		if(sid > 0) {
			result.setCode(ReturnCode.SUCCESS);
			result.setMessage("success");
			result.setData(sid);
		} else {
			result.setCode(ReturnCode.ERROR);
			result.setMessage("insert error.");
			result.setData(0);
		}
		return gson.toJson(result, type);
	}
	
	private SubscibeInfo jsonToVo(SubscibeInfoJson json) {
		SubscibeInfo subscibeInfo = new SubscibeInfo();
		subscibeInfo.setUserId(json.getUserId());
		subscibeInfo.setDistance(json.getDistance());
		subscibeInfo.setFrequency(json.getFrequency());
		StringBuilder builder = new StringBuilder();
		for(String i : json.getTagList()) {
			builder.append(PoiTag.getPoiValue(Integer.parseInt(i)));
			builder.append(",");
		}
		String s = builder.toString();
		subscibeInfo.setTagList(s.substring(0, s.length() - 1));
		return subscibeInfo;
	}
	
}
