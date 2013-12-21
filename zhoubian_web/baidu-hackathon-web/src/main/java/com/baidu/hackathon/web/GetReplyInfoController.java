package com.baidu.hackathon.web;

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

@Controller
@RequestMapping("/getReplyList")
public class GetReplyInfoController {
	@Autowired
	private ReplyInfoService replyInfoService;
	
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
		BaseResult<ReplyInfo> result = new BaseResult<ReplyInfo>();
		if(StringUtils.isBlank(json)) {
			result.setCode(ReturnCode.ERROR);
			result.setMessage("param is null.");
			return gson.toJson(result);
		}
		
		return "";
	}
}
