package ksato.kiradol.controllers;

import android.content.Intent;

import ksato.kiradol.models.WorldMain2;


public class ActivityMain2 extends ActivityMain
{
	@Override
	protected  void createModels( )
	{
		world = new WorldMain2(this);
		return;
	}
	
	@Override
	protected void createViews( )
	{
		stageView = new ksato.kiradol.views.StageViewMain(this);
		return;
	}
	
	@Override
	protected void goToNextStage( )
	{
		releaseMediaPlayerOfThisAndSoundFactory( );
		Intent intent22 = new Intent(getApplicationContext( ), ActivityMiniSelect.class);
		startActivity(intent22);
		this.finish( );
		return;
	}
	
}
