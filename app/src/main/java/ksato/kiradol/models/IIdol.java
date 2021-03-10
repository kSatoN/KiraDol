package ksato.kiradol.models;

public interface IIdol extends IGameCharacter
{
	boolean isDead( );
	boolean isPerfectlyDead( );
	public void setxVelocity(double value);
	public double getxVelocity( );
	public double getyVelocity( );
	void setOverlappingF(boolean value);
	void setOverlappingO(int index, boolean value);
	void killed( );
	void jump( );
	void overlapO(int index, IObject object);
	void land(IObject object);

}
