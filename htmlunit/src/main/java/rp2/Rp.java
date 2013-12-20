package rp2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//锟狡硷拷锟斤拷
public class Rp {
	public Rp(){}
	public static int getUnionNum(UserIndex u1,UserIndex u2)
	{
		int res=0;
		for(int i =0 ;i<u1.tagList.size();i++)
		{
			String tag = u1.tagList.get(i);
			List<String> simList = Global.TagArray.get(tag);
			for(String u2tag: u2.tagList) {
				for(String childTag : simList) {
					if(u2tag.indexOf(childTag)>0) {
						res++;
						break;
					}
				}
			}
//			for(int k=0;k<simList.size();k++)
//			{
//				String simTag = simList.get(k);
//				for(int m=0;m<u2.tagList.size();m++)
//				{
//					String tmp = u2.tagList.get(m);
//					if(tmp.indexOf(simTag)>0)
//					{
//						res++;
//					}
//				}
//			}
		}
		return res;
	}
	//锟斤拷锟斤拷锟斤拷锟斤拷锟矫伙拷锟斤拷锟斤拷锟襟方ｏ拷锟斤拷锟今方ｏ拷锟斤拷匹锟斤拷锟�
	public static Double getSim(UserIndex u1,UserIndex u2)
	{
		List<String> tagList1 = u1.tagList;
		List<String> tagList2 = u2.tagList;
		double sum = Math.sqrt((double)tagList1.size())*Math.sqrt((double)tagList2.size());
		int union_num = getUnionNum(u1,u2);
		return union_num/sum;
	}
	
	//锟斤拷锟斤拷一锟斤拷锟斤拷锟襟方ｏ拷锟斤拷锟斤拷N锟斤拷锟斤拷锟今方碉拷匹锟斤拷龋锟斤拷锟斤拷锟斤拷锟�
	public static List<Map.Entry<String, Double>> correlationCalculation(UserIndex buyer ,List<UserIndex> sellers)
	{
		HashMap<String,Double> sim_map = new HashMap<String,Double>();
		for(int i =0;i<sellers.size();i++)
		{
			Double tmp = getSim(buyer,sellers.get(i));
			sim_map.put(sellers.get(i).uid, tmp);
		}
		List<Map.Entry<String, Double>> res_list = sortedByValue(sim_map);
		return res_list;
	}
	
	public static List<Map.Entry<String, Double>> sortedByValue(HashMap<String,Double> map)
	{
		Map<String,Double> tmp_map = map;
		List<Map.Entry<String, Double>> lmap = new ArrayList<Map.Entry<String, Double>>(tmp_map.entrySet());
		Collections.sort(lmap, new Comparator<Map.Entry<String, Double>>() {   
		    public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {      
		        if((o2.getValue()-o1.getValue())>0.0)
		        	return 1;
		        else if((o2.getValue()-o1.getValue())<0.0)
		        	return -1;
		        else
		        	return 0;
		    }
		});
		return lmap;
	}
}
