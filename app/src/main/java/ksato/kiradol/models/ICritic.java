package ksato.kiradol.models;

public interface ICritic extends IGameCharacter
{
	public boolean isDead( );
	public void killed( );
	public void setHeroine(IIdol heroine);
	
}
