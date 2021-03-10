package ksato.kiradol.models;

class Follower extends People
{
	protected int myPoint;
	protected String myName;
	// protected IIdol heroine;

	Follower( )
	{
		return;
	}
	
	int getMyPoint( )
	{
		return myPoint;
	}
	
	String getMyName( )
	{
		return myName;
	}

	@Override
	void move( )
	{
		return;
	}

}
