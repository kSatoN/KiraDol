package ksato.kiradol.controllers;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by fyoshida on 2018/07/04.
 * Modified by ksato.
 */

public class TouchController implements View.OnTouchListener
{
	public View touchView;
	public MotionEvent touchEvent;
	
	@Override
	public boolean onTouch(View v, MotionEvent event)
	{
		if (event.getAction( ) == MotionEvent.ACTION_DOWN)
		{
			touchView = v;
			touchEvent = event;
		}
		else if(event.getAction( ) == MotionEvent.ACTION_UP)
		{
			touchView = null;
			touchEvent = null;
		}
		return true;
	}
	
}
