package com.android.search.rp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//推荐类
public class Rp {
	public Rp(){}
	
	//计算两个用户（需求方，服务方）的匹配度
	public Double getSim(UserIndex u1,UserIndex u2)
	{
		List<String> tagList1 = u1.tagList;
		List<String> tagList2 = u2.tagList;
		List<String> tmpList = u2.tagList;
		double sum = Math.sqrt((double)tagList1.size())*Math.sqrt((double)tagList2.size());
		tmpList.retainAll(tagList1);
		int num = tmpList.size();
		return num/sum;
	}
	
	//对于一个需求方，计算N个服务方的匹配度，并排序
	public List<Map.Entry<String, Double>> correlationCalculation(UserIndex buyer ,ArrayList<UserIndex> sellers)
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
	
//	public static void main(String[] args) {
//		HashMap<String,Double> map = new HashMap<String,Double>();
//		map.put("1", 0.3);
//		map.put("2", 0.4);
//		map.put("4", 0.3);
//		map.put("5", 0.4);
//		//List<Map.Entry<String, Double>> lmap = new ArrayList<Map.Entry<String, Double>>(map.entrySet());
//		//System.out.println(lmap.toString());
//		List<Map.Entry<String, Double>> xx = sortedByValue(map);
//		System.out.println(xx.toString());
//	}
}
