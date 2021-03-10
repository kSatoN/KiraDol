package ksato.kiradol.views;

import ksato.kiradol.controllers.BaseActivity;


public class Devices
{
	private int deviceType;	// 0：実験のNECの。1：Nexus 5
	private int deviceWidth;
	private int deviceHeight;
	private float scaleX;
	private float scaleY;
	private double dpi;
	private double textScale;
	public int getDeviceType( )
	{
		return deviceType;
	}
	public double getDpi( )
	{
		return dpi;
	}
	public double getTextScale( )
	{
		return textScale;
	}
	
	public Devices(BaseView baseView)
	{
		deviceWidth = baseView.deviceWindowWidth;
		deviceHeight = baseView.deviceWindowHeight;
		System.out.println("ディスプレイの幅は " + deviceWidth + " 物理ピクセルです。");
		scaleX = baseView.windowScaleX;
		scaleY = baseView.windowScaleY;
		dpi = baseView.dpi;
		detectDeviceType( );
		detectTextScale( );
		return;
	}
	
	public Devices(BaseActivity baseActivity)
	{
		deviceWidth = baseActivity.getDeviceWindowWidth( );
		deviceHeight = baseActivity.getDeviceWindowHeight( );
		scaleX = baseActivity.getDeviceWindowWidth( ) / 1920;
		scaleY = baseActivity.getDeviceWindowHeight( ) / 1080;
		dpi = baseActivity.getDpi( );
		detectDeviceType( );
		detectTextScale( );
		return;
	}
	
	private void detectDeviceType( )
	{
		if(0.65f < scaleY && scaleY < 0.68f)
		{
			deviceType = 0;
		}
		else if(0.98f < scaleY && scaleY < 1.02f)
		{
			deviceType = 1;
		}
		return;
	}
	
	private void detectTextScale( )
	{
		System.out.printf("このデバイスの画素密度は %.3f dpiです。\n", dpi);
		switch(deviceType)
		{
			case 0:
				System.out.println("これは実験単位です。");
				textScale = (442.89777319376986 / dpi) / 1.6;
				break;
			case 1:
				System.out.println("これはNexus 5です。");
				textScale = 1.00;
				break;
		}
		return;
	}
	
	public int convertModelXToPhysicalX(int modelX)
	{
		return (int) ( (double) modelX * scaleX);
	}
	
	public int convertModelYToPhysicalY(int modelY)
	{
		return (int) ( (double) modelY * scaleY);
	}
	
}
