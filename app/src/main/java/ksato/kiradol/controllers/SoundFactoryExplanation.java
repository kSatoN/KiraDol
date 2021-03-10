package ksato.kiradol.controllers;

import java.util.Arrays;

import ksato.kiradol.R;


class SoundFactoryExplanation
{
	private ksato.kiradol.controllers.BaseActivity activity;
	private int typeOfCharacter;
	private int typeOfSounds;
	private int[ ][ ] sounds = new int[2][9];
	private int returnSound;
	int getTypeOfSounds( )
	{
		return typeOfSounds;
	}
	int getReturnSound( )
	{
		return returnSound;
	}
	
	/// <summary>
	/// SoundFactoryのインスタンスを生成し，初期化します。
	/// </summary>
	/// <param name="character">0：美夢ちゃん，1：プリ動開発者</param>
	SoundFactoryExplanation(ksato.kiradol.controllers.BaseActivity baseActivity, int character)
	{
		activity = baseActivity;
		typeOfCharacter = character;
		typeOfSounds = 0;
		setSounds( );
		return;
	}
	
	private void setSounds( )
	{
		setSoundsMiyu( );
		setSoundsPri( );
		return;
	}
	
	private void setSoundsMiyu( )
	{
		sounds[0][0] = R.raw.explanation_miyu0;
		sounds[0][1] = R.raw.explanation_miyu1;
		sounds[0][2] = R.raw.explanation_miyu2;
		sounds[0][3] = R.raw.explanation_miyu3;
		sounds[0][4] = 0;
		sounds[0][5] = 0;
		sounds[0][6] = 0;
		sounds[0][7] = 0;
		return;
	}
	
	private void setSoundsPri( )
	{
		sounds[1][0] = R.raw.explanation_pri0;
		sounds[1][1] = R.raw.explanation_pri1;
		sounds[1][2] = R.raw.explanation_pri2;
		sounds[1][3] = R.raw.explanation_pri3;
		sounds[1][4] = R.raw.explanation_pri4;
		sounds[1][5] = R.raw.explanation_pri5;
		sounds[1][6] = R.raw.explanation_pri6;
		sounds[1][7] = R.raw.explanation_pri7;
		sounds[1][8] = 0;
		return;
	}
	
	public boolean changeSounds( )
	{
		if(sounds[typeOfCharacter][typeOfSounds] != 0)
		{
			returnSound = sounds[typeOfCharacter][typeOfSounds];
			typeOfSounds += 1;
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
