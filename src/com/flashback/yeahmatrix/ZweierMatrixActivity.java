package com.flashback.yeahmatrix;

import com.flashback.yeahmatrix.Matrix.MatrizenBerechnung;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ZweierMatrixActivity extends Activity {

	private EditText etA11, etA12, etA21, etA22;
	private Button btnCalc;
	private TextView tvErgebnis, tvInverse;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zweier_matrix);
		
		etA11 = (EditText)findViewById(R.id.etA11);
		etA12 = (EditText)findViewById(R.id.etA12);
		etA21 = (EditText)findViewById(R.id.etA21);
		etA22 = (EditText)findViewById(R.id.etA22);
		btnCalc = (Button)findViewById(R.id.btnBerechneDetZM);
		tvErgebnis = (TextView)findViewById(R.id.tvErgebnis);
		tvInverse = (TextView)findViewById(R.id.tvInverse);
		
		btnCalc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				int a11 = 0;
				int a12 = 0;
				int a21 = 0;
				int a22 = 0;
				int determinante = 0;
				boolean inverseD = false;
				
				MatrizenBerechnung mb = new MatrizenBerechnung();
				
				try {
					a11 = Integer.parseInt(etA11.getText().toString());
					a12 = Integer.parseInt(etA12.getText().toString());
					a21 = Integer.parseInt(etA21.getText().toString());
					a22 = Integer.parseInt(etA22.getText().toString());
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "Fehlerhafte Werteingabe!", Toast.LENGTH_LONG).show();
				}
				
				try {
					//MatrizenBerechnung mb = new MatrizenBerechnung();
					determinante = mb.berechneDeterminateZweierMatrix(a11, a12, a21, a22);
					inverseD = mb.existiertInverse(determinante);
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "Berechnung fehlgeschlagen!", Toast.LENGTH_LONG).show();
				}		
		
				tvErgebnis.setText("Die Determinate lautet: " + String.valueOf(determinante));
				tvInverse.setText("Existiert eine Inverse?: " + String.valueOf(inverseD));
			}
		});
	}

	public void clearAll() {
		etA11.setText("");
		etA12.setText("");
		etA21.setText("");
		etA22.setText("");
		tvErgebnis.setText("");
		tvInverse.setText("");
		tvErgebnis.setHint(getResources().getString(R.string.ergebnis));
		tvInverse.setHint(getResources().getString(R.string.inverse));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.zweier_matrix, menu);
		return true;
	}
	
	@Override
	 public boolean onOptionsItemSelected(MenuItem item) {
		 switch (item.getItemId()) {
	        case R.id.action_clear:
	        	clearAll();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	     }
	 }
}