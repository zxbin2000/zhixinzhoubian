package com.baidu.hackathon.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller基础类
 * @author zhuangxiaobin
 */
@Controller
@RequestMapping("/")
public class BaseController {
	private static final Logger logger = Logger.getLogger(BaseController.class);
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view =  new ModelAndView();
		view.setViewName("index");
		return view;
	}
	
	/**
	 * 读取请求参数
	 * @param request
	 * @return
	 */
	public String readParams(HttpServletRequest request) {
		String params = "";
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
			StringBuffer buffer = new StringBuffer();
			String tmpStr = null;
			while ((tmpStr = reader.readLine()) != null) {
				buffer.append(tmpStr);
			}
			params = buffer.toString();
		} catch (Exception e) {
			logger.error("解析请求参数失败:", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					logger.error("关闭读取流异常：", e);
				}
			}
		}
		return params;
	}
	
}
