package com.baidu.hackathon.web;

import java.lang.reflect.Type;
import java.util.Date;
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
import com.baidu.hackathon.domain.ReplyInfo;
import com.baidu.hackathon.search.constant.ReturnCode;
import com.baidu.hackathon.service.ReplyInfoService;
import com.baidu.hackathon.utils.HttpUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("/reply")
public class ReplyInfoController {
	
	@Autowired
	private ReplyInfoService replyInfoService;
	
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
		Type type = new TypeToken<Map<String, String>>(){}.getType();
		if(StringUtils.isBlank(json)) {
			result.setCode(ReturnCode.ERROR);
			result.setMessage("param is null.");
			result.setData(0);
			return gson.toJson(result);
		}
		Map<String, String> params = gson.fromJson(json, type);
		String userId = params.get("userId");
		String poiId = params.get("poiId");
		String message = params.get("message");
		ReplyInfo item = new ReplyInfo();
		item.setUserId(Integer.parseInt(userId));
		item.setPoiId(poiId);
		item.setMessage(message);
		item.setTimeline(new Date());
		Integer replyId = replyInfoService.addItem(item);
		if(replyId > 0) {
			result.setCode(ReturnCode.ERROR);
			result.setMessage("insert error.");
			result.setData(0);
			return gson.toJson(result);
		}
		result.setCode(ReturnCode.SUCCESS);
		result.setMessage("success");
		result.setData(replyId);
		Type returnType = new TypeToken<BaseResult<Integer>>(){}.getType();
		return gson.toJson(result, returnType);
	}
	
}