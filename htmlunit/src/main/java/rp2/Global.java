package rp2;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
public class Global {
	public static HashMap<String,List<String>> TagArray= new HashMap<String,List<String>>();
	
	static {
		//List<String> childTagList = new ArrayList<String>();
		String foodTagsStr = "美食,中餐,西餐,烧烤,自助,火锅,小吃,甜点";
		TagArray.put("美食",Arrays.asList(foodTagsStr.split(",")));
		String hotelTagsStr = "酒店,旅店,旅馆,宾馆,招待所";
		TagArray.put("酒店",Arrays.asList(hotelTagsStr.split(",")));
		String shopTagsStr = "购物,商场,,沃尔玛,超市,百货,国美,苏宁";
		TagArray.put("购物",Arrays.asList(shopTagsStr.split(",")));
		String KTVTagsStr = "KTV,钱柜,夜总会,歌舞厅,迪厅";
		TagArray.put("KTV",Arrays.asList(KTVTagsStr.split(",")));
		String houseTagsStr = "房源,地产";
		TagArray.put("房源",Arrays.asList(houseTagsStr.split(",")));
		String petrolTagsStr = "加油";
		TagArray.put("加油",Arrays.asList(petrolTagsStr.split(",")));
		String movieTagsStr = "电影,影院";
		TagArray.put("电影", Arrays.asList(movieTagsStr.split(",")));
		String secondHandTagsStr = "电影,影院";
		TagArray.put("二手", Arrays.asList(secondHandTagsStr.split(",")));
		String urgentTagsStr = "紧急";
		TagArray.put("紧急", Arrays.asList(urgentTagsStr.split(",")));
		
	}
	/*public Global()
	{
		//鍒濆鍖杢ag 瀛楀吀
		ArrayList<String> tmpItem = new ArrayList<String>();
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try
		{
			String line = "";
			fis = new FileInputStream("D:/宸ヤ綔/鍏朵粬宸ヤ綔/hackthon/code/trunk/zhoubian_web/mytest/src/main/java/foo/tag.txt");
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			while(null != (line = br.readLine()))
			{
				JSONObject tagObj = JSON.parseObject(line);
				String tagName = tagObj.getString("name");
				JSONArray tagArr = tagObj.getJSONArray("taglist");
				ArrayList<String> strList = new ArrayList<String>();
				for(int i=0;i<tagArr.size();i++)
				{
					strList.add(tagArr.getString(i));
				}
				TagArray.put(tagName, strList);
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("鎸囧畾鐨勬枃浠朵笉瀛樺湪");
		}
		catch(IOException e)
		{
			System.out.println("鎵撳紑鏂囦欢澶辫触");
		}
		finally
		{
			try {
				br.close();
				isr.close();
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}*/
}
