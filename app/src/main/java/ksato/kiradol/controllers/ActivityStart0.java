package ksato.kiradol.controllers;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import ksato.kiradol.R;
import ksato.kiradol.models.FileIO;


public class ActivityStart0 extends BaseActivity
{
	// ビュー
	private ksato.kiradol.views.StageViewStart0 stageViewStart0;
	private ConstraintLayout constraintLayout;
	private Button buttonToExplanation;
	private Button buttonToStartGame;
	// コントローラー
	private MediaPlayers mediaPlayers;
	// 音源ファイル
	private int soundTouch;
	// その他
	private boolean canUpdate;
	private long countOfRun;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		orientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE;
		gravityEnabled = false;
		super.onCreate(savedInstanceState);
		canUpdate = true;
		initialize( );
		return;
	}
	
	// 初期化処理
	private void initialize( )
	{
		stageViewStart0 = new ksato.kiradol.views.StageViewStart0(this);
		mediaPlayers = newMediaPlayers( );
		setViews( );
		createEventListener( );
		createSe( );
		checkCountOfRun( );
		writeCountOfRun( );
		playBgm( );
		return;
	}
	
	private void setViews( )
	{
		constraintLayout = stageViewStart0.getConstraintLayout( );
		buttonToExplanation = stageViewStart0.getButtonToExplanation( );
		buttonToStartGame = stageViewStart0.getButtonToStartGame( );
		return;
	}
	
	private void createEventListener( )
	{
		StartOnTouchListener startOnTouchListener = new StartOnTouchListener( );
		ButtonToExplanationOnClickListener buttonToExplanationOnClickListener = new ButtonToExplanationOnClickListener( );
		ButtonToStartGameOnClickListener buttonToStartGameOnClickListener = new ButtonToStartGameOnClickListener( );
		constraintLayout.setOnTouchListener(startOnTouchListener);
		buttonToExplanation.setOnClickListener(buttonToExplanationOnClickListener);
		buttonToStartGame.setOnClickListener(buttonToStartGameOnClickListener);
		return;
	}
	
	private void createSe( )
	{
		mediaPlayers.buildSeSoundPool( );
		soundTouch =  mediaPlayers.loadSoundPool(R.raw.touch3);
	}
	
	private void checkCountOfRun( )
	{
		ksato.kiradol.models.FileIO fileIO = new ksato.kiradol.models.FileIO(this);
		if(!fileIO.existFile(fileIO.A_PATH_OF_COUNT_OF_RUN))
		{
			countOfRun = 0;
		}
		else
		{
			String resultOfRead = fileIO.readOneLine(fileIO.R_PATH_OF_COUNT_OF_RUN);
			if(resultOfRead.equals(""))
			{
				countOfRun = 1;
			}
			else
			{
				countOfRun = Long.parseLong(resultOfRead);
			}
		}
		return;
	}
	
	private void writeCountOfRun( )
	{
		ksato.kiradol.models.FileIO fileIO = new ksato.kiradol.models.FileIO(this);
			if(countOfRun >= Long.MAX_VALUE)
			{
				countOfRun = 1;
			}
			else
			{
				countOfRun += 1;
			}
			fileIO.writeTextFile(fileIO.R_PATH_OF_COUNT_OF_RUN, Long.toString(countOfRun));
		return;
	}
	
	private void playBgm( )
	{
		mediaPlayers.buildMediaPlayer( );
		mediaPlayers.loadMediaPlayer(R.raw.bgm_sunrise);
		mediaPlayers.loopMediaPlayer( );
		return;
	}
	
	// 更新処理
	@Override
	public void update( )
	{
		if(canUpdate)
		{
			stageViewStart0.draw( );
		}
		return;
	}
	
	// イベントリスナー
	private void startGame( )
	{
		//System.out.println("プレイ回数は " + countOfRun + " 回です。");
		if(countOfRun < 2)
		{
			goToExplanation( );
		}
		else
		{
			constraintLayout.setOnTouchListener(null);
			goToMenu( );
		}
		return;
	}
	
	private void goToExplanation( )
	{
		mediaPlayers.stopMediaPlayer( );
		mediaPlayers.allRelease( );
		Intent intentS0 = new Intent(getApplicationContext( ), ActivityExplanationMiyu.class);
		startActivity(intentS0);
		this.finish( );
		return;
	}
	
	private void goToMenu( )
	{
		canUpdate = false;
		stageViewStart0.InvisibleIImageViewString( );
		stageViewStart0.VisibleButtonMenu( );
		return;
	}
	
	private void goToStartGame( )
	{
		mediaPlayers.stopMediaPlayer( );
		mediaPlayers.allRelease( );
		Intent intentS1 = new Intent(getApplicationContext( ), ActivityMain1.class);
		startActivity(intentS1);
		this.finish( );
		return;
	}
	
	private class StartOnTouchListener implements View.OnTouchListener
	{
		@Override
		public boolean onTouch(View view, MotionEvent motionEvent)
		{
			switch(motionEvent.getAction( ))
			{
				case MotionEvent.ACTION_DOWN:
					onDown( );
					break;
			}
			return false;
		}
		
		private void onDown( )
		{
			mediaPlayers.playSyncSoundPool(soundTouch, 590);
			mediaPlayers.unloadSoundPoo(soundTouch);
			startGame( );
			return;
		}
	}
	
	private class ButtonToExplanationOnClickListener implements View.OnClickListener
	{
		@Override
		public void onClick(View view)
		{
			goToExplanation( );
			return;
		}
	}
	
	private class ButtonToStartGameOnClickListener implements View.OnClickListener
	{
		@Override
		public void onClick(View view)
		{
			goToStartGame( );
			return;
		}
	}
	
	
}
