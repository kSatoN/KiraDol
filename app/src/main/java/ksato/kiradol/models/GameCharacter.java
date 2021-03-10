package ksato.kiradol.models;

abstract class GameCharacter implements IGameCharacter
{
	protected int xSize;
	protected int ySize;
	protected int x;
	protected int y;
	protected IOnOverlapListener onOverlapListener;
	
	GameCharacter( )
	{
		onOverlapListener = null;
		return;
	}
	
	@Override
	public int getxSize( )
	{
		return xSize;
	}
	
	@Override
	public int getySize( )
	{
		return ySize;
	}
	
	@Override
	public int getX( )
	{
		return x;
	}
	
	@Override
	public int getY( )
	{
		return y;
	}
	
	@Override
	public void setOnOverlapListener(IOnOverlapListener value)
	{
		onOverlapListener = value;
		return;
	}
	
	@Override
	public boolean isOverlapWith(IGameCharacter c)
	{
		if(c instanceof IIdol && ( (IIdol) c).isDead( ))
		{
			return false;
		}
		return !(youAreLeftFromMe(c) || youAreDownFromMe(c) || youAreRightFromMe(c) || youAreUpFromMe(c));
	}
	
	boolean youAreLeftFromMe(IGameCharacter gameCharacter)
	{
		return gameCharacter.getX( ) + gameCharacter.getxSize( ) - 1 < x;
	}
	
	private boolean youAreDownFromMe(IGameCharacter gameCharacter)
	{
		return y + ySize - 1 < gameCharacter.getY( );
	}
	
	boolean youAreRightFromMe(IGameCharacter gameCharacter)
	{
		return x + xSize - 1 < gameCharacter.getX( );
	}
	
	private boolean youAreUpFromMe(IGameCharacter gameCharacter)
	{
		return gameCharacter.getY( ) + gameCharacter.getySize( ) - 1 < y;
	}
	
	abstract void move( );
	
	protected int random(int from, int to)
	{
		return (int) (Math.random( ) * (to - from + 1)) + from;
	}
	
}
