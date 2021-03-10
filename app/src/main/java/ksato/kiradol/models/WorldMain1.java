package ksato.kiradol.models;

import ksato.kiradol.controllers.ActivityMain;
import ksato.kiradol.controllers.BaseActivity;

import static ksato.kiradol.models.ETypeOfKiraidaaa.Static;
import static ksato.kiradol.models.KiraidaaaBuilder.newKiraidaaa;


public class WorldMain1 extends WorldMain implements IWorldMain
{
	public WorldMain1(ActivityMain activityMain)
	{
		super(activityMain);
		return;
	}
	
	@Override
	protected void createObjects( )
	{
		pointManager = new PointManager(activity, 1);
		floor = new Floor( );
		idol = new Miyu(this);
		platforms.add(new Platform(1400, 848, 1));
		platforms.add(new Platform(1660, 540, 2));
		platforms.add(new Platform(2560, 774, 3));
		platforms.add(new Platform(1400 + 3840, 848, 4));
		platforms.add(new Platform(1660 + 3840, 540, 5));
		platforms.add(new Platform(2560 + 3840, 774, 6));
		pKiraKira.add(new KiraKira(500, 896, false, getPointManager( )));
		pKiraKira.add(new KiraKira(platforms.get(1).getX( ) + (platforms.get(1).getxSize( ) / 2) - 48, platforms.get(1).getY( ) - 96 - 74, true, getPointManager( )));
		pKiraKira.add(new KiraKira(platforms.get(2).getX( ) + (platforms.get(2).getxSize( ) / 2) - 48, platforms.get(2).getY( ) - 96, true, getPointManager( )));
		pKiraKira.add(new KiraKira(3200, 856, false, getPointManager( )));
		pKiraKira.add(new KiraKira(4340, 896, false, getPointManager( )));
		pKiraKira.add(new KiraKira(platforms.get(4).getX( ) + (platforms.get(4).getxSize( ) / 2) - 48, platforms.get(4).getY( ) - 96 - 74, true, getPointManager( )));
		pKiraKira.add(new KiraKira(platforms.get(5).getX( ) + (platforms.get(5).getxSize( ) / 3) - 48, platforms.get(5).getY( ) - 96, true, getPointManager( )));
		pKiraKira.add(new KiraKira(3200 + 3840, 856, false, getPointManager( )));
		pKiraidaaa.add(newKiraidaaa(1656, 1080 - 48 - 180, Static));
		pKiraidaaa.add(newKiraidaaa(platforms.get(5).getX( ) + platforms.get(5).getxSize( ) - 60, platforms.get(5).getY( ) - 180, Static));
		station = new Station(7340 - 80, 840, getIdol( ), this, getPointManager( ));
		return;
	}
	
}
