package ksato.kiradol.views;

import android.content.Context;
import android.graphics.Bitmap;
import ksato.kiradol.R;


class BitmapFactoryMain extends BaseView
{
	// リソース（画像）
	private Bitmap imageFloorFlat;
	private Bitmap imageIdol;
	private Bitmap imagePlatform;
	private Bitmap[ ] imageKiraKira = new Bitmap[4];
	private Bitmap imageKiraidaaa;
	private Bitmap imageStation;
	
	BitmapFactoryMain(Context context)
	{
		super(context);
		setImage( );
	}
	
	private void setImage( )
	{
		imageFloorFlat = loadImage(R.mipmap.floor_flat, 1920, 48);
		imageIdol = loadImage(R.mipmap.miyu, 96, 159);
		imagePlatform = loadImage(R.mipmap.platform, 256, 48);
		imageKiraKira[0] = loadImage(R.mipmap.kira_kira, 96, 96);
		imageKiraidaaa = loadImage(R.mipmap.kiraidaaa, 167, 180);
		imageStation = loadImage(R.mipmap.station, 130, 180);
		return;
	}
	
	Bitmap getImage(ksato.kiradol.models.IGameCharacter gameCharacter)
	{
		if(gameCharacter instanceof ksato.kiradol.models.Floor)
		{
			return getImageCharacter( (ksato.kiradol.models.Floor) gameCharacter);
		}
		else if(gameCharacter instanceof ksato.kiradol.models.Miyu)
		{
			return getImageCharacter( (ksato.kiradol.models.Miyu) gameCharacter);
		}
		else if(gameCharacter instanceof ksato.kiradol.models.Platform)
		{
			return getImageCharacter( (ksato.kiradol.models.Platform) gameCharacter);
		}
		else if(gameCharacter instanceof ksato.kiradol.models.KiraKira)
		{
			return getImageCharacter( (ksato.kiradol.models.KiraKira) gameCharacter);
		}
		else if(gameCharacter instanceof ksato.kiradol.models.KiraidaaaBuilder)
		{
			return getImageCharacter( (ksato.kiradol.models.KiraidaaaBuilder) gameCharacter);
		}
		else if(gameCharacter instanceof ksato.kiradol.models.Station)
		{
			return getImageCharacter( (ksato.kiradol.models.Station) gameCharacter);
		}
		return null;
	}
	
	private Bitmap getImageCharacter(ksato.kiradol.models.Floor floor)
	{
		return imageFloorFlat;
	}
	
	private Bitmap getImageCharacter(ksato.kiradol.models.Miyu miyu)
	{
		if(miyu.isPerfectlyDead( ))
		{
			return null;
		}
		return imageIdol;
	}
	
	private Bitmap getImageCharacter(ksato.kiradol.models.Platform platform)
	{
		return imagePlatform;
	}
	
	private Bitmap getImageCharacter(ksato.kiradol.models.KiraKira kiraKira)
	{
		if(!kiraKira.isRemained( ))
		{
			return null;
		}
		return imageKiraKira[kiraKira.getState( )];
	}
	
	private Bitmap getImageCharacter(ksato.kiradol.models.KiraidaaaBuilder kiraidaaa)
	{
		if(kiraidaaa.isDead( ))
		{
			return null;
		}
		return imageKiraidaaa;
	}
	
	private Bitmap getImageCharacter(ksato.kiradol.models.Station station)
	{
		return imageStation;
	}
	
}
