package ksato.kiradol.controllers;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class ActivityMini extends BaseActivity
{
	protected ksato.kiradol.views.StageViewMini stageViewMini;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		orientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE;
		gravityEnabled = true;
		super.onCreate(savedInstanceState);
		initialize( );
	}
	
	private void initialize( )
	{
		createViews( );
		setEventListeners( );
		return;
	}
	
	private void createViews( )
	{
		stageViewMini = new ksato.kiradol.views.StageViewMini(this);
		return;
	}
	
	private void setEventListeners( )
	{
		
		return;
	}
	
	
	private class ButtonSelectOkOnClickListener implements View.OnClickListener
	{
		@Override
		public void onClick(View view)
		{
			//startMiniGame( );
			return;
		}
	}
	
	
}
