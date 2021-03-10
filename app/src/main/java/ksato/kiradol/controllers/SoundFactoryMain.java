package ksato.kiradol.controllers;

import ksato.kiradol.R;


class SoundFactoryMain
{
	private ksato.kiradol.controllers.BaseActivity activity;
	private MediaPlayers mediaPlayers;
	private int soundIdJump;
	private int soundIdLanding;
	private int soundIdKiraKira;
	private int soundIdKiraidaaa;
	private int soundIdFalling;
	
	SoundFactoryMain(ksato.kiradol.controllers.BaseActivity baseActivity)
	{
		activity = baseActivity;
		mediaPlayers = activity.newMediaPlayers( );
		prepareSe( );
		return;
	}
	
	private void prepareSe( )
	{
		mediaPlayers.buildSeSoundPool( );
		soundIdJump = mediaPlayers.loadSoundPool(R.raw.jump);
		soundIdLanding = mediaPlayers.loadSoundPool(R.raw.landing);
		soundIdKiraKira = mediaPlayers.loadSoundPool(R.raw.kira_kira);
		soundIdKiraidaaa = mediaPlayers.loadSoundPool(R.raw.kiraidaaa);
		soundIdFalling = mediaPlayers.loadSoundPool(R.raw.falling);
		return;
	}
	
	void playSe(int sound)
	{
		switch(sound)
		{
			case R.raw.jump:
				mediaPlayers.playSoundPool(soundIdJump);
				break;
			case R.raw.landing:
				mediaPlayers.playSoundPool(soundIdLanding);
				break;
			case R.raw.kira_kira:
				mediaPlayers.playSoundPool(soundIdKiraKira);
				break;
			case R.raw.kiraidaaa:
				mediaPlayers.playSoundPool(soundIdKiraidaaa);
				break;
			case R.raw.falling:
				mediaPlayers.playSoundPool(soundIdFalling);
				break;
		}
		return;
	}
	
	void release( )
	{
		mediaPlayers.allRelease( );
		return;
	}

}
