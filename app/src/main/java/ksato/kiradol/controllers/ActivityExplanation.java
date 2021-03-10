package ksato.kiradol.controllers;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import ksato.kiradol.R;


abstract class ActivityExplanation extends BaseActivity
{
	protected int imageType;
	// ビュー
	private ksato.kiradol.views.StageViewExplanation stageViewExplanation;
	protected ksato.kiradol.views.StringFactory stringFactory;
	private MediaPlayers mediaPlayerVoice;
	protected MediaPlayers mediaPlayerBgm;
	private ImageView imageViewStringNext;
	// コントローラー
	protected SoundFactoryExplanation soundFactory;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		orientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE;
		gravityEnabled = false;
		super.onCreate(savedInstanceState);
		initialize( );
		return;
	}
	
	// 初期化処理
	private void initialize( )
	{
		stageViewExplanation = new ksato.kiradol.views.StageViewExplanation(this);
		mediaPlayerVoice = newMediaPlayers( );
		mediaPlayerBgm = newMediaPlayers( );
		setFactories( );
		imageViewStringNext = stageViewExplanation.getImageViewStringNext( );
		imageViewStringNext.setOnClickListener(new StringOnClickListener( ));
		displayFirstString( );
		playBgm( );
		playFirstVoice( );
		setImageType();
		stageViewExplanation.drawImage(imageType);
		return;
	}
	
	protected abstract void setFactories( );
	
	private void displayFirstString( )
	{
		stringFactory.changeStrings( );
		stageViewExplanation.drawString(stringFactory.getReturnString( ));
		return;
	}
	
	protected abstract void playBgm( );
	
	private void playFirstVoice( )
	{
		soundFactory.changeSounds( );
		mediaManagement( );
		return;
	}
	
	protected abstract void setImageType( );
	
	// イベントリスナー
	private void nextString( )
	{
		boolean needChangeText = stringFactory.changeStrings( ) && soundFactory.changeSounds( );
		if(!needChangeText)
		{
			mediaPlayerVoice.allRelease( );
			goToNextStage( );
		}
		else
		{
			setImageType( );
			stageViewExplanation.drawImage(imageType);
			stageViewExplanation.drawString(stringFactory.getReturnString( ));
			mediaManagement( );
		}
		return;
	}
	
	protected abstract void goToNextStage( );
	
	private void mediaManagement( )
	{
		mediaPlayerVoice.allRelease( );
		mediaPlayerVoice.buildMediaPlayer( );
		mediaPlayerVoice.loadMediaPlayer(soundFactory.getReturnSound( ));
		mediaPlayerVoice.setMediaPlayerOnlyOnce(true);
		mediaPlayerVoice.playMediaPlayer( );
		return;
	}
	
	
	// インナークラス
	private class StringOnClickListener implements View.OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			nextString( );
			return;
		}
	}
	
}
