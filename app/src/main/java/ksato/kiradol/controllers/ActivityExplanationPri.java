package ksato.kiradol.controllers;

import android.content.Intent;

import ksato.kiradol.R;


public class ActivityExplanationPri extends ActivityExplanation
{
	private int soundBubu;
	
	@Override	// from: -initialize -onCreate
	protected void setFactories( )
	{
		stringFactory = new ksato.kiradol.views.StringFactory(this, 1);
		soundFactory = new SoundFactoryExplanation(this, 1);
		return;
	}
	
	@Override	// from: -initialize -onCreate
	protected void playBgm( )
	{
		mediaPlayerBgm.allRelease( );
		mediaPlayerBgm.buildMediaPlayer( );
		mediaPlayerBgm.loadMediaPlayer(R.raw.bubu);
		mediaPlayerBgm.playSyncMediaPlayer( );
		mediaPlayerBgm.loadMediaPlayer(R.raw.bgm_rule);
		mediaPlayerBgm.loopMediaPlayer( );
		return;
	}
	
	@Override
	protected void setImageType( )
	{
		imageType = soundFactory.getTypeOfSounds( );
		return;
	}
	
	@Override	// from: -nextString -onClick -StringOnClickListener
	protected void goToNextStage( )
	{
		Intent intentE2 = new Intent(getApplicationContext( ), ActivityMain1.class);
		mediaPlayerBgm.allRelease( );
		startActivity(intentE2);
		this.finish( );
		return;
	}
	
}
