package com.zhixinzhoubian.ui;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.cloud.CloudListener;
import com.baidu.mapapi.cloud.CloudManager;
import com.baidu.mapapi.cloud.CloudPoiInfo;
import com.baidu.mapapi.cloud.CloudSearchResult;
import com.baidu.mapapi.cloud.DetailSearchResult;
import com.baidu.mapapi.cloud.NearbySearchInfo;
import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.LocationData;
import com.baidu.mapapi.map.MKMapViewListener;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationOverlay;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.zhixinzhoubian.manager.BangbangManager;

import de.greenrobot.event.EventBus;

public class MapActivity extends Activity {

	private MapView mMapView = null;
	private MapController mMapController = null;
	private MKMapViewListener mMapListener = null;
	private Button mBtnPoiSearch = null;
	private Button mBtnLocation = null;
	
	private double mDefLat = 40.057132;
	private double mDefLng = 116.307347;
	private String CoorType = "bd09ll";
	private int ScanSpan = 1000;
	
	boolean isFirstLoc = true;				//是否首次定位
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private Sensor mMageticField;
	private MySensorEventListener mSensorEventListener = new MySensorEventListener();
	
	LocationData mLocationData = null;
	LocationClient mLocationClient = null;
	MyLocationOverlay mLocationOverlay = null;
	public MyLocationListener mLocationListener = new MyLocationListener();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ZbApplication app = (ZbApplication)this.getApplication();
        if (app.mBMapManager == null) {
            app.mBMapManager = new BMapManager(this);
            app.mBMapManager.init(ZbApplication.strKey, new ZbApplication.MyGeneralListener());
        }
		
		setContentView(R.layout.activity_main);
		
		mMapView = (MapView)findViewById(R.id.bmapView);
		mBtnLocation = (Button)findViewById(R.id.btnLocation);
		mBtnPoiSearch = (Button)findViewById(R.id.btnPoiSearch);
		mBtnLocation.setOnClickListener(onClickListener);
		mBtnPoiSearch.setOnClickListener(onClickListener);
		
        mMapController = mMapView.getController();
        mMapController.enableClick(true);
        mMapController.setZoom(14);
        GeoPoint p ;
        Intent  intent = getIntent();
        if ( intent.hasExtra("x") && intent.hasExtra("y") ){
        	//当用intent参数时，设置中心点为指定点
        	Bundle b = intent.getExtras();
        	p = new GeoPoint(b.getInt("y"), b.getInt("x"));
        }else{
        	//设置默认中心点为百度大厦
        	 p = new GeoPoint((int)(mDefLat * 1E6), (int)(mDefLng * 1E6));
        }
        mMapController.setCenter(p);
        
        /**
    	 *  MapView的生命周期与Activity同步，当activity挂起时需调用MapView.onPause()
    	 */
        mMapListener = new MKMapViewListener() {
			@Override
			public void onMapMoveFinish() {
				/**
				 * 在此处理地图移动完成回调
				 * 缩放，平移等操作完成后，此回调被触发
				 */
			}
			
			@Override
			public void onClickMapPoi(MapPoi mapPoiInfo) {
				/**
				 * 在此处理底图poi点击事件
				 * 显示底图poi名称并移动至该点
				 * 设置过： mMapController.enableClick(true); 时，此回调才能被触发
				 */
				String title = "";
				if (mapPoiInfo != null){
					title = mapPoiInfo.strText;
					Toast.makeText(MapActivity.this, title, Toast.LENGTH_SHORT).show();
					mMapController.animateTo(mapPoiInfo.geoPt);
				}
			}

			@Override
			public void onGetCurrentMap(Bitmap b) {
				/**
				 *  当调用过 mMapView.getCurrentMap()后，此回调会被触发
				 *  可在此保存截图至存储设备
				 */
			}

			@Override
			public void onMapAnimationFinish() {
				/**
				 *  地图完成带动画的操作（如: animationTo()）后，此回调被触发
				 */
				
			}
			
            /**
             * 在此处理地图载完成事件 
             */
			@Override
			public void onMapLoadFinish() {
				Toast.makeText(MapActivity.this, "地图加载完成", Toast.LENGTH_SHORT).show();
			}
		};
		
		mMapView.regMapViewListener(ZbApplication.getInstance().mBMapManager, mMapListener);
		
