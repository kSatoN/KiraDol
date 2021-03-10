package ksato.kiradol.models;

import ksato.kiradol.controllers.ActivityMini;
import ksato.kiradol.controllers.BaseActivity;


public class WorldMini implements IWorld
{
	private BaseActivity activity;
	private PointManager pointManager;
	public PointManager getPointManager( )
	{
		return pointManager;
	}
	
	public WorldMini(BaseActivity activityMini)
	{
		activity = activityMini;
		pointManager = new PointManager(activity, 0);
		return;
	}
	
	@Override
	public void move( )
	{
	
	}
	
}
