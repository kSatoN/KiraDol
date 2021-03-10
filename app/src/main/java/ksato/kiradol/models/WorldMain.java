package ksato.kiradol.models;

import java.util.LinkedList;

///TODO kira回転，死亡理由
public abstract class WorldMain implements IWorldMain
{
	static final double GRAVITY = 2.50;
	static final int WIDTH = 7680;
	static final int HEIGHT = 1080;
	protected ksato.kiradol.controllers.ActivityMain activity;
	private IClearListener clearListener;
	protected LinkedList<IGameCharacter> gameCharacters;
	protected boolean debugMode = false;
	private boolean wasEnding = false;
	private boolean wasEnded = false;
	private boolean wasClear = false;
	protected PointManager pointManager;
	protected Floor floor;
	protected IIdol idol;
	protected LinkedList<Platform> platforms;
	protected LinkedList<KiraKira> pKiraKira;
	protected LinkedList<KiraidaaaBuilder> pKiraidaaa;
	protected Station station;
	
	WorldMain(ksato.kiradol.controllers.ActivityMain activityMain)
	{
		activity = activityMain;
		clearListener = activity.getClearListener( );
		createLists( );
		createObjects( );
		jointObjects( );
		jointGameCharacterList( );
		return;
	}
	
	private void createLists( )
	{
		gameCharacters = new LinkedList<IGameCharacter>( );
		platforms = new LinkedList<Platform>( );
		pKiraKira = new LinkedList<KiraKira>( );
		pKiraidaaa = new LinkedList<KiraidaaaBuilder>( );
		return;
	}
	
	protected abstract void createObjects( );
	
	private void jointObjects( )
	{
		floor.setIdol(idol);
		for(Platform platform: platforms)
		{
			platform.setIdol(idol);
		}
		for(KiraKira kiraKira: pKiraKira)
		{
			kiraKira.setIdol(idol);
		}
		for(KiraidaaaBuilder kiraidaaa : pKiraidaaa)
		{
			kiraidaaa.setHeroine(idol);
		}
		station.setIdol(idol);
		return;
	}
	
	private void jointGameCharacterList( )
	{
		// ※ 描画は逆順！！
		gameCharacters.add(floor);
		gameCharacters.add(idol);
		gameCharacters.addAll(pKiraidaaa);
		gameCharacters.addAll(pKiraKira);
		gameCharacters.add(station);
		gameCharacters.addAll(platforms);
		return;
	}
	
	@Override
	public boolean isEnding( )
	{
		wasEnding = idol.isDead( ) && !idol.isPerfectlyDead( );
		return wasEnding;
	}
	
	@Override
	public boolean isEnded( )
	{
		wasEnded = idol.isPerfectlyDead( );
		return wasEnded;
	}
	
	@Override
	public boolean isClear( )
	{
		wasClear = station.isCleared( );
		return wasClear;
	}
	
	@Override
	public PointManager getPointManager( )
	{
		return pointManager;
	}
	
	@Override
	public LinkedList<IGameCharacter> getGameCharacters( )
	{
		return gameCharacters;
	}
	
	@Override
	public Floor getFloor( )
	{
		return floor;
	}
	
	@Override
	public IIdol getIdol( )
	{
		return idol;
	}
	
	@Override
	public void move( )
	{
		if(isClear( ) || isEnded( ))
		{
			return;
		}
		if(isEnding( ))
		{
			Idol cIdol = (Idol) idol;
			cIdol.move( );
			return;
		}
		for(IGameCharacter gameCharacter: gameCharacters)
		{
			GameCharacter character = (GameCharacter) gameCharacter;
			character.move( );
		}
		return;
	}
	
	void updateClear(EClear c, EGameOver gameOver)
	{
		boolean is;
		switch(c)
		{
			case Ending:
				is = idol.isDead( ) && !idol.isPerfectlyDead( );
				if(wasEnding != is)
				{
					clearListener.changeClear(EClear.Ending, gameOver);
				}
				return;
			case Ended:
				is = idol.isPerfectlyDead( );
				if(wasEnded != is)
				{
					clearListener.changeClear(EClear.Ended, gameOver);
				}
				return;
		}
		return;
	}
	
	void updateClear(EClear c)
	{
		boolean is;
		is = station.isCleared( );
		if(wasClear != is)
		{
			pointManager.savePoint( );
			clearListener.changeClear(EClear.Clear, pointManager.getNextFollower( ));
		}
		return;
	}
	
}
