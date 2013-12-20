import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


public class HtmlUnitTest {
	
	public static void main(String[] args) throws Exception {
		// 新建一个WebClient对象，此对象相当于浏览器   
        final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_3_6);
        // 构造一个URL   
        URL url = new URL("http://map.baidu.com/detail?qt=ninf&uid=9d705f80a9f36ae0f03cba1f&detail=hotel");
        // 通过getPage()方法，返回相应的页面   
        HtmlPage page = (HtmlPage) webClient.getPage(url);
        String tmpName = "uid" + new Date().hashCode();
        File tempFile = new File("temp/" + tmpName, tmpName + ".html");
        //System.out.println(tempFile.getAbsolutePath());
        //System.out.println(page.asText());
        page.save(tempFile);
        
        //String localPath = "D:\\cc.xx.html";
        String localPath = tempFile.getAbsolutePath();
        Document doc = null;
       //StringBuilder sBuilder = new StringBuilder();
        String result="";
    	try {
    		//System.out.println(localPath);
			//doc = Jsoup.connect(localPath).get();
    		doc = Jsoup.parse(tempFile, "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
    	Element infoDiv = doc.getElementById("shopInfoSlider");
        Elements imgElements = infoDiv.select("img");
        for(Element element: imgElements) {
        	result = element.attr("data-src");
        	break;
        	//sBuilder.append(element.attr("data-src")+",");
        }
//        if(sBuilder.length()!=0)
//        	result = sBuilder.substring(0, sBuilder.length()-1);
//        else
//        	result = sBuilder.toString();
//        return result;
        
	}

}

