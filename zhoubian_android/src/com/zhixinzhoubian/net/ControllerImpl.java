package com.zhixinzhoubian.net;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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

	private ControllerImpl() {
		this.mGson = new Gson();
	}

	public static ControllerImpl getInstance() {
		if (INSTANCE == null) {
			synchronized (ControllerImpl.class) {
				// double checking Singleton instance
				if (INSTANCE == null) {
					INSTANCE = new ControllerImpl();
				}
			}
		}
		return INSTANCE;
	}

	@Override
	public UpdateUserInfoResponse saveUserInfo(UpdateUserInfoRequest request) {

		String jsonString = mGson.toJson(request, UpdateUserInfoRequest.class);

		String responseString = BaiduHttpClient.INSTANCE.doPostJSON(
				UrlResources.BASE_URL + UrlResources.ACTION_SAVE_USER,
				jsonString);
		UpdateUserInfoResponse response = mGson.fromJson(responseString,
				new TypeToken<UpdateUserInfoResponse>() {
				}.getType());
		return response;
	}

	@Override
	public ResponseBase publishPoiInformation(PublishInfomationRequest request) {
		// TODO Auto-generated method stub
		String jsonString = mGson.toJson(request,
				PublishInfomationRequest.class);

		String responseString = BaiduHttpClient.INSTANCE.doPostJSON(
				UrlResources.BASE_URL + UrlResources.ACTION_PUB_POI,
				jsonString);

		ResponseBase response = mGson.fromJson(responseString,
				new TypeToken<ResponseBase>() {
				}.getType());

		return response;
	}

	@Override
	public ResponseBase subscribeUserInterest(InterestSubscribeRequest request) {
		// TODO Auto-generated method stub
		String jsonString = mGson.toJson(request,
				InterestSubscribeRequest.class);

		String responseString = BaiduHttpClient.INSTANCE.doPostJSON(
				UrlResources.BASE_URL + UrlResources.ACTION_SUB_INTST,
				jsonString);

		ResponseBase response = mGson.fromJson(responseString,
				new TypeToken<ResponseBase>() {
				}.getType());

		return response;
	}

	@Override
	public PoiQueryResponse searchPoiMessages(PoiQueryRequest request) {
		// TODO Auto-generated method stub
		String jsonString = mGson.toJson(request, PoiQueryRequest.class);
		Log.e("error", "searchPoiMessages:"+jsonString);

		String responseString = BaiduHttpClient.INSTANCE.doPostJSON(
				UrlResources.BASE_URL + UrlResources.ACTION_SEARCH_POI,
				jsonString);

		PoiQueryResponse response = mGson.fromJson(responseString,
				new TypeToken<PoiQueryResponse>() {
				}.getType());

		return response;
	}

	@Override
	public SendFeedBackResponse sendFeedback(SendFeedbackRequest request) {
		// TODO Auto-generated method stub
		String jsonString = mGson.toJson(request, SendFeedbackRequest.class);

		String responseString = BaiduHttpClient.INSTANCE.doPostJSON(
				UrlResources.BASE_URL + UrlResources.ACTION_SEND_FEEDBACK,
				jsonString);

		SendFeedBackResponse response = mGson.fromJson(responseString,
				new TypeToken<SendFeedBackResponse>() {
				}.getType());

		return response;
	}

	@Override
	public GetFeedbackResponse getFeedBacks(GetFeedbackResquest request) {
		// TODO Auto-generated method stub
		String jsonString = mGson.toJson(request, GetFeedbackResquest.class);

		String responseString = BaiduHttpClient.INSTANCE.doPostJSON(
				UrlResources.BASE_URL + UrlResources.ACTION_GET_FEEDBACKS,
				jsonString);

		GetFeedbackResponse response = mGson.fromJson(responseString,
				new TypeToken<GetFeedbackResponse>() {
				}.getType());

		return response;
	}

	@Override
	public GetMyPoiMessageResponse getMyPoiMessages(
			GetMyPoiMessageRequest request) {
		// TODO Auto-generated method stub
		String jsonString = mGson.toJson(request, GetMyPoiMessageRequest.class);

		String responseString = BaiduHttpClient.INSTANCE.doPostJSON(
				UrlResources.BASE_URL + UrlResources.ACTION_GET_POI_MSG,
				jsonString);

		GetMyPoiMessageResponse response = mGson.fromJson(responseString,
				new TypeToken<GetMyPoiMessageResponse>() {
				}.getType());

		return response;
	}
	

}
