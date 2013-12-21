package com.zhixinzhoubian.net;



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

public interface IRequestController {

	/**
	 * 上传用户信息
	 * @param request
	 * @return
	 */
	public UpdateUserInfoResponse saveUserInfo(UpdateUserInfoRequest request);
	
	/**
	 * 发布需求或者服务
	 * @param request - 根据messageType来确定是需求还是服务
	 * @return
	 */
	public ResponseBase publishPoiInformation(PublishInfomationRequest request);
	
	
	/**
	 * 上传用户订阅信息
	 * @param request
	 * @return
	 */
	public ResponseBase subscribeUserInterest(InterestSubscribeRequest request);
	
	
	/**
	 * 搜索周围的Poi信息
	 * @param request
	 * @return
	 */
	public PoiQueryResponse searchPoiMessages(PoiQueryRequest request);
	
	/**
	 * 发送回应
	 * @param request
	 * @return
	 */
	public SendFeedBackResponse sendFeedback(SendFeedbackRequest request);
	
	/**
	 * 获得一条Poi的回应
	 * @param request
	 * @return
	 */
	public GetFeedbackResponse getFeedBacks(GetFeedbackResquest request);
	
	
	/**
	 * 获得我发布的Poi信息
	 */
	public GetMyPoiMessageResponse getMyPoiMessages(GetMyPoiMessageRequest request);
	
}
