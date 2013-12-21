package com.baidu.zhixinzhoubian.net;

import com.baidu.zhixinzhoubian.net.response.ResponseBase;
import com.baidu.zhixinzhoubian.net.response.UpdateUserInfoResponse;

public interface CallbackListener {

	public void onFinishedSaveUserInfo(UpdateUserInfoResponse response);
	
	public void onFinishedPublishPoiInformation(ResponseBase response);
	
	
}
