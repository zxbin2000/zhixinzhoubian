package com.zhixinzhoubian.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;
import com.zhixinzhoubian.manager.BangbangEvent;

public class MainActivity extends SlidingFragmentActivity
		implements
			SlidingMenu.OnClosedListener,
			SlidingMenu.OnOpenedListener,
			SlidingMenu.OnPageScrolledListener {

	private static final String TAG = "MainActivity";

	private LeftFragment mLeftFragment;
	private TestFragment mTestFragment;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_frame);
		setupViews();

		// init fragment
		mLeftFragment = new LeftFragment();
		mTestFragment = new TestFragment();

		getSlidingMenu().setOnOpenedListener(this);
		getSlidingMenu().setOnClosedListener(this);

		setBehindContentView(R.layout.left_fragment);
		FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
		t.replace(R.id.menu_frame, mLeftFragment);
		t.commit();

		FragmentTransaction t2 = getSupportFragmentManager().beginTransaction();
		t2.setCustomAnimations(R.anim.in_from_right, R.anim.out_to_left);
		t2.replace(R.id.content_frame, mTestFragment).commitAllowingStateLoss();

	}

	public void onEventMainThread(BangbangEvent event) {

		switch (event.getId()) {

			case BangbangEvent.EVENT_ID.ACTION_UPDATE_USER_INFO :

				Log.i(TAG, "event in activity:" + event);
				break;

		}

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
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

	}

	@Override
	public void onOpened() {

	}

	@Override
	public void onClosed() {

	}

}
