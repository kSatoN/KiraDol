package ksato.kiradol.controllers;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ksato.kiradol.models.FileIO;
import ksato.kiradol.models.WorldMini;


public class ActivityMiniSelect extends BaseActivity
{
	protected ksato.kiradol.models.WorldMini world;
	protected ksato.kiradol.views.StageViewMiniSelect stageViewMiniSelect;
	private Button buttonSelectOk;
	private FileIO fileIO;
	public FileIO getFileIO( )
	{
		return fileIO;
	}
	
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
		world = new WorldMini(this);
		fileIO = new FileIO(this);
		createViews( );
		setEventListeners( );
		return;
	}
	
	private void createViews( )	// 子クラスで実装
	{
		stageViewMiniSelect = new ksato.kiradol.views.StageViewMiniSelect(this, world);
		buttonSelectOk = stageViewMiniSelect.getButtonSelectOk( );
		return;
	}
	
	private void setEventListeners( )
	{
		ButtonSelectOkOnClickListener buttonSelectOkOnClickListener = new ButtonSelectOkOnClickListener( );
		buttonSelectOk.setOnClickListener(buttonSelectOkOnClickListener);
		return;
	}
	
	private void startMiniGame( )
	{
		Intent intentM0 = new Intent(getApplicationContext( ), ActivityStart0.class);
		startActivity(intentM0);
		this.finish( );
		return;
	}
	
	
	private class ButtonSelectOkOnClickListener implements View.OnClickListener
	{
		@Override
		public void onClick(View view)
		{
			startMiniGame( );
			return;
		}
	}
	
	
}
