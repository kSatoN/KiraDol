package ksato.kiradol.models;

public class Platform extends Object implements IGameCharacter, IObject
{
	private int index;
	
	Platform(int wx, int wy, int windex)
	{
		xSize = 256;
		ySize = 48;
		x = random(wx - 25, wx + 25);
		y = random(wy - 25, wy + 25);
		index = windex;
		return;
	}

	@Override
	void move( )
	{
		if(isOverlapWith(idol))
		{
			idol.setOverlappingO(index, true);
			idol.overlapO(index, this);
		}
		else
		{
			idol.setOverlappingO(index, false);
		}
		return;
	}

}
