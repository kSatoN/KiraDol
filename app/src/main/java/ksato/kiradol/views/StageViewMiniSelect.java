package ksato.kiradol.views;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import ksato.kiradol.R;
import ksato.kiradol.controllers.ActivityMini;
import ksato.kiradol.controllers.ActivityMiniSelect;
import ksato.kiradol.models.FileIO;
import ksato.kiradol.models.WorldMini;


public class StageViewMiniSelect extends BaseView
{
	Button buttonSelectOk;
	TextView textViewCm;
	public Button getButtonSelectOk( )
	{
		return buttonSelectOk;
	}
	
	public StageViewMiniSelect(ActivityMiniSelect context, WorldMini world)
	{
		super(context);
		activity.setContentView(ksato.kiradol.R.layout.activity_mini_select);
		setViews(context, world);
	}
	
	private void setViews(ActivityMiniSelect activity, WorldMini world)
	{
		buttonSelectOk = (Button) activity.viewById(R.id.buttonSelectOk);
		FileIO fileIO = activity.getFileIO( );
		long count = Long.parseLong(fileIO.readOneLine(fileIO.R_PATH_OF_COUNT_OF_RUN));
		String cm = activity.getString(R.string.cm) + "\nプレイ回数：" + Long.toString(count) + " 回";
		textViewCm = (TextView) activity.viewById(R.id.TextViewCm);
		textViewCm.setText(cm);
		return;
	}
	
	
}
