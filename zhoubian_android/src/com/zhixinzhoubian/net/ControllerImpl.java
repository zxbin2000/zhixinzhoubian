package com.zhixinzhoubian.net;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.google.gson.Gson;
import com.zhixinzhoubian.net.request.GetFeedbackResquest;
import com.zhixinzhoubian.net.request.GetMyPoiMessageRequest;
import com.zhixinzhoubian.net.request.InterestSubscribeRequest;
import com.zhixinzhoubian.net.request.PoiQueryRequest;
import com.zhixinzhoubian.net.request.PublishInfomationRequest;
import com.zhixinzhoubian.net.request.SendFeedbackRequest;
import com.zhixinzhoubian.net.request.UpdateUserInfoRequest;
import com.zhixinzhoubian.net.response.GetFeedbackResponse;
import com.zhixinzhoubian.net.response.GetMyPoiMessageResponse;
import com.zhixinzhoubian.net.response.PoiQueryResponse;
import com.zhixinzhoubian.net.response.ResponseBase;
import com.zhixinzhoubian.net.response.SendFeedBackResponse;
import com.zhixinzhoubian.net.response.UpdateUserInfoResponse;
import com.zhixinzhoubian.ui.BaiduHttpClient;

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
