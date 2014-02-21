package com.flashback.yeahmatrix;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartActivity extends Activity {

	private Button btnZweierMatrix, btnDreierMatrix;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		//Tinted Statusbar
		SystemBarTintManager stm = new SystemBarTintManager(this);
		stm.setStatusBarTintEnabled(true);
		stm.setStatusBarTintColor(getResources().getColor(R.color.etUlineColor));
				
		btnZweierMatrix = (Button)findViewById(R.id.btnZweierM);
		btnDreierMatrix = (Button)findViewById(R.id.btnDreierM);
		
		btnZweierMatrix.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent startZM = new Intent(StartActivity.this, ZweierMatrixActivity.class);
				startActivity(startZM);
			}
		});
		
		btnDreierMatrix.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent startDM = new Intent(StartActivity.this, DreierMatrixActivity.class);
				startActivity(startDM);				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
		return false;
	}
}