package ksato.kiradol.models;

class KiraidaaaMoving extends KiraidaaaBuilder implements IGameCharacter, ICritic
{
	KiraidaaaMoving(int wx, int wy)
	{
		xSize = 167;
		ySize = 180;
		x = random(wx - 25, wx + 25);
		y = wy;
		markedForDeath = false;
		xVelocity = random(5, 20);
	}
	
	@Override
	public void move( )
	{
		if(isOverlapWith(heroine))
		{
			kill( );
			xVelocity = 0.00;
		}
		else
		{
			xMove( );
			turn( );
		}
		return;
	}
	
	private void xMove( )
	{
		x = (int) ( (double) x + xVelocity);
		return;
	}
	
	private void turn( )
	{
		if(x < 0)
		{
			x = 1;
			xVelocity = -xVelocity;
		}
		else if(x + xSize > 3840 - 67)
		{
			x = 3840 - 67 - xSize - 1;
			xVelocity = -xVelocity;
		}
		return;
	}
	
}
