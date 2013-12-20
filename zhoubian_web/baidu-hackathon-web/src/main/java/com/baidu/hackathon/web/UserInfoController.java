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
import com.baidu.hackathon.domain.UserInfoSubmitJson;
import com.baidu.hackathon.search.constant.ReturnCode;
import com.baidu.hackathon.service.UserInfoService;
import com.baidu.hackathon.utils.HttpUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("/submit")
public class UserInfoController {
	
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
		BaseResult<Integer> result = new BaseResult<Integer>();
		Type type = new TypeToken<BaseResult<Integer>>(){}.getType();
		if(StringUtils.isBlank(json)) {
			result.setCode(ReturnCode.ERROR);
			result.setMessage("param is null");
			return gson.toJson(result);
		}
		UserInfoSubmitJson params = gson.fromJson(json, UserInfoSubmitJson.class);
		Integer userId = userInfoService.addItem(params.getDeviceId(), params.getNickName(), params.getTelephone());
		result.setCode(ReturnCode.SUCCESS);
		result.setMessage("success");
		result.setData(userId);
		return gson.toJson(result, type);
	}
}
