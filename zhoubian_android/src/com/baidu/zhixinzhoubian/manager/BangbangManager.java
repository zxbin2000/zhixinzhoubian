package com.baidu.zhixinzhoubian.manager;

import android.content.Context;
import android.os.Bundle;

import com.baidu.zhixinzhoubian.net.CallbackListener;
import com.baidu.zhixinzhoubian.net.ControllerFactory;
import com.baidu.zhixinzhoubian.net.CoreThread;
import com.baidu.zhixinzhoubian.net.IRequestController;
import com.baidu.zhixinzhoubian.net.request.UpdateUserInfoRequest;
import com.baidu.zhixinzhoubian.net.response.ResponseBase;
import com.baidu.zhixinzhoubian.net.response.UpdateUserInfoResponse;
import com.baidu.zhixinzhoubian.tools.DeviceUuidFactory;

import de.greenrobot.event.EventBus;

public class BangbangManager {

	private CoreThread mCoreThread = null;
	private IRequestController mController = null;
	private volatile static BangbangManager INSTANCE;
	  
 
    public static BangbangManager getInstance(){
        if(INSTANCE == null){
           synchronized(BangbangManager.class){
               //double checking Singleton instance
               if(INSTANCE == null){
                   INSTANCE = new BangbangManager();
               }
           }
        }
        return INSTANCE;
    }
    
	private BangbangManager(){
		
		mCoreThread = new CoreThread();
		mCoreThread.start();
		mController = ControllerFactory.getRequestController();
		
	}

	public void updateUserInfo(final Context context, final long userId, final String nickName, final String telphone){
		Runnable task = new Runnable(){

			@Override
			public void run() {
				UpdateUserInfoRequest request = new UpdateUserInfoRequest();
				request.setNickName(nickName);
				request.setTelephone(telphone);
				request.setUserId(userId);
				DeviceUuidFactory factory = new DeviceUuidFactory(context);
				request.setDiviceId(factory.getDeviceUuid().toString());
				
				UpdateUserInfoResponse response =  mController.saveUserInfo(request);
				
				mCallbackListener.onFinishedSaveUserInfo(response);
				
			}
			
		};
		
		addTask(task);
	}
	


	public void PublishPoiInformation(){
		
	}
	
	/**
	 * 将task放入task queue中
	 * @param task
	 */
	private void addTask(Runnable task) {
		mCoreThread.addTask(task);
	}
	
	CallbackListener mCallbackListener = new CallbackListener() {
		
		@Override
		public void onFinishedSaveUserInfo(UpdateUserInfoResponse response) {
			BangbangEvent event = new BangbangEvent(BangbangEvent.EVENT_ID.ACTION_UPDATE_USER_INFO);
			event.setReturnCode(response.getReturnCode());
			event.setMessage(response.getMessage());
			Bundle bundle = new Bundle();
			bundle.putLong("userId", response.getUserId());
			event.setData(bundle);
			EventBus.getDefault().post(event);
		}
		
		@Override
		public void onFinishedPublishPoiInformation(ResponseBase response) {
			// TODO Auto-generated method stub
			
		}
	};
	

	/**
	 * 关闭线程
	 */
	public void shutdown(){
		mCoreThread.stopThread();
		mCoreThread = null;
	}

	
}
