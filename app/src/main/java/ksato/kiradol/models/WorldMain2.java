package ksato.kiradol.models;

import ksato.kiradol.controllers.ActivityMain;

import static ksato.kiradol.models.ETypeOfKiraidaaa.GoAfter;
import static ksato.kiradol.models.ETypeOfKiraidaaa.Moving;
import static ksato.kiradol.models.ETypeOfKiraidaaa.Static;
import static ksato.kiradol.models.KiraidaaaBuilder.newKiraidaaa;


public class WorldMain2 extends WorldMain implements IWorldMain
{
	public WorldMain2(ActivityMain activityMain)
	{
		super(activityMain);
	}
	
	@Override
	protected void createObjects( )
	{
		pointManager = new PointManager(activity, 2);
		floor = new Floor( );
		idol = new Miyu(this);
		platforms.add(new Platform(1400, 848, 1));
		platforms.add(new Platform(1660, 540, 2));
		platforms.add(new Platform(2560, 774, 3));
		pKiraKira.add(new KiraKira(platforms.get(1).getX( ) + platforms.get(1).getxSize( ) / 2 - 48, platforms.get(1).getY( ) - 96 - 74, true, getPointManager( )));
		pKiraKira.add(new KiraKira(platforms.get(2).getX( ) + platforms.get(2).getxSize( ) / 2 - 48, platforms.get(2).getY( ) - 96, true, getPointManager( )));
		pKiraKira.add(new KiraKira(500, 896, false, getPointManager( )));
		pKiraKira.add(new KiraKira(3200, 856, false, getPointManager( )));
		pKiraKira.add(new KiraKira(7024, 896, false, getPointManager( )));
		pKiraidaaa.add(newKiraidaaa(2800, 1080 - 48 - 180, Moving));
		pKiraidaaa.add(newKiraidaaa(3400, 1080 - 48 - 180, GoAfter));
		station = new Station(3500, 840, getIdol( ), this, getPointManager( ));
	}
	
}
