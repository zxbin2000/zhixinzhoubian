package baidu.hackathon.zhoubian;

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
import com.zhixinzhoubian.ui.R;

public class MainActivity extends Activity {

	private MapView mMapView = null;
	private MapController mMapController = null;
	MKMapViewListener mMapListener = null;
	private Button mBtnPoiSearch = null;
	private Button mBtnLocation = null;
	MyLocationOverlay myLocationOverlay = null;
	public LocationClient mLocationClient = null;
	LocationData locData = null;
	public MyLocationListener myListener = new MyLocationListener();
	boolean isFirstLoc = true;//是否首次定位
	private SensorManager sensorManager;
	private Sensor mAccelerometer;
	private Sensor mMageticField;
	private MySensorEventListener mySensorEventListener = new MySensorEventListener();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ZbApplication app = (ZbApplication)this.getApplication();
        if (app.mBMapManager == null) {
            app.mBMapManager = new BMapManager(this);
            app.mBMapManager.init(ZbApplication.strKey,new ZbApplication.MyGeneralListener());
        }
		
		setContentView(R.layout.activity_main);
		
		mMapView = (MapView)findViewById(R.id.bmapView);
		mBtnLocation = (Button)findViewById(R.id.btnLocation);
		mBtnPoiSearch = (Button)findViewById(R.id.btnPoiSearch);
		mBtnLocation.setOnClickListener(onClickListener);
		mBtnPoiSearch.setOnClickListener(onClickListener);
		
        mMapController = mMapView.getController();
        mMapController.enableClick(true);
        mMapController.setZoom(12);
        GeoPoint p ;
        double cLat = 39.945 ;
        double cLon = 116.404 ;
        Intent  intent = getIntent();
        if ( intent.hasExtra("x") && intent.hasExtra("y") ){
        	//当用intent参数时，设置中心点为指定点
        	Bundle b = intent.getExtras();
        	p = new GeoPoint(b.getInt("y"), b.getInt("x"));
        }else{
        	//设置中心点为天安门
        	 p = new GeoPoint((int)(cLat * 1E6), (int)(cLon * 1E6));
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
				 * 
				 */
				String title = "";
				if (mapPoiInfo != null){
					title = mapPoiInfo.strText;
					Toast.makeText(MainActivity.this, title, Toast.LENGTH_SHORT).show();
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
				Toast.makeText(MainActivity.this, "地图加载完成", Toast.LENGTH_SHORT).show();
			}
		};
		
		mMapView.regMapViewListener(ZbApplication.getInstance().mBMapManager, mMapListener);
		
		/**
		 * 地图定位SDK
		 */
		mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
		locData = new LocationData();
	    mLocationClient.registerLocationListener( myListener );    		   //注册监听函数
	    LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);			//打开gps
        option.setCoorType("bd09ll");     	//设置坐标类型
        option.setScanSpan(1000);
        mLocationClient.setLocOption(option);
        mLocationClient.start();
        
		myLocationOverlay = new MyLocationOverlay(mMapView);
		myLocationOverlay.enableCompass();
	    myLocationOverlay.setData(locData);
		mMapView.getOverlays().add(myLocationOverlay);
		mMapView.refresh();
		
		/**
		 * 传感器注册
		 */
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mMageticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		
		CloudManager.getInstance().init(cloudListener);
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
	            CloudOverlay poiOverlay = new CloudOverlay(MainActivity.this, mMapView);
	            poiOverlay.setData(result.poiList);
	            mMapView.getOverlays().clear();
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
				NearbySearchInfo info = new NearbySearchInfo();
				info.ak = "D9ace96891048231e8777291cda45ca0";
				info.geoTableId = 32038;
				info.filter="time:20130801,20130810";
				info.location = "116.403689,39.914957";
				info.radius = 30000;
				CloudManager.getInstance().nearbySearch(info);
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
		sensorManager.unregisterListener(mySensorEventListener);
		mMapView.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		mMapView.onResume();
		super.onResume();
	    sensorManager.registerListener(mySensorEventListener, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
	    sensorManager.registerListener(mySensorEventListener, mMageticField, SensorManager.SENSOR_DELAY_NORMAL);
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
            locData.latitude = location.getLatitude();
            locData.longitude = location.getLongitude();
            locData.accuracy = location.getRadius();
            // 此处可以设置 locData的方向信息, 如果定位 SDK 未返回方向信息，用户可以自己实现罗盘功能添加方向信息。
            locData.direction = location.getDerect();
            //更新定位数据
            myLocationOverlay.setData(locData);
            //更新图层数据执行刷新后生效
            mMapView.refresh();
            //是手动触发请求或首次定位时，移动到定位点
            if (isFirstLoc){
            	//移动地图到定位点
                mMapController.animateTo(new GeoPoint((int)(locData.latitude* 1e6), (int)(locData.longitude *  1e6)));
            }
            //首次定位完成
            isFirstLoc = false;
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
			    if(Math.abs(lastAzimuth - azimuth) > 2) {
			    	locData.direction = lastAzimuth = azimuth;
				    myLocationOverlay.setData(locData);
		            mMapView.refresh();
			    } else {
			    	locData.direction = lastAzimuth;
				    myLocationOverlay.setData(locData);
		            mMapView.refresh();
			    }
			}
//			SensorManager.getRotationMatrix(R, null, accelerometerValues, magneticFieldValues);
//			SensorManager.getOrientation(R, values);
//			values[0] = (float) Math.toDegrees(values[0]);
//			Log.i("baidu", values[0]+"");
		}
    	
    }
    
    class CloudOverlay extends ItemizedOverlay {

        List<CloudPoiInfo> mLbsPoints;
        Activity mContext;
        
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