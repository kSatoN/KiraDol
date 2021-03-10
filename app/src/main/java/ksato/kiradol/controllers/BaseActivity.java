package ksato.kiradol.controllers;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;


public class BaseActivity extends AppCompatActivity /*implements Runnable*/
{
	// スレッド処理用
	private Handler handler = new Handler( );
	private long startTime = 0;
	private Timer timer;
	private TimerTask timerTask;
	
	// デバイスの表示領域
	private int deviceWindowWidth;
	private int deviceWindowHeight;
	private double dpi;	// 自作
	
	// センサー制御用コントローラ（あえてパッケージプライベート）
	AccelerationController accelerationController;
	GpsController gpsController;
	TouchController touchController;
	
	// センサー設定（あえてパッケージプライベート）
	boolean gravityEnabled = false;
	boolean gpsEnabled = false;
	int orientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE;
	
	// 音楽設定
	private LinkedList<MediaPlayers> mediaPlayers;
	
	//=========================
	//  ライフサイクル用関数
	//=========================
	@Override	// 追記
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		fullScreen( );
		mediaPlayers = new LinkedList<MediaPlayers>( );
		initializeWindow( );
		initializeAccelerationController( );
		initializeGpsController( );
		initializeOrientation( );
		initializeTouchController( );
	}
	
	// 自作
	private void fullScreen( )
	{
		// ステータスバーを非表示
		getWindow( ).addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// タイトルを非表示
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// ナビゲーションバーを非表示
		hideNavigation( );
		return;
	}
	
	// 自作
	private void hideNavigation( )
	{
		View decorView = getWindow( ).getDecorView( );
		if(Build.VERSION.SDK_INT >= 19)
		{
			decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
		}
		else if(Build.VERSION.SDK_INT >= 16)
		{
			decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN);
		}
		return;
	}
	
	@Override	// 追記
	protected void onResume( )
	{
		super.onResume( );
		if (gravityEnabled && accelerationController != null)
		{
			accelerationController.start();
		}
		if (gpsEnabled && gpsController != null)
		{
			gpsController.start( );
		}
		for(MediaPlayers mediaPlayer: mediaPlayers)
		{
			mediaPlayer.onResume( );
		}
		//startThread( );
		//continueThread( );
		startTimer( );
		hideNavigation( );
	}
	
	@Override
	protected void onPause( )
	{
		if(gravityEnabled)
		{
			accelerationController.stop( );
		}
		if(gpsEnabled)
		{
			gpsController.stop( );
		}
		//stopThread( );
		stopTimer( );
		super.onPause( );
	}
	
	@Override
	protected void onDestroy( )
	{
		super.onDestroy( );
		stopTimer( );
		for(MediaPlayers mediaPlayer: mediaPlayers)
		{
			mediaPlayer.onDestroy( );
		}
		mediaPlayers = new LinkedList<MediaPlayers>( );
	}
	
	//=========================
	//  スレッド処理用の関数
	//=========================
	private void startTimer( )
	{
		timer = new Timer( );
		timerTask = new TimerTask( );
		timer.scheduleAtFixedRate(timerTask, 17, 17);
		return;
	}
	
	private void stopTimer( )
	{
		timer.cancel( );
		return;
	}
	
	protected void update( )
	{
		return;
	}
	
	
	private class TimerTask extends java.util.TimerTask
	{
		@Override
		public void run( )
		{
			handler.post(new Runnable( )
			{
				public void run( )
				{
					update( );
					return;
				}
			});
			return;
		}
	}
	
	
//	@Override
//	public void run( )
//	{
//		//float deltaTime = (System.nanoTime( ) - startTime) / 1000000000.0f;
//		update( );
//		continueThread( );
//	}
//
//	private void startThread( )
//	{
//		handler = new Handler( );
//		startTime = System.nanoTime( );
//	}
//
//	private void continueThread( )
//	{
//		handler.postDelayed(this, 17);
//	}
//
//	private void stopThread( )
//	{
//		handler.removeCallbacks(this);
//	}
//
//	//=========================
//	//  更新処理用の関数
//	//=========================
//	// コントローラの更新処理
//	protected void update( )
//	{
//		return;
//	}
	
	//=========================
	//  初期化用の関数
	//=========================
	// 追記
	private void initializeWindow( )
	{
		WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
		Display dp = wm.getDefaultDisplay( );
		DisplayMetrics displayMetrics = new DisplayMetrics( );
		dp.getMetrics(displayMetrics);
		deviceWindowWidth = getRealSize( ).x;
		deviceWindowHeight = getRealSize( ).y;
		dpi = Math.sqrt( (displayMetrics.xdpi * displayMetrics.ydpi));
	}
	
	// 自作
	private Point getRealSize( )
	{
		Display display = getWindowManager( ).getDefaultDisplay( );
		Point point = new Point(0, 0);
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) 	// Android 4.2以上
		{
			display.getRealSize(point);
			return point;
		}
		return point;
	}
	
	// 自作
	public double getDpi( )
	{
		return dpi;
	}
	
	protected void initializeAccelerationController( )
	{
		if(gravityEnabled)
		{
			accelerationController = new AccelerationController( (SensorManager) getSystemService(Context.SENSOR_SERVICE));
			accelerationController.start( );
		}
		else
		{
			accelerationController = null;
		}
	}
	
	protected void initializeGpsController( )
	{
		if(gpsEnabled)
		{
			gpsController = new GpsController( (LocationManager) getSystemService(Context.LOCATION_SERVICE));
		}
	}
	
	protected void initializeTouchController( )
	{
		touchController = new TouchController( );
	}
	
	protected void initializeOrientation( )
	{
		setRequestedOrientation(orientation);
	}
	
	//=========================
	//  アクセッサ
	//=========================
	public int getDeviceWindowWidth( )
	{
		return deviceWindowWidth;
	}
	
	public int getDeviceWindowHeight( )
	{
		return deviceWindowHeight;
	}
	
	// 自作
	protected MediaPlayers newMediaPlayers( )
	{
		MediaPlayers mediaPlayer = new MediaPlayers(this);
		mediaPlayers.add(mediaPlayer);
		return mediaPlayer;
	}
	
	//====================
	// findViewByIdの拡張（自作）
	// Nullのチェックをするようになる（ただしキャストは必須に）。
	//====================
	public final View viewById(@IdRes int id)
	{
		View view = findViewById(id);
		return checkNotNull(view);
	}
	
	public static <T> T checkNotNull(T reference)
	{
		if(reference == null)
		{
			throw new NullPointerException( );
		}
		return reference;
	}
	
}
