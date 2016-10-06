package com.example.mtxx;

import com.mt.mtxx.image.JNI;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private ImageView iv;
	private JNI jni;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (ImageView) findViewById(R.id.iv);
		jni = new JNI();
	}
	
	public void lomo(View view){
		
		//将图片的像素信息保存到数组中
		
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.face);
		
		int[] pixels = new int[bitmap.getWidth()*bitmap.getHeight()];
		/**
		 * 参数4和5：从bitmap的哪个像素点开始读取
		 */
		bitmap.getPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
		
		/*
		 * 参数1：像素信息
		 * 参数2和3：图片的宽和高
		 */
		jni.StyleLomoB(pixels, bitmap.getWidth(), bitmap.getHeight());
		
		//需要重新将pixels转换为Bitmap对象，然后设置给ImageView
		Bitmap bitmap2 = Bitmap.createBitmap(pixels, bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
		iv.setImageBitmap(bitmap2);
		
		
	}
	
	public void faceBeauty(View view){
		
		final ProgressDialog progressDialog = new ProgressDialog(this);
		progressDialog.setMessage("正在美颜中，给我一分钟，还你个微辣！");
		progressDialog.show();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				//将图片的像素信息保存到数组中
				
				Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.face);
				
				int[] pixels = new int[bitmap.getWidth()*bitmap.getHeight()];
				/**
				 * 参数4和5：从bitmap的哪个像素点开始读取
				 */
				bitmap.getPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
				
				/*
				 * 参数1：像素信息
				 * 参数2和3：图片的宽和高
				 * 参数4：美颜等级
				 */
				jni.FaceBeauty(pixels, bitmap.getWidth(), bitmap.getHeight(),100);
				
				//需要重新将pixels转换为Bitmap对象，然后设置给ImageView
				final Bitmap bitmap2 = Bitmap.createBitmap(pixels, bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
				
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						iv.setImageBitmap(bitmap2);
					}
				});
				
				progressDialog.dismiss();
			}
		}).start();
	}
	
	public void origin(View view){
		iv.setImageResource(R.drawable.face);
	}

}
