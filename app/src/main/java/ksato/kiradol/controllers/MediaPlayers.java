package ksato.kiradol.controllers;

import android.annotation.TargetApi;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;


class MediaPlayers
{
	private BaseActivity activity;
	private MediaPlayer mediaPlayer = null;
	private SoundPool soundPool = null;
	private boolean mediaPlayerIsInitialized = false;
	private boolean mediaPlayerOnlyOnce = false;
	private int poolMax = 20;
	private final static int SOUND_POOL_PRIORITY = 1;	// 現在は必ず1
	
	void setMediaPlayerOnlyOnce(boolean value)
	{
		mediaPlayerOnlyOnce = value;
		return;
	}
	
	MediaPlayers(BaseActivity baseActivity)
	{
		activity = baseActivity;
		initialize( );
		return;
	}
	
	private void initialize( )
	{
		activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		return;
	}
	
	void allRelease( )
	{
		if(mediaPlayer != null)
		{
			mediaPlayer.release( );
		}
		if(soundPool != null)
		{
			soundPool.release( );
		}
		return;
	}
	
	// -----------------------------
	// ① MediaPlayer
	// -----------------------------
	void buildMediaPlayer()
	{
		mediaPlayer = new MediaPlayer( );
		return;
	}
	
	void loadMediaPlayer(int id)
	{
		mediaPlayer = MediaPlayer.create(activity, id);
		mediaPlayer.setOnCompletionListener(new MediaPlayerOnCompletionListener( ));
		mediaPlayer.setVolume(1.00f, 1.00f);
		return;
	}
	
	void playMediaPlayer( )
	{
		if(!mediaPlayer.isPlaying( ) && mediaPlayer != null)
		{
			mediaPlayer.setLooping(false);
			mediaPlayer.start( );
		}
		return;
	}
	
	void playSyncMediaPlayer( )
	{
		int ms = mediaPlayer.getDuration( );
		if(!mediaPlayer.isPlaying( ) && mediaPlayer != null)
		{
			mediaPlayer.setLooping(false);
			mediaPlayer.start( );
			try
			{
				Thread.sleep(ms + 100);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace( );
			}
		}
		return;
	}
	
	void loopMediaPlayer( )
	{
		if(!mediaPlayer.isPlaying( ) && mediaPlayer != null)
		{
			mediaPlayer.setLooping(true);
			mediaPlayer.start( );
		}
		return;
	}
	
	void stopMediaPlayer( )
	{
		if(mediaPlayer.isPlaying( ) && mediaPlayer != null)
		{
			mediaPlayer.stop( );
		}
		return;
	}
	
	void unloadMediaPlayer( )
	{
		mediaPlayer.release( );
		return;
	}
	
	// -----------------------------
	// ② SoundPool（Voice，SE用）
	// -----------------------------
	@SuppressWarnings("deprecation")
	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	void buildVoiceSoundPool( )
	{
		if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
		{
			soundPool = new SoundPool(poolMax, AudioManager.STREAM_MUSIC, 0);
		}
		else
		{
			AudioAttributes audioAttributes = new AudioAttributes.Builder( )
				.setUsage(AudioAttributes.USAGE_GAME)
				.setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
				.build( );
			soundPool = new SoundPool.Builder( )
				.setAudioAttributes(audioAttributes)
				.setMaxStreams(poolMax)
				.build( );
		}
		return;
	}
	
	@SuppressWarnings("deprecation")
	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	void buildSeSoundPool( )
	{
		if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
		{
			soundPool = new SoundPool(poolMax, AudioManager.STREAM_MUSIC, 0);
		}
		else
		{
			AudioAttributes audioAttributes = new AudioAttributes.Builder( )
				.setUsage(AudioAttributes.USAGE_GAME)
				.setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
				.build( );
			soundPool = new SoundPool.Builder( )
				.setAudioAttributes(audioAttributes)
				.setMaxStreams(poolMax)
				.build( );
		}
		return;
	}
	
	int loadSoundPool(int id)
	{
		return soundPool.load(activity, id, SOUND_POOL_PRIORITY);
	}
	
	void playSyncSoundPool(int soundId, long ms)
	{
		soundPool.play(soundId, 1.00f, 1.00f, 0, 0, 1.00f);	// ID，左音量，右音量，優先度，ループ，速度
		try
		{
			Thread.sleep(ms + 100);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace( );
		}
		return;
	}
	
	// 非同期
	void playSoundPool(int soundId)
	{
		soundPool.play(soundId, 1.00f, 1.00f, 0, 0, 1.00f);	// ID，左音量，右音量，優先度，ループ，速度
		return;
	}
	
	void loopSoundPool(int soundId)
	{
		soundPool.play(soundId, 1.00f, 1.00f, 0, -1, 1.00f);
		return;
	}
	
	void unloadSoundPoo(int soundId)
	{
		soundPool.unload(soundId);
		return;
	}
	
	// -----------------------------
	// その他，重要なの
	// -----------------------------
	void onResume( )
	{
		return;
	}
	
	void onDestroy( )
	{
		allRelease( );
		return;
	}
	
	
	// -----------------------------
	// リスナー
	// -----------------------------
	private class MediaPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener
	{
		@Override
		public void onCompletion(MediaPlayer mediaPlayer)
		{
			synchronized(this)
			{
				mediaPlayerIsInitialized = false;
			}
			if(mediaPlayerOnlyOnce)
			{
				unloadMediaPlayer( );
			}
			return;
		}
	}
	
}
