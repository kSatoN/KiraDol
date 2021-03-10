package ksato.kiradol.controllers;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import ksato.kiradol.R;
import ksato.kiradol.models.EClear;
import ksato.kiradol.models.EGameOver;
import ksato.kiradol.models.IClearListener;
import ksato.kiradol.models.IGameCharacter;
import ksato.kiradol.models.IOnOverlapListener;
import ksato.kiradol.models.IWorldMain;


public abstract class ActivityMain extends BaseActivity
{
	private final double filterWeight = 0.80;
	private double filteredAcceralation;
	// モデル
	protected IWorldMain world;
	// ビュー
	protected ksato.kiradol.views.StageViewMain stageView;
	private ClearListener clearListener;
	private Button buttonRetry;
	private Button buttonNext;
	private ImageView imageViewJump;
	// 音源ファイル
	private MediaPlayers mediaPlayers;
	private SoundFactoryMain soundFactory;
	public ClearListener getClearListener( )
	{
		return clearListener;
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
		filteredAcceralation = 0.00;
		clearListener = new ClearListener( );
		createViews( );
		createModels( );
		createOtherObjects( );
		setEventListeners( );
		playBgm( );
		//startTimer( );
	}
	
	protected abstract void createViews( );
	protected abstract void createModels( );
	
	private void createOtherObjects( )
	{
		imageViewJump = stageView.getImageViewJump( );
		buttonRetry = stageView.getButtonRetry( );
		buttonNext = stageView.getButtonNext( );
		mediaPlayers = newMediaPlayers( );
		soundFactory = new SoundFactoryMain(this);
		return;
	}
	
	private void setEventListeners( )
	{
		ImageViewJumpOnTouchListener imageViewJumpOnTouchListener = new ImageViewJumpOnTouchListener( );
		ButtonRetryOnClickListener buttonRetryOnClickListener = new ButtonRetryOnClickListener( );
		ButtonNextOnClickListener buttonNextOnClickListener = new ButtonNextOnClickListener( );
		OnOverlapListener onOverlapListener = new OnOverlapListener( );
		imageViewJump.setOnTouchListener(imageViewJumpOnTouchListener);
		buttonRetry.setOnClickListener(buttonRetryOnClickListener);
		buttonNext.setOnClickListener(buttonNextOnClickListener);
		for(IGameCharacter gameCharacter: world.getGameCharacters( ))
		{
			gameCharacter.setOnOverlapListener(onOverlapListener);
		}
		return;
	}
	
	private void playBgm( )
	{
		mediaPlayers.buildMediaPlayer( );
		mediaPlayers.loadMediaPlayer(ksato.kiradol.R.raw.bgm_main_game);
		mediaPlayers.loopMediaPlayer( );
		return;
	}
	
	@Override	// 更新処理
	public void update( )
	{
		if(world.isClear( ) || world.isEnded( ))
		{
			return;
		}
		setxVelocity( );
		world.move( );
		stageView.render(world);
		return;
	}
	
	private void setxVelocity( )
	{
		ksato.kiradol.models.IIdol idol = world.getIdol( );
		double rawAccelerationY = (double) accelerationController.y;
		filteredAcceralation = filterWeight * filteredAcceralation + (1 - filterWeight) * rawAccelerationY;
		if(-1.00 < filteredAcceralation && filteredAcceralation < 1.00)
		{
			idol.setxVelocity(0.00);
		}
		else
		{
			idol.setxVelocity( (5.00 * filteredAcceralation));
		}
		return;
	}
	
	protected abstract void goToNextStage( );
	
	private void goToStart0( )
	{
		releaseMediaPlayerOfThisAndSoundFactory( );
		Intent intentM0 = new Intent(getApplicationContext( ), ActivityStart0.class);
		startActivity(intentM0);
		this.finish( );
		return;
	}
	
	protected void releaseMediaPlayerOfThisAndSoundFactory( )
	{
		mediaPlayers.allRelease( );
		mediaPlayers = null;
		soundFactory.release( );
		soundFactory = null;
		return;
	}
	
	
	// イベントリスナー
	private class ImageViewJumpOnTouchListener implements View.OnTouchListener
	{
		@Override
		public boolean onTouch(View view, MotionEvent motionEvent)
		{
			switch(motionEvent.getAction( ))
			{
				case MotionEvent.ACTION_DOWN:
					world.getIdol( ).jump( );
					break;
			}
			return false;
		}
	}
	
	private class ButtonRetryOnClickListener implements View.OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			goToStart0( );
			return;
		}
	}
	
	private class ButtonNextOnClickListener implements View.OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			goToNextStage( );
			return;
		}
	}
	
	private class ClearListener implements IClearListener
	{
		@Override
		public void changeClear(EClear typeOfClear, EGameOver gameOver)
		{
			stageView.renderGameOver(gameOver);
			vibe(typeOfClear);
			return;
		}
		
		@Override
		public void changeClear(EClear typeOfClear, String follower)
		{
			stageView.renderStageClear(follower);
			vibe(typeOfClear);
			return;
		}
		
		private void vibe(EClear typeOfClear)
		{
			Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
			switch(typeOfClear)
			{
				case Ending:
					vibrator.vibrate(2000);
					break;
				case Clear:
					vibrator.vibrate(500);
					break;
			}
			return;
		}
		
	}
	
	private class OnOverlapListener implements IOnOverlapListener
	{
		@Override
		public void onOverlap(IGameCharacter gameCharacter)
		{
			if(gameCharacter instanceof ksato.kiradol.models.Floor)
			{
				landing( );
			}
			else if(gameCharacter instanceof ksato.kiradol.models.IIdol)
			{
				idolOnOverlap( );
			}
			else if(gameCharacter instanceof ksato.kiradol.models.Platform)
			{
				landing( );
			}
			else if(gameCharacter instanceof ksato.kiradol.models.KiraKira)
			{
				kiraKiraOnOverlap( );
			}
			else if(gameCharacter instanceof ksato.kiradol.models.ICritic)
			{
				criticOnOverlap( );
			}
			return;
		}
		
		private void landing( )
		{
			soundFactory.playSe(R.raw.landing);
			return;
		}
		
		private void idolOnOverlap( )
		{
			soundFactory.playSe(R.raw.jump);
			return;
		}
		
		private void kiraKiraOnOverlap( )
		{
			soundFactory.playSe(R.raw.kira_kira);
			return;
		}
		
		private void criticOnOverlap( )
		{
			soundFactory.playSe(R.raw.kiraidaaa);
		}
		
	}
	
	
}
