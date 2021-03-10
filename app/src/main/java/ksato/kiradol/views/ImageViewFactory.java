package ksato.kiradol.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.LinkedList;

class ImageViewFactory extends BaseView
{
	///TODO 足場とかの数が変わったら配列長変更
	private Context context;
	private ConstraintLayout layout;
	private ImageView imageViewFloor;
	private ImageView imageViewIdol;
	private ImageView[ ] imageViewsKiraidaaa = new ImageView[3];
	private ImageView[ ] imageViewsKiraKira = new ImageView[8];
	private ImageView imageViewStation;
	private ImageView[ ] imageViewsPlatform = new ImageView[6];
	private LinkedList<ImageView> imageViews;
	private int countOfPlatform;
	private int countOfKiraKira;
	private int countOfKiraidaaa;
	
	ImageViewFactory(Context context)
	{
		super(context);
		this.context = context;
		this.layout = activity.findViewById(ksato.kiradol.R.id.constraintLayoutActivityMain);
		initialize( );
	}
	
	void initialize( )
	{
		createImageViews(context);
		setList( );
		addLayout( );
		resetCounts( );
		return;
	}
	
	private void createImageViews(Context context)
	{
		imageViewFloor = new ImageView(context);
		imageViewIdol = new ImageView(context);
		for(int i = 0; i < imageViewsKiraidaaa.length; i += 1)
		{
			imageViewsKiraidaaa[i] = new ImageView(context);
		}
		for(int i = 0; i < imageViewsKiraKira.length; i+= 1)
		{
			imageViewsKiraKira[i] = new ImageView(context);
		}
		imageViewStation = new ImageView(context);
		for(int i = 0; i < imageViewsPlatform.length; i += 1)
		{
			imageViewsPlatform[i] = new ImageView(context);
		}
		return;
	}
	
	private void setList( )
	{
		imageViews = new LinkedList<ImageView>( );
		imageViews.add(imageViewFloor);
		imageViews.add(imageViewIdol);
		imageViews.addAll(Arrays.asList(imageViewsKiraidaaa));
		imageViews.addAll(Arrays.asList(imageViewsKiraKira));
		imageViews.add(imageViewStation);
		imageViews.addAll(Arrays.asList(imageViewsPlatform));
		return;
	}
	
	private void addLayout( )
	{
		for(ImageView imageView: imageViews)
		{
			layout.addView(imageView, 1);
		}
		return;
	}
	
	void clear( )
	{
		if(imageViews.isEmpty( ))
		{
			return;
		}
		for(ImageView imageView: imageViews)
		{
			layout.removeView(imageView);
		}
		imageViews = new LinkedList<ImageView>( );
		return;
	}
	
	void resetCounts( )
	{
		countOfPlatform = -1;
		countOfKiraKira = -1;
		countOfKiraidaaa = -1;
		return;
	}
	
	@NonNull
	ImageView getImageView(ksato.kiradol.models.IGameCharacter gameCharacter)
	{
		if(gameCharacter instanceof ksato.kiradol.models.Floor)
		{
			return imageViewFloor;
		}
		if(gameCharacter instanceof ksato.kiradol.models.IIdol)
		{
			return imageViewIdol;
		}
		if(gameCharacter instanceof ksato.kiradol.models.KiraidaaaBuilder)
		{
			countOfKiraidaaa += 1;
			return imageViewsKiraidaaa[countOfKiraidaaa];
		}
		if(gameCharacter instanceof ksato.kiradol.models.KiraKira)
		{
			countOfKiraKira += 1;
			return imageViewsKiraKira[countOfKiraKira];
		}
		if(gameCharacter instanceof ksato.kiradol.models.Station)
		{
			return imageViewStation;
		}
		if(gameCharacter instanceof ksato.kiradol.models.Platform)
		{
			countOfPlatform += 1;
			return imageViewsPlatform[countOfPlatform];
		}
		// バグでここまでで抜けれなかったらFloorを返す。
		return imageViewFloor;
	}
	
}
