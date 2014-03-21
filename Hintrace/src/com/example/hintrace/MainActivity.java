package com.example.hintrace;

import com.example.hindigame.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
Button play;
Button exit;
Button instruction;
Animation animAlpha;
static MainActivity mainActivity;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		mainActivity=this;
		play=(Button)findViewById(R.id.playBtn);
		instruction=(Button)findViewById(R.id.kaisekhelenBtn);
		exit=(Button)findViewById(R.id.exit);
		animAlpha=AnimationUtils.loadAnimation(this,R.anim.anim_alpha);
		play.setOnClickListener(new View.OnClickListener() 
		{		
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				play.startAnimation(animAlpha);
				Intent in= new Intent (MainActivity.this,SecondMain.class);
				startActivity(in);
			}
		});
         instruction.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View arg0) {
				instruction.startAnimation(animAlpha);
				Intent in1= new Intent (MainActivity.this,Instruction.class);
				startActivity(in1);
			}
		});
		exit.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				exit.startAnimation(animAlpha);
			   finish();
			   Intent intent = new Intent(Intent.ACTION_MAIN);
			   intent.addCategory(Intent.CATEGORY_HOME);
			   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			   startActivity(intent);
			}
		});
}
	 @Override
	    public boolean onKeyDown(int keyCode, KeyEvent event)
	    {
	        if ((keyCode == KeyEvent.KEYCODE_BACK))
	        {
	        	
	            finish();
	        }
	        return super.onKeyDown(keyCode, event);
	    }


	 @Override
	    public void onBackPressed() 
	    {
	            super.onBackPressed();
	            this.finish();
	    		super.finish();
	    }

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
	    
}