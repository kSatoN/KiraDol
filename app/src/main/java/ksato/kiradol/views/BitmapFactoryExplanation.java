package ksato.kiradol.views;

import android.content.Context;
import android.graphics.Bitmap;

import ksato.kiradol.R;


public class BitmapFactoryExplanation extends BaseView
{
	// リソース
	private Bitmap imageExplanationMiyu;
	private Bitmap[ ] imageExplanationPri = new Bitmap[5];
	
	public BitmapFactoryExplanation(Context context)
	{
		super(context);
		// リソースの取得
		setResources( );
		return;
	}
	
	private void setResources( )
	{
		imageExplanationMiyu = loadImage(R.mipmap.explanation_miyu);
		imageExplanationPri[0] = loadImage(R.mipmap.pripri);
		imageExplanationPri[1] = loadImage(R.mipmap.screen2);
		imageExplanationPri[2] = loadImage(R.mipmap.screen345);
		imageExplanationPri[3] = loadImage(R.mipmap.screen6);
		imageExplanationPri[4] = loadImage(R.mipmap.screen78);
		return;
	}
	
	// アクセッサ
	public Bitmap getImage(int type)
	{
		switch(type)
		{
			case -1:
				return imageExplanationMiyu;
			case 1:
				return imageExplanationPri[0];
			case 2:
				return imageExplanationPri[1];
			case 3:
				return imageExplanationPri[2];
			case 4:
				return imageExplanationPri[2];
			case 5:
				return imageExplanationPri[2];
			case 6:
				return imageExplanationPri[3];
			case 7:
				return imageExplanationPri[4];
			case 8:
				return imageExplanationPri[4];
			default:
				throw new NumberFormatException( );
		}
	}
	
}
