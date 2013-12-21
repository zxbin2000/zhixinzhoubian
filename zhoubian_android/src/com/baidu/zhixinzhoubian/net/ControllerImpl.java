package com.baidu.zhixinzhoubian.net;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.baidu.zhixinzhoubian.net.request.GetFeedbackResquest;
import com.baidu.zhixinzhoubian.net.request.GetMyPoiMessageRequest;
import com.baidu.zhixinzhoubian.net.request.InterestSubscribeRequest;
import com.baidu.zhixinzhoubian.net.request.PoiQueryRequest;
import com.baidu.zhixinzhoubian.net.request.PublishInfomationRequest;
import com.baidu.zhixinzhoubian.net.request.SendFeedbackRequest;
import com.baidu.zhixinzhoubian.net.request.UpdateUserInfoRequest;
import com.baidu.zhixinzhoubian.net.response.GetFeedbackResponse;
import com.baidu.zhixinzhoubian.net.response.GetMyPoiMessageResponse;
import com.baidu.zhixinzhoubian.net.response.PoiQueryResponse;
import com.baidu.zhixinzhoubian.net.response.ResponseBase;
import com.baidu.zhixinzhoubian.net.response.SendFeedBackResponse;
import com.baidu.zhixinzhoubian.net.response.UpdateUserInfoResponse;
import com.baidu.zhixinzhoubian.ui.BaiduHttpClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ControllerImpl implements IRequestController {

	private static final String TAG = ControllerImpl.class.getName();

	private Gson mGson = null;
	
	private volatile static ControllerImpl INSTANCE;
	  
    private ControllerImpl(){
    	this.mGson = new Gson();
    }
 
    public static ControllerImpl getInstance(){
        if(INSTANCE == null){
           synchronized(ControllerImpl.class){
               //double checking Singleton instance
               if(INSTANCE == null){
                   INSTANCE = new ControllerImpl();
               }
           }
        }
        return INSTANCE;
    }


	@Override
	public UpdateUserInfoResponse saveUserInfo(UpdateUserInfoRequest request) {

		String jsonString = mGson.toJson(request, UpdateUserInfoRequest.class);
		JSONObject json = null;
		
		try {

			json = new JSONObject(jsonString);

		} catch (JSONException e) {
			e.printStackTrace();
			Log.e(TAG, "json error:" + e.getMessage());
		}
		
		String responseString = BaiduHttpClient.INSTANCE.doPostJSON(UrlResources.BASE_URL
				+ UrlResources.ACTION_POI_QUERY, json);

		/*
		UpdateUserInfoResponse response = mGson.fromJson(
				responseString, 
				new TypeToken<UpdateUserInfoResponse>(){}.getType());
		*/
		UpdateUserInfoResponse response = new UpdateUserInfoResponse();
		response.setReturnCode(1);
		response.setMessage("OK");
		response.setUserId(1000L);
		return response;
	}

	@Override
	public ResponseBase publishPoiInformation(PublishInfomationRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseBase subscribeUserInterest(InterestSubscribeRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PoiQueryResponse searchPoiMessages(PoiQueryRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SendFeedBackResponse sendFeedback(SendFeedbackRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetFeedbackResponse getFeedBacks(GetFeedbackResquest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GetMyPoiMessageResponse getMyPoiMessages(GetMyPoiMessageRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
