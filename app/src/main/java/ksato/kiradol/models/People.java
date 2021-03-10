package ksato.kiradol.models;

public abstract class People extends GameCharacter implements IGameCharacter
{
	protected double xVelocity;
	protected double yVelocity;
	
	public double getxVelocity( )
	{
		return xVelocity;
	}
	
	public double getyVelocity( )
	{
		return yVelocity;
	}
	
}
