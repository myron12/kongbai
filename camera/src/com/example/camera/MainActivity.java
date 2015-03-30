package com.example.camera;

import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity implements Callback ,View.OnClickListener, PictureCallback{

	private SurfaceView surface;
	private Camera camera;
	private ImageView imageview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		surface = (SurfaceView) findViewById(R.id.surface);
		camera = Camera.open();
		surface.getHolder().addCallback(this);
		findViewById(R.id.button1).setOnClickListener(this);
		imageview = (ImageView) findViewById(R.id.imageview);
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder surfaceHolder) {
		try {
			//设置预览
			camera.setPreviewDisplay(surfaceHolder);
			//设置显示方向（只能是90的整数倍）
			camera.setDisplayOrientation(90);
			//开始预览
			camera.startPreview();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View arg0) {
		//拍照
		camera.takePicture(null, null, this);
	}
	
	/**
	 * 拍照后回调方法
	 * bytes 画片数据
	 */
	@Override
	public void onPictureTaken(byte[] bytes, Camera arg1) {
		Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
		imageview.setImageBitmap(bitmap);
		camera.startPreview();
	}
}
