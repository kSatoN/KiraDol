package ksato.kiradol.controllers;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;


public class AccelerationController implements android.hardware.SensorEventListener
{
	float x;
	float y;
	float z;
	private SensorManager sensorManager;
	private Sensor sensor;
	
	// センサーの初期設定
	AccelerationController(SensorManager sensorManager)
	{
		this.sensorManager = sensorManager;
		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	}
	
	void start( )
	{
		sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);
		
	}
	
	void stop( )
	{
		sensorManager.unregisterListener(this);
	}
	
	// イベントリスナー用メソッド
	@Override
	public void onSensorChanged(SensorEvent event)
	{
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
		{
			x = event.values[0];
			y = event.values[1];
			z = event.values[2];
		}
	}
	
	// イベントリスナー用メソッド
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy)
	{
		return;
	}
	
}
