package com.zhixinzhoubian.net;



import com.zhixinzhoubian.net.response.GetFeedbackResponse;
import com.zhixinzhoubian.net.response.GetMyPoiMessageResponse;
import com.zhixinzhoubian.net.response.PoiQueryResponse;
import com.zhixinzhoubian.net.response.ResponseBase;
import com.zhixinzhoubian.net.response.SendFeedBackResponse;
import com.zhixinzhoubian.net.response.UpdateUserInfoResponse;

public interface CallbackListener {

	public void onFinishedSaveUserInfo(UpdateUserInfoResponse response);
	
	public void onFinishedPublishPoiInformation(ResponseBase response);
	
	public void onFinishedsubscribeUserInterest(ResponseBase response);
	
	public void onFinishedsearchPoiMessages(PoiQueryResponse response);
	
	public void onFinishedsendFeedback(SendFeedBackResponse response);
	
	public void onFinishedgetFeedBacks(GetFeedbackResponse response);
	
	public void onFinishedgetMyPoiMessages(GetMyPoiMessageResponse response);
	
}
