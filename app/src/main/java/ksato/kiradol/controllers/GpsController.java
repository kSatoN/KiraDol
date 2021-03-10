package ksato.kiradol.controllers;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * Created by fyoshida on 2018/07/04.
 * Modified by ksato.
 */

public class GpsController implements LocationListener
{
	public int status;
	public double latitude;
	public double longitude;
	public double altitude;
	public float accuracy;
	public float time;
	public float speed;
	public float bearing;
	
	LocationManager locationManager;
	
	public GpsController(LocationManager locationManager)
	{
		this.locationManager=locationManager;
	}
	
	public void start( )
	{
		if(locationManager != null)
		{
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,0,this);
		}
	}
	
	public void stop( )
	{
		if(locationManager != null)
		{
			locationManager.removeUpdates(this);
		}
	}
	@Override
	public void onLocationChanged(Location location)
	{
		latitude=location.getLatitude( );
		longitude=location.getLongitude( );
		accuracy=location.getAccuracy( );
		altitude=location.getAltitude( );
		time=location.getTime( );
		speed=location.getSpeed( );
		bearing=location.getBearing( );
	}
	
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras)
	{
		this.status=status;
	}
	
	@Override
	public void onProviderEnabled(String provider)
	{
	
	}
	
	@Override
	public void onProviderDisabled(String provider)
	{
	
	}
	
}
