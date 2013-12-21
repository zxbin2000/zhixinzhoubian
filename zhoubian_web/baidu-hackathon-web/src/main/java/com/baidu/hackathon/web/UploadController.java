package com.baidu.hackathon.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.hackathon.domain.BaseResult;
import com.baidu.hackathon.search.constant.ReturnCode;
import com.google.gson.Gson;

@Controller
@RequestMapping("/image/upload")
public class UploadController {
	private static final Logger logger = Logger.getLogger(UploadController.class);

	private long maxSize = 5 * 1024 * 1024;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public String doGetTask(HttpServletRequest request, HttpServletResponse response) {
		return "test";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public String doUploadTask(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		String savePath = "D:/upload/";
		Gson gson = new Gson();
		BaseResult<String> result = new BaseResult<String>();

		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

		if (!ServletFileUpload.isMultipartContent(request)) {
			result.setCode(ReturnCode.ERROR);
			result.setMessage("please select image file..");
			return gson.toJson(result);
		}
		File uploadDirFile = new File(savePath);
		if (!uploadDirFile.exists()) {
			uploadDirFile.mkdir();
		}
		String dirName = request.getParameter("dir");
		if (dirName == null) {
			dirName = "image";
		}
		if (!extMap.containsKey(dirName)) {
			result.setCode(ReturnCode.ERROR);
			result.setMessage("目录名不正确");
			return gson.toJson(result);
		}
		// 创建文件夹
		savePath += dirName + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		try {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			List items = upload.parseRequest(request);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				String fileName = item.getName();
				if (!item.isFormField()) {
					// 检查文件大小
					if (item.getSize() > maxSize) {
						result.setCode(ReturnCode.ERROR);
						result.setMessage("上传文件大小超过限制");
						return gson.toJson(result);
					}
					// 检查扩展名
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					if (!Arrays.<String> asList(extMap.get(dirName).split(",")).contains(fileExt)) {
						result.setCode(ReturnCode.ERROR);
						result.setMessage("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式");
						return gson.toJson(result);
					}

					SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
					String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
					try {
						File uploadedFile = new File(savePath, newFileName);
						item.write(uploadedFile);
					} catch (Exception e) {
						result.setCode(ReturnCode.ERROR);
						result.setMessage("上传文件失败");
						return gson.toJson(result);
					}
					result.setCode(ReturnCode.SUCCESS);
					result.setMessage("success");

					return gson.toJson(result);
				}
			}
		} catch (Exception e) {
			logger.error("上传出现异常：", e);
		}
		return "";
	}

}
