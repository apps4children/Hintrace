package com.example.hintrace;

import com.example.hindigame.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.Window;
import android.widget.Button;

public class PlayAgain extends Activity implements OnClickListener{
	Animation animAlpha;
	Button b1,b2;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.second_level);
		
	     b1= (Button)findViewById(R.id.playagain);
	     b1.setOnClickListener(this);
	     b2=(Button)findViewById(R.id.exit1);
	     b2.setOnClickListener(this);
	   animAlpha=AnimationUtils.loadAnimation(this,R.anim.anim_alpha); 
}
	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.playagain:
			b1.startAnimation(animAlpha);
			Intent intent =new Intent (PlayAgain.this,SecondMain.class);
			startActivity(intent);
			finish();
			break;
		case R.id.exit1:
			
			b2.startAnimation(animAlpha);
			MainActivity.mainActivity.finish();
			finish();
			Intent in = new Intent(Intent.ACTION_MAIN);
			in.addCategory(Intent.CATEGORY_HOME);
			//in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(in);
			break;
			
		}
		
	}	
	 public void onBackPressed() 
	    {
			Intent in1=new Intent(getApplicationContext(),SecondMain.class);
			startActivity(in1);
			finish();
	    }
}