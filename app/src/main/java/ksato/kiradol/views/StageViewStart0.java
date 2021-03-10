package ksato.kiradol.views;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.ImageView;

import ksato.kiradol.R;


public class StageViewStart0 extends BaseView
{
	ConstraintLayout constraintLayout;
	ImageView imageViewString;
	Button buttonToExplanation;
	Button buttonToStartGame;
	int stringAlpha;
	int idolTime;
	public ConstraintLayout getConstraintLayout( )
	{
		return constraintLayout;
	}
	public Button getButtonToExplanation( )
	{
		return buttonToExplanation;
	}
	public Button getButtonToStartGame( )
	{
		return buttonToStartGame;
	}
	
	public StageViewStart0(Context context)
	{
		super(context);
		activity.setContentView(ksato.kiradol.R.layout.activity_start0);
		setViews( );
		setTextSizes( );
		stringAlpha = 255;
		idolTime = 0;
		return;
	}
	
	private void setViews( )
	{
		constraintLayout = (ConstraintLayout) activity.viewById(R.id.constraintLayoutStart0);
		imageViewString = (ImageView) activity.viewById(R.id.imageViewString);
		buttonToExplanation = (Button) activity.viewById(R.id.buttonToExplanation);
		buttonToStartGame = (Button) activity.viewById(R.id.buttonToStartGame);
		return;
	}
	
	private void setTextSizes( )
	{
		Devices devices = new Devices(this);
		float textSize = (float) (24.0 * devices.getTextScale( ));
		buttonToExplanation.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
		buttonToStartGame.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
		return;
	}
	
	// 描画
	public void draw( )
	{
		if(0 <= idolTime && idolTime <= 119)
		{
			idolTime += 1;
		}
		else if(idolTime == 120)
		{
			idolTime = 1;
		}
		flicker( );
		return;
	}
	
	private void flicker( )
	{
		if(1 <= idolTime && idolTime <= 120)
		{
			stringAlpha = (int) (127.5 * Math.cos( (Math.PI / 60) * idolTime) + 127.5);
		}
		imageViewString.setImageAlpha(stringAlpha);
		return;
	}
	
	public void InvisibleIImageViewString( )
	{
		imageViewString.setVisibility(INVISIBLE);
		return;
	}
	
	public void VisibleButtonMenu( )
	{
		buttonToExplanation.setVisibility(VISIBLE);
		buttonToStartGame.setVisibility(VISIBLE);
		return;
	}
	
}
