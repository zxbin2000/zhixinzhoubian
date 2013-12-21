package com.zhixinzhoubian.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.IntentSender.SendIntentException;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.os.UserHandle;
import android.util.Log;
import android.view.Display;

import com.zhixinzhoubian.model.PoiMessage;
import com.zhixinzhoubian.net.CallbackListener;
import com.zhixinzhoubian.net.ControllerFactory;
import com.zhixinzhoubian.net.CoreThread;
import com.zhixinzhoubian.net.IRequestController;
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
import com.zhixinzhoubian.tools.DeviceUuidFactory;

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
				request.setDeviceId(factory.getDeviceUuid().toString());
				UpdateUserInfoResponse response =  mController.saveUserInfo(request);
				
				mCallbackListener.onFinishedSaveUserInfo(response);
				
			}
			
		};
		
		addTask(task);
	}
	

	//miaolong
	public void publishPoiInformation(final Context context, final long userId, final String message, final String photoPath, final int messageType, final String poiTag, final double langX, final double latY, final int distance, final int 	timelines){
		Runnable task = new Runnable(){

			@Override
			public void run() {
				PublishInfomationRequest request = new PublishInfomationRequest();
				request.setUserId(userId + "");
				request.setMessage(message);
				request.setMessageType(messageType);
				request.setPhotoPath(photoPath);
				request.setPoiTag(poiTag);
				request.setLangX(langX);
				request.setLatY(latY);
				request.setDistance(distance);
				long now = new Date().getTime();
				long endTime = now + timelines*60000;
				request.setBeginTime(now + "");
				request.setEndTime(endTime + "");
				ResponseBase response =  mController.publishPoiInformation(request);
				mCallbackListener.onFinishedPublishPoiInformation(response);
				
			}
			
		};
		
		addTask(task);
	}
	
	//miaolong
	public void subscribeUserInterest(final Context context, final long userId, final int[] tagList, final int distance, final int frequency){
		Runnable task = new Runnable(){

			@Override
			public void run() {
				InterestSubscribeRequest request = new InterestSubscribeRequest(userId, tagList, distance, frequency);
				ResponseBase response =  mController.subscribeUserInterest(request);
				mCallbackListener.onFinishedsubscribeUserInterest(response);
			}
			
		};
		addTask(task);
	}
	
	public void searchPoiMessages(final Context context, final long userId, final double langX, final double latY, final int distance, final int[] tagList) {
		Runnable task = new Runnable(){

			@Override
			public void run() {
				PoiQueryRequest request = new PoiQueryRequest();
				request.setUserId(userId);
				request.setLngX(langX);
				request.setLatY(latY);
				request.setDistance(distance);
				request.setTagList(tagList);
				PoiQueryResponse response =  mController.searchPoiMessages(request);
				Log.d("miaolong:searchPoiMessages", response.toString());
				mCallbackListener.onFinishedsearchPoiMessages(response);
			}
			
		};
		addTask(task);
	}
	
	public void sendFeedback(final Context context, final long userId, final String poiId, final String message) {
		Runnable task = new Runnable(){

			@Override
			public void run() {
				SendFeedbackRequest request = new SendFeedbackRequest(userId, poiId, message);
				SendFeedBackResponse response =  mController.sendFeedback(request);
				mCallbackListener.onFinishedsendFeedback(response);
			}
			
		};
		addTask(task);
	}
	
	public void getFeedBacks(final Context context, final long userId, final String poiId) {
		Runnable task = new Runnable(){

			@Override
			public void run() {
				GetFeedbackResquest request = new GetFeedbackResquest(userId, poiId);
				GetFeedbackResponse response =  mController.getFeedBacks(request);
				mCallbackListener.onFinishedgetFeedBacks(response);
			}
			
		};
		addTask(task);
	}
	
	
	public void getMyPoiMessages(final Context context, final long userId, int messageType) {
		Runnable task = new Runnable(){

			@Override
			public void run() {
				GetMyPoiMessageRequest request = new GetMyPoiMessageRequest();
				GetMyPoiMessageResponse response =  mController.getMyPoiMessages(request);
				mCallbackListener.onFinishedgetMyPoiMessages(response);
			}
			
		};
		addTask(task);
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
		public void onFinishedsubscribeUserInterest(ResponseBase response) {
			// TODO Auto-generated method stub
			BangbangEvent event = new BangbangEvent(BangbangEvent.EVENT_ID.ACTION_SUBSCRIBE_USER_INTEREST);
			event.setReturnCode(response.getReturnCode());
			event.setMessage(response.getMessage());
			EventBus.getDefault().post(event);
		}

		@Override
		public void onFinishedsearchPoiMessages(PoiQueryResponse response) {
			// TODO Auto-generated method stub
			BangbangEvent event = new BangbangEvent(BangbangEvent.EVENT_ID.ACTION_SEARCH_POI_MSG);
			event.setReturnCode(response.getReturnCode());
			event.setMessage(response.getMessage());
			Bundle bundle = new Bundle();
			bundle.putParcelableArrayList("messages", (ArrayList<? extends Parcelable>) response.getmMessages());
			event.setData(bundle);
			EventBus.getDefault().post(event);
		}

		@Override
		public void onFinishedsendFeedback(SendFeedBackResponse response) {
			// TODO Auto-generated method stub
			BangbangEvent event = new BangbangEvent(BangbangEvent.EVENT_ID.ACTION_SEND_FEEDBACK_RESPONSE);
			event.setReturnCode(response.getReturnCode());
			event.setMessage(response.getMessage());
			EventBus.getDefault().post(event);
		}

		@Override
		public void onFinishedgetFeedBacks(GetFeedbackResponse response) {
			// TODO Auto-generated method stub
			BangbangEvent event = new BangbangEvent(BangbangEvent.EVENT_ID.ACTION_GET_FEEDBACKS);
			event.setReturnCode(response.getReturnCode());
			event.setMessage(response.getMessage());
			Bundle bundle = new Bundle();
			bundle.putParcelableArrayList("messages", (ArrayList<? extends Parcelable>) response.getMessages());
			event.setData(bundle);
			EventBus.getDefault().post(event);
		}

		@Override
		public void onFinishedgetMyPoiMessages(GetMyPoiMessageResponse response) {
			// TODO Auto-generated method stub
			BangbangEvent event = new BangbangEvent(BangbangEvent.EVENT_ID.ACTION_SEARCH_POI_MSG);
			event.setReturnCode(response.getReturnCode());
			event.setMessage(response.getMessage());
			Bundle bundle = new Bundle();
			bundle.putParcelableArrayList("messages", (ArrayList<? extends Parcelable>) response.getMessages());
			event.setData(bundle);
			EventBus.getDefault().post(event);
		}

		@Override
		public void onFinishedSaveUserInfo(UpdateUserInfoResponse response) {
			//miaolong
			System.out.println("onFinishedSaveUserInfo"+response.toString());
			BangbangEvent event = new BangbangEvent(BangbangEvent.EVENT_ID.ACTION_UPDATE_USER_INFO);
			event.setReturnCode(response.getReturnCode());
			event.setMessage(response.getMessage());
			Bundle bundle = new Bundle();
			bundle.putLong("userId", response.getData());
			event.setData(bundle);
			EventBus.getDefault().post(event);
		}
		
		@Override
		public void onFinishedPublishPoiInformation(ResponseBase response) {
			// TODO Auto-generated method stub
			BangbangEvent event = new BangbangEvent(BangbangEvent.EVENT_ID.ACTION_PUBLISH_PIO_INFO);
			event.setReturnCode(response.getReturnCode());
			event.setMessage(response.getMessage());
			EventBus.getDefault().post(event);
		}
	};
	

	/**
	 * 关闭线程
	 */
	public void shutdown(){
		mCoreThread.stopThread();
		mCoreThread = null;
	}
	
	public static void main(String[] args) {
		BangbangManager manager = BangbangManager.getInstance();
		manager.updateUserInfo(null, 99999999L, "我是大神", "18612291961");
		//manager.updateUserInfo(context, userId, nickName, telphone)
		//manager.publishPoiInformation(null, 888L, "888message", "http://su.bdimg.com/static/superpage/img/logo_white.png", PoiMessage.MessageType.REQUIREMENT, "美食 宾馆", 116.403779, 39.915729, 1000, 24*60);
	}
}
