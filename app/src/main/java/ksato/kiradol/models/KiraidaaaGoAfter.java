package ksato.kiradol.models;

class KiraidaaaGoAfter extends KiraidaaaBuilder implements IGameCharacter, ICritic
{
	KiraidaaaGoAfter(int wx, int wy)
	{
		xSize = 167;
		ySize = 180;
		x = random(wx - 100, wx + 100);
		y = wy;
		markedForDeath = false;
		xVelocity = 0.00;
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
			setFuzzyVelocity( );
			xMove( );
		}
		return;
	}
	
	private void setFuzzyVelocity( )
	{
		xVelocity = coefficientOfFuzzy( ) * 20;
		return;
	}
	
	private double coefficientOfFuzzy( )
	{
		if(norm( ) < -1000)
		{
			return -1.00;
		}
		else if(-1000 <= norm( ) && norm( ) <= -10)
		{
			return (0.9 / 990.0) * (norm( ) + 1000.0) - 1.0;
		}
		else if(-10 < norm( ) && norm( ) < 0)
		{
			return -0.100;
		}
		else if(0 <= norm( ) && norm( ) < 10)
		{
			return 0.100;
		}
		else if(10 <= norm( ) && norm( ) <= 1000)
		{
			return (0.9 / 990.0) * (norm( ) - 1000) + 1.0;
		}
		else
		{
			return 1.00;
		}
	}
	
	private int norm( )
	{
		if(youAreLeftFromMe(heroine))
		{
			return (heroine.getX( ) + heroine.getxSize( )) - x;
		}
		else if(youAreRightFromMe(heroine))
		{
			return heroine.getX( ) - x;
		}
		return 0;
	}
	
	private void xMove( )
	{
		x = (int) ( (double) x + xVelocity);
		return;
	}
	
}
