package ksato.kiradol.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.view.View;
import android.widget.ImageView;

public class BaseView extends View
{
	ksato.kiradol.controllers.BaseActivity activity;
	
	// 画面の基準座標
	int canvasBaseX = 0;
	int canvasBaseY = 0;
	
	// 画面の幅、高さ
	final int canvasWindowWidth;
	final int canvasWindowHeight;
	final double dpi;
	
	// デバイスの表示領域
	final int deviceWindowWidth;
	final int deviceWindowHeight;
	
	// 表示倍率
	final float windowScaleX;
	final float windowScaleY;
	
	public BaseView(Context context)
	{
		super(context);
		activity = (ksato.kiradol.controllers.BaseActivity) context;
		
		deviceWindowHeight = activity.getDeviceWindowHeight( );
		deviceWindowWidth = activity.getDeviceWindowWidth( );
		dpi = activity.getDpi( );
		
		if (deviceWindowHeight > deviceWindowWidth)
		{
			//canvasWindowHeight = 600;
			//canvasWindowWidth = 360;
			canvasWindowHeight = 1920;
			canvasWindowWidth = 1080;
		}
		else
		{
			//canvasWindowHeight = 360;
			//canvasWindowWidth = 600;
			canvasWindowHeight = 1080;
			canvasWindowWidth = 1920;
		}
		
		windowScaleX = (float) deviceWindowWidth / canvasWindowWidth;
		windowScaleY = (float) deviceWindowHeight / canvasWindowHeight;
	}
	
	//=========================
	//  画像表示用の関数
	//=========================
	// 画像の読み込み
//	protected Bitmap loadImage(int id)
//	{
//		Bitmap bitmap = BitmapFactory.decodeResource(getResources( ), id);
//		int bitmapXSize = bitmap.getWidth();
//		int bitmapYSize = bitmap.getHeight();
//		Matrix m = new Matrix();
//		return Bitmap.createBitmap(bitmap, 0, 0, bitmapXSize, bitmapYSize, m, true);
//	}
	
	protected Bitmap loadImage(int id)
	{
		Bitmap bitmap = BitmapFactory.decodeResource(getResources( ), id);
		return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth( ), bitmap.getHeight( ));
	}
	
	protected Bitmap loadImage(int id, int xSize, int ySize)
	{
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), id);
		int bitmapXSize = bitmap.getWidth();
		int bitmapYSize = bitmap.getHeight();
		float deviceXSize = (float) xSize / bitmapXSize * windowScaleX;
		float deviceYSize = (float) ySize / bitmapYSize * windowScaleY;
		Matrix m = new Matrix();
		m.postScale(deviceXSize, deviceYSize);
		return Bitmap.createBitmap(bitmap, 0, 0, bitmapXSize, bitmapYSize, m, true);
	}
	
	// 画像の描画
	protected void renderImageWithoutScale(int x, int y, int xSize, int ySize, Bitmap bitmap, ImageView imageView) {
		if (canvasBaseX + canvasWindowWidth - 1 < x || x + xSize-1 < canvasBaseX) {
			return;
		}
		if (canvasBaseY + canvasWindowHeight-1 < y || y + ySize-1 < canvasBaseY) {
			return;
		}
		
		int canvasX = x - canvasBaseX;
		//int canvasY = canvasWindowHeight - ySize - y + canvasBaseY;
		int canvasY = y - canvasBaseY;
		
		int deviceX = (int) (canvasX * windowScaleX);
		int deviceY = (int) (canvasY * windowScaleY);
		
		
		imageView.setImageBitmap(bitmap);
		imageView.setTranslationX(deviceX);
		imageView.setTranslationY(deviceY);
	}
	
	// 画像の描画
	protected void renderImage(int x, int y, int xSize, int ySize, Bitmap bitmap, ImageView imageView) {
		if (canvasBaseX + canvasWindowWidth + xSize < x || x + (2 * xSize) < canvasBaseX)
		{
			return;
		}
		if (canvasBaseY + canvasWindowHeight + ySize < y || y + (2 * ySize) < canvasBaseY)
		{
			return;
		}
		int bitmapXSize = bitmap.getWidth( );
		int bitmapYSize = bitmap.getHeight( );
		
		int canvasX = x - canvasBaseX;
		//int canvasY = canvasWindowHeight - ySize - y + canvasBaseY;
		int canvasY = y - canvasBaseY;
		
		int deviceX = (int) (canvasX * windowScaleX);
		int deviceY = (int) (canvasY * windowScaleY);
		int deviceXSize = (int) (xSize * windowScaleX);
		int deviceYSize = (int) (ySize * windowScaleY);
		
		float xScale = deviceXSize / (float) bitmapXSize;
		float yScale = deviceYSize / (float) bitmapYSize;
		
		Matrix m = new Matrix();
		m.postScale(xScale, yScale);
		imageView.setImageMatrix(m);
		imageView.setImageBitmap(bitmap);
		
		imageView.setTranslationX(deviceX);
		imageView.setTranslationY(deviceY);
		imageView.setScaleX(xScale);
		imageView.setScaleY(yScale);
	}
}
