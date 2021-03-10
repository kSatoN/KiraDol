package ksato.kiradol.views;

import android.content.Context;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.TextView;

import ksato.kiradol.R;


public class StageViewExplanation extends BaseView
{
	private ImageView imageViewExplanationImage;
	private ImageView imageViewStringNext;
	private TextView textViewString;
	private BitmapFactoryExplanation imageFactory;
	
	public ImageView getImageViewStringNext( )
	{
		return imageViewStringNext;
	}
	
	public StageViewExplanation(Context context)
	{
		super(context);
		activity.setContentView(R.layout.activity_explanation);
		imageFactory = new BitmapFactoryExplanation(context);
		findView( );
		setTextSize( );
		return;
	}
	
	private void findView( )
	{
		imageViewExplanationImage = (ImageView) activity.viewById(R.id.imageViewExplanationImage);
		imageViewStringNext = (ImageView) activity.viewById(R.id.imageViewStringNext);
		textViewString = (TextView) activity.viewById(R.id.textViewString);
		return;
	}
	
	private void setTextSize( )
	{
		Devices devices = new Devices(this);
		float textSize = (float) (20.0 * devices.getTextScale( ));
		textViewString.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
		return;
	}
	
	// 描画
	public void drawString(int drawString)
	{
		textViewString.setText(drawString);
		return;
	}
	
	public void drawImage(int imageType)
	{
		imageViewExplanationImage.setImageBitmap(imageFactory.getImage(imageType));
		return;
	}
	
}
