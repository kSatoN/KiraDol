package ksato.kiradol.models;

public class KiraKira extends Object implements IGameCharacter, IObject
{
	final private PointManager pointManager;
	private int state;
	private boolean _isRemained;
	public int getState( )
	{
		return state;
	}
	public boolean isRemained( )
	{
		return _isRemained;
	}
	
	KiraKira(int wx, int wy, boolean isOnPlatform, PointManager point)
	{
		ySize = (xSize = 96);
		x = setX(wx, isOnPlatform);
		y = setY(wy, isOnPlatform);
		pointManager = point;
		state = 0;
		_isRemained = true;
		return;
	}
	
	private int setX(int wx, boolean isOnPlatform)
	{
		if(isOnPlatform)
		{
			return wx;
		}
		else
		{
			return random(wx - 25, wx + 25);
		}
	}
	
	private int setY(int wy, boolean isOnPlatform)
	{
		if(isOnPlatform)
		{
			return wy;
		}
		else
		{
			return random(wy - 25, wy + 25);
		}
	}
	
	@Override
	void move( )
	{
		if(!isOverlapWith(idol) || idol.isDead( ) || !_isRemained)
		{
			return;
		}
		_isRemained = false;
		onOverlapListener.onOverlap(this);
		pointManager.addPoint( );
		return;
	}
	
}
