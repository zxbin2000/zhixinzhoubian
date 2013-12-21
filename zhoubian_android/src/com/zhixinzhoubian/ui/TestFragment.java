package com.zhixinzhoubian.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class TestFragment extends AbsFragment {

	
	private MainActivity mActivity;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		mActivity = (MainActivity)getActivity();
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mViewGroup = (ViewGroup) inflater.inflate(R.layout.test_activity, container);

		Button button = (Button) mViewGroup.findViewById(R.id.slid);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO
				mActivity.getSlidingMenu().showBehind();
			}
		});

		return super.onCreateView(inflater, container, savedInstanceState);
	}

	

}
