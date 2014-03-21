package com.example.hintrace;

import com.example.hindigame.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class HindiMainActivity extends Activity {
	public static int SCREEN_WIDTH;
	public static int SCREEN_HEIGHT;
	int[] varnmalaResource;
	int[] completeVarnmala;
	int[] audio;
	MediaPlayer mediaPlayer;
	static HindiMainActivity hindiMainActivity;
	Animation animAlpha;
	private static int counter = 0;
	Intent in;
	ImageView next,prev;
	Button clear;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		audio=new int[]{R.raw.a, R.raw.aa ,R.raw.e,R.raw.ee,R.raw.newo,R.raw.newoo,R.raw.ree,R.raw.aedi,R.raw.aninak,R.raw.au,R.raw.auu,R.raw.ang,R.raw.ahh};
		varnmalaResource = new int[] { R.drawable.dots_a, R.drawable.dots_aa,R.drawable.dots_e,R.drawable.dots_ee,R.drawable.chottaoo,R.drawable.badaooo,R.drawable.dots_ree,R.drawable.dot_ae,R.drawable.dot_aee,R.drawable.dots_au,R.drawable.dots_auu,R.drawable.dot_an,R.drawable.dots_ann};
		completeVarnmala = new int[] { R.drawable.a, R.drawable.aa,R.drawable.e,R.drawable.ee,R.drawable.o,R.drawable.oo,R.drawable.ree,R.drawable.ae,R.drawable.aee,R.drawable.au,R.drawable.auu,R.drawable.an,R.drawable.ann};
		DisplayMetrics mDisplayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
		setContentView(R.layout.activity_main);
		hindiMainActivity=this;
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		counter = 0;
		SCREEN_HEIGHT = mDisplayMetrics.heightPixels;
		SCREEN_WIDTH = mDisplayMetrics.widthPixels;
		clear =(Button)findViewById(R.id.clear);
		startGame(R.drawable.dots_a, R.drawable.a);
		startAudio(R.raw.a);	
		animAlpha=AnimationUtils.loadAnimation(this,R.anim.anim_alpha);
		/* Functionality for clicking to next button 
		 * Swar akshar
		 * It will reload the canvas screen 
		 * *
		 */
		next=(ImageView)findViewById(R.id.next);
		next.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				next.startAnimation(animAlpha);
				if(counter==0 || counter<12)
				{
					counter++;
					if (counter < varnmalaResource.length)
					{
						startGame(varnmalaResource[counter],
								completeVarnmala[counter]);
						startAudio(audio[counter]);
					}
					if(counter>12)
					{
						Intent i = new Intent(getApplicationContext(), PlayAgain.class);
						startActivity(i);
						finish();
					}
				}
				else
				{
					Intent in = new Intent(getApplicationContext(), PlayAgain.class);
					startActivity(in);
					finish();
				}

			}
		});
		prev=(ImageView)findViewById(R.id.prev);
		prev.setOnClickListener(new OnClickListener() 
		{

			@Override
			public void onClick(View v) {
				prev.startAnimation(animAlpha);
				if(counter>0 && counter <13)
				{
					counter--;
					if (counter >= 0 && counter<13)
					{
						startGame(varnmalaResource[counter],completeVarnmala[counter]);
						startAudio(audio[counter]);
					}
				}
				else
				{
					Intent in1=new Intent(getApplicationContext(),SecondMain.class);
					startActivity(in1);
					finish();
				}
			}
		});
		clear.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				clear.startAnimation(animAlpha);
				startGame(varnmalaResource[counter],completeVarnmala[counter]);
			}
		});
	}

	private void startGame(int resource, int complete ) 
	{  
		ImageView mImageView = (ImageView) findViewById(R.id.imageView1);
		Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), complete);
		mImageView.setImageBitmap(mBitmap);
		LinearLayout mLayout = (LinearLayout) findViewById(R.id.lc);
		CustomDrawingView mCustomDrawingView = new CustomDrawingView(getApplicationContext(), resource);
		mCustomDrawingView.invalidate();
		mLayout.removeAllViews();
		mLayout.addView(mCustomDrawingView);
	}
	private void startAudio(int audio)
	{
		if(mediaPlayer!=null)
		{
			mediaPlayer.reset();  
		}
		mediaPlayer = MediaPlayer.create(HindiMainActivity.this, audio);
		mediaPlayer.start();
	}
	@Override
	public void onBackPressed() 
	{
		Intent in1=new Intent(getApplicationContext(),SecondMain.class);
		startActivity(in1);
		finish();
	}
}
