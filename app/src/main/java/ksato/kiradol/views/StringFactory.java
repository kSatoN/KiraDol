package ksato.kiradol.views;

import ksato.kiradol.R;


public class StringFactory
{
	private ksato.kiradol.controllers.BaseActivity activity;
	private int typeOfCharacter;
	private int typeOfWords;
	private int[ ][ ] strings = new int[2][9];
	private int returnString;
	public int getReturnString( )
	{
		return returnString;
	}
	
	/// <summary>
	/// StringFactoryのインスタンスを生成し，初期化します。
	/// </summary>
	/// <param name="character">0：美夢ちゃん，1：プリ動開発者</param>
	public StringFactory(ksato.kiradol.controllers.BaseActivity baseActivity, int character)
	{
		activity = baseActivity;
		typeOfCharacter = character;
		typeOfWords = 0;
		setStrings( );
		return;
	}
	
	private void setStrings( )
	{
		setStringsMiyu( );
		setStringsPri( );
		return;
	}
	
	private void setStringsMiyu( )
	{
		strings[0][0] = R.string.explanationMiyu0;
		strings[0][1] = R.string.explanationMiyu1;
		strings[0][2] = R.string.explanationMiyu2;
		strings[0][3] = R.string.explanationMiyu3;
		strings[0][4] = 0;
		strings[0][5] = 0;
		strings[0][6] = 0;
		strings[0][7] = 0;
		return;
	}
	
	private void setStringsPri( )
	{
		strings[1][0] = R.string.explanationPri0;
		strings[1][1] = R.string.explanationPri1;
		strings[1][2] = R.string.explanationPri2;
		strings[1][3] = R.string.explanationPri3;
		strings[1][4] = R.string.explanationPri4;
		strings[1][5] = R.string.explanationPri5;
		strings[1][6] = R.string.explanationPri6;
		strings[1][7] = R.string.explanationPri7;
		strings[1][8] = 0;
		return;
	}
	
	public boolean changeStrings( )
	{
		if(strings[typeOfCharacter][typeOfWords] != 0)
		{
			returnString = strings[typeOfCharacter][typeOfWords];
			typeOfWords += 1;
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
