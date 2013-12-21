package com.zhixinzhoubian.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * 左侧的
 * @author guojialin
 *
 */
public class LeftFragment extends AbsFragment{

	private TextView tvNickName = null;
	private ImageView ivEdit = null;
	private TextView tvFrequency = null;
	private TextView tvDistance = null;
	private SeekBar sbFrequency = null;
	private SeekBar sbDistance = null;

	private Button btnMyRequirement = null;
	private Button btnMyService = null;
	
	
	public LeftFragment() {
		super();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		 mViewGroup  = (ViewGroup)(getActivity().getLayoutInflater()).inflate(R.layout.left_fragment, container);
         setupViews();
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	private void setupViews() {
		tvNickName = (TextView) mViewGroup.findViewById(R.id.nickName);
		ivEdit = (ImageView) mViewGroup.findViewById(R.id.eidtProfile);
		
		tvDistance = (TextView) mViewGroup.findViewById(R.id.distance_label);
		sbDistance = (SeekBar) mViewGroup.findViewById(R.id.distance_seek);
		tvFrequency = (TextView) mViewGroup.findViewById(R.id.frequncy_label);
		sbDistance = (SeekBar) mViewGroup.findViewById(R.id.distance_seek);
		
		
	}
}
