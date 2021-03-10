package ksato.kiradol.models;

import java.util.LinkedList;


public class PointManager
{
	private ksato.kiradol.controllers.BaseActivity activity;
	private FileIO fileIO;
	private LinkedList<Follower> followers = new LinkedList<Follower>( );
	private long kiraKiraPoint;
	private long stageKiraKiraPoint;
	private long nowAddPoint;
	private int stage;
	public long getKiraKiraPoint( )
	{
		return kiraKiraPoint;
	}
	public long getStageKiraKiraPoint( )
	{
		return stageKiraKiraPoint;
	}
	
	PointManager(ksato.kiradol.controllers.BaseActivity activityMain, int stageNo)
	{
		activity = activityMain;
		fileIO = new FileIO(activity);
		stage = stageNo;
		switch(stage)
		{
			case 1:
				kiraKiraPoint = 0;
				fileIO.writeTextFile(fileIO.R_PATH_OF_FOLLOWER, "\n");
				break;
			default:
				kiraKiraPoint = initializePoint( );
				setFollowers( );
				break;
		}
		stageKiraKiraPoint = 0;
		nowAddPoint = setNowAddPoint( );
		return;
	}
	
	private long initializePoint( )
	{
		if(!fileIO.existFile(fileIO.A_PATH_OF_NOW_POINT))
		{
			fileIO.writeTextFile(fileIO.R_PATH_OF_NOW_POINT, "0\n");
		}
		return Long.parseLong(fileIO.readOneLine(fileIO.R_PATH_OF_NOW_POINT));
	}
	
	private void setFollowers( )
	{
		// 前ステージ終了までに誰がフォヨーしたか。
		if(kiraKiraPoint >= 580)
		{
			followers.add(new FMiri( ));
		}
		if(kiraKiraPoint >= 260)
		{
			followers.add(new FMia( ));
		}
		if(kiraKiraPoint >= 80)
		{
			followers.add(new FSaki( ));
		}
		if(kiraKiraPoint >= 60)
		{
			followers.add(new FRuka( ));
		}
		return;
	}
	
	private Follower setAFollower( )
	{
		if(kiraKiraPoint >= 580)
		{
			return new FMiri( );
		}
		if(kiraKiraPoint >= 260)
		{
			return new FMia( );
		}
		if(kiraKiraPoint >= 80)
		{
			return new FSaki( );
		}
		if(kiraKiraPoint >= 60)
		{
			return new FRuka( );
		}
		return null;
	}
	
	public String getNextFollower( )
	{
		// このステージ終了時に誰がフォヨーするか。
		final String previousFollower = fileIO.readOneLine(fileIO.R_PATH_OF_FOLLOWER);
		final Follower nextFollower = setAFollower( );
		if(nextFollower == null)
		{
			return "";
		}
		if(!previousFollower.equals(nextFollower.getMyName( )))
		{
			fileIO.writeTextFile(fileIO.R_PATH_OF_FOLLOWER, nextFollower.getMyName( ) + "\n");
			return nextFollower.getMyName( );
		}
		return "";
	}
	
	private long setNowAddPoint( )
	{
		long nowAddPoint = 10;
		if(followers.isEmpty( ))
		{
			return nowAddPoint;
		}
		for(Follower follower: followers)
		{
			if(nowAddPoint < follower.getMyPoint( ))
			{
				nowAddPoint = follower.getMyPoint( );
			}
		}
		return nowAddPoint;
	}
	
	public void addPoint( )
	{
		stageKiraKiraPoint += nowAddPoint;
		kiraKiraPoint += nowAddPoint;
		return;
	}
	
	public void savePoint( )
	{
		fileIO.writeTextFile(fileIO.R_PATH_OF_NOW_POINT, Long.toString(kiraKiraPoint) + "\n" );
		return;
	}
	
}
