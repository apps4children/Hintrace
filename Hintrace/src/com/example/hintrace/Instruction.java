package com.example.hintrace;

import com.example.hindigame.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class Instruction extends Activity{
	Button instruction;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.instructions);	
		instruction=(Button)findViewById(R.id.button1);
		instruction.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent(Instruction.this,MainActivity.class);
				startActivity(intent);
				finish();

			}
		});
	}

}