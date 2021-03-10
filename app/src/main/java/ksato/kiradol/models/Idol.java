package ksato.kiradol.models;

import java.util.ArrayList;
import java.util.Arrays;


public class Idol extends People implements IIdol, IGameCharacter
{
	private final double dyingSpeed = 4.00;
	private WorldMain world;
	private int[ ] xObjectLeft;
	private int[ ] xObjectRight;
	private int[ ] mediumOfObject;
	private boolean jumping;
	private ArrayList<Boolean> overlapping;
	private boolean markedForDeath;
	private boolean perfectlyDead;

	Idol(WorldMain worldMain)
	{
		xSize = 96;
		ySize = 159;
		x = 67;
		y = 1080 - 48 - ySize + 1;
		xVelocity = 0.00;
		yVelocity = 0.00;
		xObjectLeft = new int[7];
		xObjectRight = new int[7];
		mediumOfObject = new int[7];
		jumping = false;
		overlapping = new ArrayList<Boolean>(7);
		for(int i = 0; i < 7; i += 1)
		{
			overlapping.add(false);
		}
		markedForDeath = false;
		perfectlyDead = false;
		world = worldMain;
		return;
	}
	
	@Override
	public void setOverlappingF(boolean value)
	{
		overlapping.set(0, value);
		return;
	}
	
	@Override
	public void setOverlappingO(int index, boolean value)
	{
		overlapping.set(index, value);
		return;
	}

	@Override
	public void setxVelocity(double value)
	{
		if(markedForDeath || jumping)
		{
			return;
		}
		xVelocity = value;
		return;
	}

	@Override
	public boolean isDead( )
	{
		return markedForDeath;
	}
	
	public boolean isPerfectlyDead( )
	{
		return perfectlyDead;
	}

	@Override
	public void killed( )
	{
		markedForDeath = true;
		jumping = false;
		yVelocity = -30.0;
		world.updateClear(EClear.Ending);
	}

	@Override
	public void jump( )
	{
		if(jumping || markedForDeath)
		{
			return;
		}
		onOverlapListener.onOverlap(this);
		yVelocity = -40.0;
		jumping = true;
	}

	@Override
	public void overlapO(int index, IObject object)
	{
		if(markedForDeath)
		{
			return;
		}
		if(jumping && yVelocity >= 0.01 && y + ySize - yVelocity - 5 < object.getY( ))
		{
			land(object);
		}
		else
		{
			xObjectLeft[index] = object.getX( );
			xObjectRight[index] = object.getX( ) + object.getxSize( ) - 1;
			mediumOfObject[index] = xObjectLeft[index] + (object.getxSize( ) / 2);
		}
		return;
	}
	
	@Override
	public void land(IObject object)
	{
		if(!jumping || markedForDeath || yVelocity < 0.01)
		{
			return;
		}
		onOverlapListener.onOverlap(object);
		yVelocity = 0.00;
		y = object.getY( ) - ySize + 1;
		jumping = false;
		return;
	}

	@Override
	void move( )
	{
		if(perfectlyDead)
		{
			return;
		}
		else if(markedForDeath)
		{
			dyingMove( );
			return;
		}
		xMove( );
		controlOverlappingMove( );
		if(jumping)
		{
			yMove( );
		}
		return;
	}

	private void dyingMove( )
	{
		y = (int) ( (double) y + (yVelocity / dyingSpeed));
		if(y > WorldMain.HEIGHT)
		{
			perfectlyDead = true;
			world.updateClear(EClear.Ended);
			return;
		}
		yVelocity += (WorldMain.GRAVITY / dyingSpeed);
		return;
	}

	private void xMove( )
	{
		x = (int) ( (double) x + xVelocity);
		if(x < 0)
		{
			x = 1;
		}
		else if(x + xSize > WorldMain.WIDTH)
		{
			x = WorldMain.WIDTH - xSize -1;
		}
		return;
	}
	
	private void controlOverlappingMove( )
	{
		if(overlapping.get(0))
		{
			for(int index = 1; index < overlapping.size( ); index += 1)
			{
				if(overlapping.get(index))
				{
					overlappingXMove(index);
					return;
				}
			}
		}
		else if(!overlapping.contains(true))
		{
			jumping = true;
		}
		return;
	}
	
	private void overlappingXMove(int index)
	{
		if(index == 0)
		{
			return;
		}
		if(xObjectLeft[index] <= x + xSize && x <= mediumOfObject[index])
		{
			x = xObjectLeft[index] - xSize + 1;
		}
		else if(mediumOfObject[index] < x && x <= xObjectRight[index])
		{
			x = xObjectRight[index];
		}
		return;
	}

	private void yMove( )
	{
		y = (int) ( (double) y + yVelocity);
		yVelocity += WorldMain.GRAVITY;
		if(y > 1080 - 48 - ySize + 1 + 1)
		{
			yVelocity = 0.00;
			y = 1080 - 48 - ySize + 1;
		}
		return;
	}

}
