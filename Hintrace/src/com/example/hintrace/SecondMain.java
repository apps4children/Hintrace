package com.example.hintrace;

import com.example.hindigame.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class SecondMain extends Activity implements OnClickListener{
	Animation animAlpha;
	Button b1,b2;
	static SecondMain secondMain;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.second_main);
		secondMain=this;
	     b1= (Button)findViewById(R.id.swar);
	     b1.setOnClickListener(this);
	     b2=(Button)findViewById(R.id.vyanjan);
	     b2.setOnClickListener(this);
	     animAlpha=AnimationUtils.loadAnimation(this,R.anim.anim_alpha);
}
	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.swar:
			b1.startAnimation(animAlpha);
			Intent intent =new Intent (getApplicationContext(),HindiMainActivity.class);
			startActivity(intent);
			finish();
			break;
			
		case R.id.vyanjan:
			b2.startAnimation(animAlpha);
			Intent intent1 =new Intent(getApplicationContext(),VyanjanActivity.class);
			startActivity(intent1);
			finish();
			break;
			
		}
		
	}
	 @Override
	    public void onBackPressed() 
	    {      
		 Intent in1=new Intent(getApplicationContext(),MainActivity.class);
			startActivity(in1);
			finish();
	         
	    }
}
