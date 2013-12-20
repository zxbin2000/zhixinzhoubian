package com.android.search.common;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class PoiHtmlParse {
	public static String getDetailImgUrl(String url) {
		// 新建一个WebClient对象，此对象相当于浏览器   
        WebClient webClient = new WebClient(BrowserVersion.FIREFOX_3_6);
        // 构造一个URL   
        URL url1 = null;
		try {
			url1 = new URL(url);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return "";
		}
        // 通过getPage()方法，返回相应的页面   
        HtmlPage page;
		try {
			page = (HtmlPage) webClient.getPage(url1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
        String tmpName = "uid" + new Date().hashCode();
        File tempFile = new File("temp/" + tmpName, tmpName + ".html");
        //System.out.println(tempFile.getAbsolutePath());
        //System.out.println(page.asText());
        try {
			page.save(tempFile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			tempFile.delete();
			return "";
		}
        Document doc = null;
        String result="";
    	try {
    		doc = Jsoup.parse(tempFile, "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
			tempFile.delete();
			return "";
		}
    	Element infoDiv = doc.getElementById("shopInfoSlider");
        Elements imgElements = infoDiv.select("img");
        for(Element element: imgElements) {
        	result = element.attr("data-src");
        	break;
        }
        tempFile.delete();
        return result;
    }
	
	
	
	public static void main(String args[]) {
		try {
			String imgs = PoiHtmlParse.getDetailImgUrl("http://map.baidu.com/detail?qt=ninf&uid=9d705f80a9f36ae0f03cba1f&detail=hotel");
			System.out.println(imgs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
