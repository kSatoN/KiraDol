package ksato.kiradol.models;

public class MiniJudgeZone extends MiniGameCharacter
{
	MiniJudgeZone(int jx, int jy)
	{
		//TODO xSizeとySize
		x = jx;
		y = jy;
		return;
	}
	
	@Override
	public void move( )
	{
		judge( );
		return;
	}
	
	private void judge( )
	{
		//TODO judge
		return;
	}
	
}
