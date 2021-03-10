package ksato.kiradol.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import ksato.kiradol.R;
import ksato.kiradol.models.EClear;
import ksato.kiradol.models.EGameOver;
import ksato.kiradol.models.IGameCharacter;
import ksato.kiradol.models.IWorldMain;


public class StageViewMain extends BaseView
{
	private int lowPassedXScroll;
	private final double filterWeight = 0.92;
	private boolean wasRight;
	private Devices devices;
	private BitmapFactoryMain bitmapFactoryMain;
	private ImageViewFactory imageViewFactory;
	private TextView textViewKiraKiraPoint;
	private TextView textViewGameOver;
	private TextView textViewClear;
	private TextView textViewNextChara;
	private Button buttonRetry;
	private Button buttonNext;
	private ImageView imageViewJump;
	private ImageView imageViewGameOver;
	public Button getButtonRetry( )
	{
		return buttonRetry;
	}
	public Button getButtonNext( )
	{
		return buttonNext;
	}
	public ImageView getImageViewJump( )
	{
		return imageViewJump;
	}
	
	public StageViewMain(Context context)
	{
		super(context);
		activity.setContentView(ksato.kiradol.R.layout.activity_main);
		setResourcesAndViews(context);
		setTextSize( );
		lowPassedXScroll = 0;
		wasRight = true;
		renderJumpButton( );
		return;
	}
	
	private void setResourcesAndViews(Context context)
	{
		bitmapFactoryMain = new BitmapFactoryMain(context);
		imageViewFactory = new ImageViewFactory(context);
		textViewKiraKiraPoint = (TextView) activity.viewById(R.id.textViewKiraKiraPoint);
		textViewGameOver = (TextView) activity.viewById(R.id.textViewGameOver);
		textViewClear = (TextView) activity.viewById(R.id.textViewClear);
		textViewNextChara = (TextView) activity.viewById(R.id.textViewNextChara);
		buttonRetry = (Button) activity.viewById(R.id.buttonRetry);
		buttonNext = (Button) activity.viewById(R.id.buttonNext);
		imageViewJump = (ImageView) activity.viewById(R.id.imageViewJump);
		imageViewGameOver = (ImageView) activity.viewById(R.id.imageViewGameOver);
		return;
	}
	
	private void setTextSize( )
	{
		devices = new Devices(this);
		float textSize = (float) (24.0 * devices.getTextScale( ));
		textViewKiraKiraPoint.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
		return;
	}
	
	private void renderJumpButton( )
	{
		renderImage(canvasBaseX + 1745, canvasBaseY + 898, 175, 182, loadImage(R.mipmap.button_jump, 175, 182), imageViewJump);
		return;
	}
	
	// 描画
	public void render(IWorldMain world)
	{
		imageViewFactory.resetCounts( );
		horizontalScroll(world);
		renderModels(world);
		renderKiraKiraPoint(world);
		return;
	}
	
	private void horizontalScroll(IWorldMain world)
	{
		lowPassedXScroll = (int) ( (filterWeight * (double) lowPassedXScroll)  + ( (1 - filterWeight) * (double) goalCanvasBaseX(world)) );
		canvasBaseX = lowPassedXScroll;
		if(canvasBaseX < 0)
		{
			canvasBaseX = 0;
		}
		else if(world.WIDTH - canvasWindowWidth + 67 < canvasBaseX)
		{
			canvasBaseX = world.WIDTH - canvasWindowWidth + 67;
		}
		world.getFloor( ).setX(canvasBaseX);
		return;
	}
	
	private int goalCanvasBaseX(IWorldMain world)
	{
		int goalCanvasBaseX = 0;
		if(0.10 < world.getIdol( ).getxVelocity( ))
		{
			goalCanvasBaseX = world.getIdol( ).getX( ) - 640;
			wasRight = true;
		}
		else if(world.getIdol( ).getxVelocity( ) < -0.10)
		{
			goalCanvasBaseX = world.getIdol( ).getX( ) - 1024;
			wasRight = false;
		}
		else
		{
			if(wasRight)
			{
				goalCanvasBaseX = world.getIdol( ).getX( ) - 640;
			}
			else
			{
				goalCanvasBaseX = world.getIdol( ).getX( ) - 1024;
			}
		}
		return goalCanvasBaseX;
	}
	
	private void renderModels(IWorldMain world)
	{
		for(IGameCharacter gameCharacter: world.getGameCharacters( ))
		{
			renderCharacter(gameCharacter, imageViewFactory.getImageView(gameCharacter));
		}
		return;
	}
	
	private void renderCharacter(IGameCharacter gameCharacter, ImageView imageView)
	{
		Bitmap bitmap = bitmapFactoryMain.getImage(gameCharacter);
		if(bitmap != null)
		{
			renderImage(gameCharacter.getX( ), gameCharacter.getY( ), gameCharacter.getxSize( ), gameCharacter.getySize( ), bitmap, imageView);
		}
		else
		{
			imageView.setVisibility(GONE);
		}
		bitmap = null;
		return;
	}
	
	private void renderKiraKiraPoint(IWorldMain world)
	{
		String point = Long.toString(world.getPointManager( ).getKiraKiraPoint( )) + activity.getString(R.string.TextKiraKiraPoint);
		textViewKiraKiraPoint.setText(point);
		textViewKiraKiraPoint.setX( (float) devices.convertModelXToPhysicalX(1853) - (float) textViewKiraKiraPoint.getWidth( ));
		textViewKiraKiraPoint.setY( (float) devices.convertModelYToPhysicalY(38));
		return;
	}
	
	public void renderGameOver(EGameOver gameOver)
	{
		imageViewGameOver.setVisibility(VISIBLE);
		textViewGameOver.setVisibility(VISIBLE);
		buttonRetry.setVisibility(VISIBLE);
		return;
	}
	
	public void renderStageClear(String follower)
	{
		imageViewGameOver.setVisibility(VISIBLE);
		textViewClear.setVisibility(VISIBLE);
		notificationManagement(follower);
		buttonNext.setVisibility(VISIBLE);
		return;
	}
	
	private void notificationManagement(String follower)
	{
		if(follower.equals(""))
		{
			notificationOfNoFollower( );
		}
		else
		{
			notificationOfFollower(follower);
		}
		return;
	}
	
	private void notificationOfNoFollower( )
	{
		textViewNextChara.setText(R.string.TextViewNoNextChara);
		textViewNextChara.setVisibility(VISIBLE);
	}
	
	private void notificationOfFollower(String follower)
	{
		String notification = follower + activity.getString(R.string.TextViewNextChara);
		textViewNextChara.setText(notification);
		textViewNextChara.setVisibility(VISIBLE);
		return;
	}
	
}
