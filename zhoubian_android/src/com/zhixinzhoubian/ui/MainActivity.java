package com.zhixinzhoubian.ui;



import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingActivityHelper;
import com.slidingmenu.lib.app.SlidingFragmentActivity;
import com.zhixinzhoubian.manager.BangbangEvent;

import de.greenrobot.event.EventBus;

public class MainActivity extends SlidingFragmentActivity
		implements
			SlidingMenu.OnClosedListener,
			SlidingMenu.OnOpenedListener,
			SlidingMenu.OnPageScrolledListener {

	private static final String TAG = "MainActivity";
	
	private SlidingActivityHelper mHelper;

	private EventBus eventBus = EventBus.getDefault();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mHelper = new SlidingActivityHelper(this);
		mHelper.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		setupViews();
		getSlidingMenu().setOnOpenedListener(this);
		getSlidingMenu().setOnClosedListener(this);
		
		eventBus.register(this);
	}

	private void setupViews() {
		// customize the SlidingMenu
		SlidingMenu sm = getSlidingMenu();
		sm.setOnClosedListener(this);
		sm.setOnPageScrolledListener(this);
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.menu_shadow);
		sm.setBehindOffsetRes(R.dimen.menu_behind_offset);
		// sm.setFadeDegree(0.7f);
		sm.setFadeEnabled(false);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		sm.setTouchModeBehind(SlidingMenu.TOUCHMODE_MARGIN);
		sm.setBehindScrollScale(0);

		setSlidingActionBarEnabled(false);
	}

	@Override
	public void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mHelper.onPostCreate(savedInstanceState);
	}

	@Override
	public View findViewById(int id) {
		View v = super.findViewById(id);
		if (v != null)
			return v;
		return mHelper.findViewById(id);
	}

	@Override
	public void setContentView(int id) {
		setContentView(getLayoutInflater().inflate(id, null));
	}

	@Override
	public void setContentView(View v) {
		setContentView(v, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	}

	@Override
	public void setContentView(View v, LayoutParams params) {
		super.setContentView(v, params);
		mHelper.registerAboveContentView(v, params);
	}

	public void setBehindContentView(int id) {
		setBehindContentView(getLayoutInflater().inflate(id, null));
	}

	public void setBehindContentView(View v) {
		setBehindContentView(v, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	}

	public void setBehindContentView(View v, LayoutParams params) {
		mHelper.setBehindContentView(v, params);
	}

	public SlidingMenu getSlidingMenu() {
		return mHelper.getSlidingMenu();
	}

	public void toggle() {
		mHelper.toggle();
	}

	public void showAbove() {
		mHelper.showAbove();
	}

	public void showBehind() {
		mHelper.showBehind();
	}

	public void setSlidingActionBarEnabled(boolean b) {
		mHelper.setSlidingActionBarEnabled(b);
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		boolean b = mHelper.onKeyUp(keyCode, event);
		if (b)
			return b;
		return super.onKeyUp(keyCode, event);
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

	}

	@Override
	public void onOpened() {

	}

	@Override
	public void onClosed() {

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		eventBus.unregister(this);
	}

	 public void onEventMainThread(BangbangEvent event) {
		 
		 switch(event.getId()){
			 
			 case BangbangEvent.EVENT_ID.ACTION_UPDATE_USER_INFO:
				 
				 Log.i(TAG, "event in activity:"+event);
				 break;
				 
		 }
		 
		 
	 }
	
}
