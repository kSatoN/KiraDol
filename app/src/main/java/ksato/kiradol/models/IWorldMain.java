package ksato.kiradol.models;

import java.util.LinkedList;


public interface IWorldMain extends IWorld
{
	int WIDTH = WorldMain.WIDTH;
	int HEIGHT = WorldMain.HEIGHT;
	public boolean isEnding( );
	public boolean isEnded( );
	public boolean isClear( );
	public PointManager getPointManager( );
	public LinkedList<IGameCharacter> getGameCharacters( );
	public Floor getFloor( );
	public IIdol getIdol( );
	
}
