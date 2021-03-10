package ksato.kiradol.models;

class KiraidaaaStatic extends KiraidaaaBuilder implements IGameCharacter, ICritic
{
	KiraidaaaStatic(int wx, int wy)
	{
		xSize = 167;
		ySize = 180;
		x = random(wx - 25, wx + 25);
		y = wy;
		markedForDeath = false;
	}
	
	@Override
	public void move( )
	{
		if(isOverlapWith(heroine))
		{
			kill( );
		}
		return;
	}
	
}