		/**
		 * 地图定位SDK
		 */
		mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
		mLocationData = new LocationData();
	    mLocationClient.registerLocationListener(mLocationListener);       //注册监听函数
	    LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);			//打开gps
        option.setCoorType(CoorType);     	//设置坐标类型
        option.setScanSpan(ScanSpan);
        mLocationClient.setLocOption(option);
        mLocationClient.start();
        
		mLocationOverlay = new MyLocationOverlay(mMapView);
	    mLocationOverlay.setData(mLocationData);
		mMapView.getOverlays().add(mLocationOverlay);
		mLocationOverlay.enableCompass();
		mMapView.refresh();
		
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mMageticField = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		
		EventBus.getDefault().register(this);
	}
	
	/**
	 * 云检索监听方法
	 */
	private CloudListener cloudListener = new CloudListener() {

		@Override
		public void onGetDetailSearchResult(DetailSearchResult arg0, int arg1) {
			
		}

		@Override
		public void onGetSearchResult(CloudSearchResult result, int error) {
			if (result != null && result.poiList!= null && result.poiList.size() > 0) {
	            CloudOverlay poiOverlay = new CloudOverlay(MapActivity.this, mMapView);
	            poiOverlay.setData(result.poiList);
	            //mMapView.getOverlays().clear();
	            mMapView.getOverlays().add(poiOverlay);
	            mMapView.refresh();
	            mMapView.getController().animateTo(new GeoPoint((int)(result.poiList.get(0).latitude * 1e6), (int)(result.poiList.get(0).longitude * 1e6)));
	        }
		}
	};
	
	/**
	 * 按钮监听方法
	 */
	private OnClickListener onClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch(v.getId()) {
			case R.id.btnLocation:
				mLocationClient.requestLocation();
				break;
			case R.id.btnPoiSearch:
				long userId = 11L;
				int distance = 1000;
				int[] tags = {1,2};
				BangbangManager manager = BangbangManager.getInstance();
				manager.searchPoiMessages(MapActivity.this, 11L, mDefLng, mDefLat, distance, tags);
				break;
			}
		}
	};
	
	@Override
	protected void onDestroy() {
		mMapView.destroy();
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		mSensorManager.unregisterListener(mSensorEventListener);
		mMapView.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		mMapView.onResume();
		super.onResume();
	    mSensorManager.registerListener(mSensorEventListener, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
	    mSensorManager.registerListener(mSensorEventListener, mMageticField, SensorManager.SENSOR_DELAY_NORMAL);
	}
	
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    	super.onSaveInstanceState(outState);
    	mMapView.onSaveInstanceState(outState);
    }
    
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
    	super.onRestoreInstanceState(savedInstanceState);
    	mMapView.onRestoreInstanceState(savedInstanceState);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**
     * 定位SDK监听函数
     */
    public class MyLocationListener implements BDLocationListener {
    	
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null)
                return ;
            mLocationData.latitude = location.getLatitude();
            mLocationData.longitude = location.getLongitude();
            mLocationData.accuracy = location.getRadius();
            if (location.getDerect() > 0) {
            	// 此处可以设置 locData的方向信息, 如果定位 SDK 未返回方向信息，用户可以自己实现罗盘功能添加方向信息。
            	mLocationData.direction = location.getDerect();
            }
            mLocationOverlay.setData(mLocationData);		//更新定位数据
            mMapView.refresh();								//更新图层数据执行刷新后生效
            if (isFirstLoc){								//是手动触发请求或首次定位时，移动到定位点
                mMapController.animateTo(new GeoPoint((int)(mLocationData.latitude* 1e6), (int)(mLocationData.longitude *  1e6)));
            }
            isFirstLoc = false;								//首次定位完成
        }
        
        public void onReceivePoi(BDLocation poiLocation) {
            if (poiLocation == null){
                return ;
            }
        }
    }

    public class MySensorEventListener implements SensorEventListener{
        float[] inR = new float[16];
        float[] outR = new float[16];
        float[] I = new float[16];
        float[] gravity = new float[3];
        float[] magneticFieldValues = new float[3];
        float[] accelerometerValues = new float[3];
        float lastAzimuth = 0l;
        
        final float pi = (float) Math.PI;
        final float rad2deg = 180/pi;
    	
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			
		}

		@Override
		public void onSensorChanged(SensorEvent event) {
			if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
				magneticFieldValues = event.values.clone();
			} else if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
				gravity = event.values.clone();
			}
			calculateOrientation();
		}
		
		private void calculateOrientation() {
			boolean success = SensorManager.getRotationMatrix(inR, I, gravity, magneticFieldValues);
			if(success) {
				SensorManager.remapCoordinateSystem(inR, SensorManager.AXIS_X, SensorManager.AXIS_Z, outR);
				SensorManager.getOrientation(outR, accelerometerValues);
			    float azimuth = accelerometerValues[0] * rad2deg;
			    float pitch = accelerometerValues[1] * rad2deg;
			    float roll = accelerometerValues[2] * rad2deg;
			    if(lastAzimuth == 0l) {
			    	lastAzimuth = azimuth;
			    }
			    Log.e("hackathon", azimuth + ";" + pitch + ";" + roll);
			    if(Math.abs(lastAzimuth - azimuth) > 0.6) {
			    	mLocationData.direction = lastAzimuth = azimuth;
				    mLocationOverlay.setData(mLocationData);
		            mMapView.refresh();
			    } else {
			    	mLocationData.direction = lastAzimuth;
				    mLocationOverlay.setData(mLocationData);
		            mMapView.refresh();
			    }
			}
		}
    	
    }
    
    class CloudOverlay extends ItemizedOverlay {
        private List<CloudPoiInfo> mLbsPoints;
        private Activity mContext;
        
        public CloudOverlay(Activity context ,MapView mMapView) {
            super(null,mMapView);
            mContext = context;
        }

        public void setData(List<CloudPoiInfo> lbsPoints) {
            if (lbsPoints != null) {
                mLbsPoints = lbsPoints;
            }
            for ( CloudPoiInfo rec : mLbsPoints ){
                GeoPoint pt = new GeoPoint((int)(rec.latitude * 1e6), (int)(rec.longitude * 1e6));
                OverlayItem item = new OverlayItem(pt , rec.title, rec.address);
                Drawable marker1 = this.mContext.getResources().getDrawable(R.drawable.icon_marka);
                item.setMarker(marker1);
                addItem(item);
            }
        }
        
        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
        
        @Override
        protected boolean onTap(int arg0) {
            CloudPoiInfo item = mLbsPoints.get(arg0);
            Toast.makeText(mContext, item.title,Toast.LENGTH_LONG).show();
            return super.onTap(arg0);
        }
        
    }
}