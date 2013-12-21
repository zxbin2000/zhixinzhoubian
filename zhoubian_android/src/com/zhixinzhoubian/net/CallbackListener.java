package com.zhixinzhoubian.net;



import com.zhixinzhoubian.net.response.ResponseBase;
import com.zhixinzhoubian.net.response.UpdateUserInfoResponse;

public interface CallbackListener {

	public void onFinishedSaveUserInfo(UpdateUserInfoResponse response);
	
	public void onFinishedPublishPoiInformation(ResponseBase response);
	
	
}
