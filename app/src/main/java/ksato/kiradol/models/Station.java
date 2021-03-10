package ksato.kiradol.models;

public class Station extends Object implements IGameCharacter, IObject
{
	private IIdol idol;
	private WorldMain world;
	private PointManager pointManager;
	private boolean _isCleared;
	public boolean isCleared( )
	{
		return _isCleared;
	}
	
	Station(int wx, int wy, IIdol iIdol, WorldMain worldMain, PointManager manager)
	{
		xSize = 130;
		ySize = 180;
		x = random(wx - 100, wx + 100);
		y = wy;
		idol = iIdol;
		world = worldMain;
		pointManager = manager;
		_isCleared = false;
		return;
	}
	
	@Override
	void move( )
	{
		if(!isOverlapWith(idol) || idol.isDead( ))
		{
			return;
		}
		onOverlapListener.onOverlap(this);
		updateClear( );
		return;
	}
	
	private void updateClear( )
	{
		if(pointManager.getStageKiraKiraPoint( ) <= 0)
		{
			idol.killed( );
		}
		else
		{
			_isCleared = true;
			world.updateClear(EClear.Clear);
		}
		return;
	}
	
}
