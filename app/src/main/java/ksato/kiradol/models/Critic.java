package ksato.kiradol.models;

abstract class Critic extends People implements IGameCharacter, ICritic
{
	protected IIdol heroine;
	protected boolean markedForDeath;
	
	@Override
	public void setHeroine(IIdol value)
	{
		heroine = value;
		return;
	}
	
	@Override
	public boolean isDead( )
	{
		return markedForDeath;
	}
	
	@Override
	public void killed( )
	{
		markedForDeath = true;
		return;
	}
	
	protected void kill( )
	{
		if(markedForDeath)
		{
			return;
		}
		onOverlapListener.onOverlap(this);
		heroine.killed( );
		return;
	}
	
}
