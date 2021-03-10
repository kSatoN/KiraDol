package ksato.kiradol.models;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import ksato.kiradol.controllers.BaseActivity;


public class FileIO
{
	private BaseActivity activity;
	public final String R_PATH_OF_COUNT_OF_RUN;
	public final String A_PATH_OF_COUNT_OF_RUN;
	public final String R_PATH_OF_NOW_POINT;
	public final String A_PATH_OF_NOW_POINT;
	public final String R_PATH_OF_FOLLOWER;
	public final String A_PATH_OF_FOLLOWER;
	
	public FileIO(BaseActivity baseActivity)
	{
		activity = baseActivity;
		R_PATH_OF_COUNT_OF_RUN = "CountOfRun.txt";
		A_PATH_OF_COUNT_OF_RUN = activity.getFilesDir( ).getAbsolutePath( ) + "/" + R_PATH_OF_COUNT_OF_RUN;
		R_PATH_OF_NOW_POINT = "NowKira-KiraPoint.txt";
		A_PATH_OF_NOW_POINT = activity.getFilesDir( ).getAbsolutePath( ) + "/" + R_PATH_OF_NOW_POINT;
		R_PATH_OF_FOLLOWER = "Follower.txt";
		A_PATH_OF_FOLLOWER = activity.getFilesDir( ).getAbsolutePath( ) + "/" + R_PATH_OF_FOLLOWER;
		return;
	}
	
	public boolean existFile(String absoluteFilePath)
	{
		File file = new File(absoluteFilePath);
		return file.exists( );
	}
	
	@TargetApi(Build.VERSION_CODES.KITKAT)
	public String readOneLine(String relativeFilePath)
	{
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
		{
			try(FileInputStream fileInputStream = activity.openFileInput(relativeFilePath);
				InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader))
			{
				String oneLine = bufferedReader.readLine( );
				bufferedReader.close( );
				inputStreamReader.close( );
				fileInputStream.close( );
				return oneLine;
			}
			catch(IOException e)
			{
				e.printStackTrace( );
			}
		}
		return "";
	}
	
	@TargetApi(Build.VERSION_CODES.KITKAT)
	public void writeTextFile(String relativeFilePath, String content)
	{
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
		{
			try(FileOutputStream fileOutputStream = activity.openFileOutput(relativeFilePath, Context.MODE_PRIVATE))
			{
				fileOutputStream.write(content.getBytes( ));
				fileOutputStream.close( );
			}
			catch(IOException e)
			{
				e.printStackTrace( );
			}
		}
		return;
	}
	
}
