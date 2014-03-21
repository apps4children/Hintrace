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
import android.os.PowerManager.WakeLock;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class VyanjanActivity extends Activity {

	public static int SCREEN_WIDTH;
	public static int SCREEN_HEIGHT;

	int[] varnmalaResource;
	int[] completeVarnmala;
	int[] audio;
	MediaPlayer mediaPlayer;
	WakeLock wakeLock;
	Animation animAlpha;
	private static int counter = 0;
	Intent in;
	ImageView next,prev;
	Button clear;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		audio=new int[]
				{R.raw.kaa, R.raw.khaa ,R.raw.ga,R.raw.ghar,R.raw.angaa,R.raw.caa,R.raw.chaa,R.raw.jug,R.raw.jhanda,R.raw.eeyaa,R.raw.tamatar,R.raw.thanda,R.raw.damru,R.raw.dhakan,R.raw.adhan,R.raw.ttarboj,R.raw.thermas,R.raw.d_dawat,R.raw.dha_dhanush,R.raw.nal,R.raw.p_patang,R.raw.fa_fal,R.raw.ba_bathak,R.raw.bhalu,R.raw.mala,R.raw.yaa,R.raw.rath,R.raw.lattu,R.raw.va,R.raw.shailjam,R.raw.shaitkon,R.raw.sapera,R.raw.hal,R.raw.ksha,R.raw.triya,R.raw.gyaani};
		varnmalaResource = new int[] 
				{ R.drawable.kamal, R.drawable.kharbhuj,R.drawable.gamla,R.drawable.ghar,R.drawable.aanga,R.drawable.chamach,R.drawable.chatri,R.drawable.jahaj,R.drawable.jhanda,R.drawable.eya,R.drawable.tamatar,R.drawable.thathera,R.drawable.damru,R.drawable.dhakan,R.drawable.adan,R.drawable.tarboj,R.drawable.tharmas,R.drawable.dawat,R.drawable.dhanush,R.drawable.nal,R.drawable.papita,R.drawable.fal,R.drawable.bathak,R.drawable.bhalu,R.drawable.maa,R.drawable.yaa,R.drawable.raa,R.drawable.lattu,R.drawable.vaa,R.drawable.shailjam,R.drawable.shatkon,R.drawable.sapera,R.drawable.hal,R.drawable.akshya_chatiya,R.drawable.trishul,R.drawable.gyaani};
		completeVarnmala = new int[] 
				{ R.drawable.vyn_k, R.drawable.vya_kha,R.drawable.vya_gh,R.drawable.vya_ghar,R.drawable.vya_yang,R.drawable.vya_chamach,R.drawable.vyan_chatri,R.drawable.vya_jug,R.drawable.vyan_flag,R.drawable.vyan_aiyyan,R.drawable.vya_tamator,R.drawable.vya_thanda,R.drawable.vya_damru,R.drawable.vya_dhakkan,R.drawable.ya_rn,R.drawable.vya_tarboj,R.drawable.vya_tha,R.drawable.vya_dawat,R.drawable.vya_dhanush,R.drawable.vyan_na,R.drawable.vya_pa,R.drawable.vyan_pha,R.drawable.vya_baa,R.drawable.vya_bhaa,R.drawable.vya_maa,R.drawable.vya_yaa,R.drawable.vya_raa,R.drawable.vya_laa,R.drawable.vya_vaa,R.drawable.vya_sha,R.drawable.vya_cutsha,R.drawable.vya_sapna,R.drawable.vya_hum,R.drawable.vya_shatriya,R.drawable.vya_triya,R.drawable.vya_ghya};

		DisplayMetrics mDisplayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
		setContentView(R.layout.activity_main);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		counter = 0;
		SCREEN_HEIGHT = mDisplayMetrics.heightPixels;
		SCREEN_WIDTH = mDisplayMetrics.widthPixels;
		clear =(Button)findViewById(R.id.clear);
		startGame( R.drawable.kamal,R.drawable.vyn_k);
		startAudio(R.raw.kaa);
		animAlpha=AnimationUtils.loadAnimation(this,R.anim.anim_alpha);
		next=(ImageView)findViewById(R.id.next);
		next.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg0) {
				next.startAnimation(animAlpha);
				if(counter==0 || counter<35){
					counter++;
					if (counter < varnmalaResource.length) {
						startGame(varnmalaResource[counter],
								completeVarnmala[counter]);
						startAudio(audio[counter]);
					}
					if(counter>35){
						Intent i = new Intent(getApplicationContext(), PlayAgain.class);
						startActivity(i);
						finish();
					}
				}
				else
				{
					startActivity(new Intent(getApplicationContext(),PlayAgain.class));
					finish();

				}

			}
		});
		prev=(ImageView)findViewById(R.id.prev);
		prev.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				prev.startAnimation(animAlpha);
				if(counter>0 && counter <35){
					counter--;
					if (counter >= 0 && counter<35)
					{
						startGame(varnmalaResource[counter],
								completeVarnmala[counter]);	
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
			public void onClick(View v) {
				clear.startAnimation(animAlpha);
				startGame(varnmalaResource[counter],
						completeVarnmala[counter]);			
			}
		});
	}
	private void startGame(int resource, int complete) 
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
	private void startAudio(int audio){
		if(mediaPlayer!=null){
			mediaPlayer.reset();        
		}
		mediaPlayer = MediaPlayer.create(VyanjanActivity.this, audio);
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
