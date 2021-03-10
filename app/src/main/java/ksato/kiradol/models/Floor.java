package ksato.kiradol.models;

public class Floor extends Object implements IGameCharacter, IObject
{
	Floor( )
	{
		xSize = 1920;
		ySize = 48;
		x = 0;
		y = 1080 - ySize;
		return;
	}
	
	public void setX(int value)
	{
		if(value <= WorldMain.WIDTH - 1920)
		{
			x = value;
		}
		else
		{
			x = WorldMain.WIDTH - 1920;
		}
		return;
	}
	
	@Override
	void move( )
	{
		if(isOverlapWith(idol))
		{
			idol.setOverlappingF(true);
			idol.land(this);
		}
		else
		{
			idol.setOverlappingF(false);
		}
		return;
	}
	
}
