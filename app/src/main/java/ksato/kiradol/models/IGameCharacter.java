package ksato.kiradol.models;

public interface IGameCharacter
{
	public int getxSize( );
	public int getySize( );
	public int getX( );
	public int getY( );
	public void setOnOverlapListener(IOnOverlapListener value);
	public boolean isOverlapWith(IGameCharacter gameCharacter);
	//public void move( );
}
