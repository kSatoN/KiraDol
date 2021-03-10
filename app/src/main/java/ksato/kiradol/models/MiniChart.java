package ksato.kiradol.models;

public class MiniChart extends MiniGameCharacter
{
	private double ySpeed;
	
	MiniChart(int cx)
	{
		// TODO xSizeとySize
		x = cx;
		y = 360 + ySize;
		return;
	}
	
	@Override
	public void move( )
	{
		y = (int) ( (double) y - ySpeed);
		return;
	}
	
}
