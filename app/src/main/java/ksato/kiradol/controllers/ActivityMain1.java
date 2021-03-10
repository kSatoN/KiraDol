package ksato.kiradol.controllers;

import android.content.Intent;

import ksato.kiradol.models.WorldMain1;


public class ActivityMain1 extends ActivityMain
{
	@Override
	protected  void createModels( )
	{
		world = new WorldMain1(this);
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
		Intent intent12 = new Intent(getApplicationContext( ), ActivityMain2.class);
		startActivity(intent12);
		this.finish( );
		return;
	}
	
}
