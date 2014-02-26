package com.flashback.yeahmatrix;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.readystatesoftware.systembartint.SystemBarTintManager;


public class StartActivity extends Activity {

	final Context context = this;
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
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
	       case R.id.action_settings:
	    	   openLicenseDialog();
	           return true;
	       default:
	           return super.onOptionsItemSelected(item);
	    }
	}
	
	public void openLicenseDialog() {
		//
		final Dialog dialog = new Dialog(context);
		dialog.setContentView(R.layout.licensedialog);
		dialog.setTitle("verwendete Bibliotheken");
		//
		TextView tv1 = (TextView)dialog.findViewById(R.id.tvCrouton);
		tv1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/keyboardsurfer/Crouton"));
				startActivity(i1);
			}
		});
		//
		TextView tv2 = (TextView)dialog.findViewById(R.id.tvSystemBarTint);
		tv2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/jgilfelt/SystemBarTint"));
				startActivity(i2);
			}
		});
		//
		Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
		// if button is clicked, close the custom dialog
		dialogButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		//
		dialog.show();
	}
}