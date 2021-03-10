package ksato.kiradol.controllers;

import android.content.Intent;

import ksato.kiradol.R;


public class ActivityExplanationMiyu extends ActivityExplanation
{
	@Override	// from: -initialize -onCreate
	protected void setFactories( )
	{
		stringFactory = new ksato.kiradol.views.StringFactory(this, 0);
		soundFactory = new SoundFactoryExplanation(this, 0);
		return;
	}
	
	@Override	// from: -initialize -onCreate
	protected void playBgm( )
	{
		mediaPlayerBgm.allRelease( );
		mediaPlayerBgm.buildMediaPlayer( );
		mediaPlayerBgm.loadMediaPlayer(R.raw.bgm_yasashisawamahonoyoni);
		mediaPlayerBgm.loopMediaPlayer( );
		return;
	}
	
	@Override
	protected void setImageType( )
	{
		imageType = -1;	// 美夢ちゃん
		return;
	}
	
	@Override	// from: -nextString -onClick -StringOnClickListener
	protected void goToNextStage( )
	{
		Intent intentE1 = new Intent(getApplicationContext( ), ActivityExplanationPri.class);
		mediaPlayerBgm.allRelease( );
		startActivity(intentE1);
		this.finish( );
		return;
	}
	
}
