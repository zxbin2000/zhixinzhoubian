package com.zhixinzhoubian.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.zhixinzhoubian.manager.BangbangEvent;
import com.zhixinzhoubian.manager.BangbangManager;
import com.zhixinzhoubian.model.PoiMessage;

import de.greenrobot.event.EventBus;

public class RegisterActivity extends Activity {

	private static final String TAG = "RegisterActivity";
	
	private EditText etNickName = null;
	private EditText etTelephone = null;
	
	private Button btnSubmit = null;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*
		setContentView(R.layout.register_activity);
		EventBus.getDefault().register(this);

		etNickName = (EditText) findViewById(R.id.nickName);
		etTelephone = (EditText) findViewById(R.id.telephone);
		btnSubmit = (Button) findViewById(R.id.submit);
		
		btnSubmit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				BangbangManager.getInstance().updateUserInfo(
						RegisterActivity.this,
						-1L , 
						etNickName.getText().toString(), 
						etTelephone.getText().toString());
			}
			
		});
		*/
		BangbangManager manager = BangbangManager.getInstance();
//		manager.updateUserInfo(RegisterActivity.this, 99999999L, "我是大神", "18612291961");

		
		//		int[] tags = new int[1];
//		tags[0]=1;
//		manager.subscribeUserInterest(RegisterActivity.this, 11L, tags, 1000, 1);
		
		int[] tags = new int[1];
		tags[0]=1;
		manager.searchPoiMessages(RegisterActivity.this, 11L, 116.403725,39.91577, 1000, tags);
		
		
		//manager.publishPoiInformation(RegisterActivity.this, 11L, "我发布的第一条需求", "http://su.bdimg.com/static/superpage/img/logo_white.png", PoiMessage.MessageType.REQUIREMENT, "美食", 116.403725, 39.91577, 1000, 1536);
		
		
		
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
		
	}
	
	
	 public void onEventMainThread(BangbangEvent event) {
		 
		 switch(event.getId()){
			 
			 case BangbangEvent.EVENT_ID.ACTION_UPDATE_USER_INFO:
				 
				 Log.i(TAG, "event in activity:"+event);
				 break;
				 
		 }
		 
		 
	 }
	
}
