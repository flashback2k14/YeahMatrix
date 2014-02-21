package com.flashback.yeahmatrix;

import java.text.NumberFormat;
import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.flashback.yeahmatrix.Matrix.MatrizenBerechnung;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class ZweierMatrixActivity extends Activity {

	private EditText etA11, etA12, etA21, etA22;
	private Button btnCalc;
	private TextView tvErgebnis, tvInverse;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zweier_matrix);
		setTitle(R.string.zweierMatrix);
		setTitleColor(getResources().getColor(R.color.headlineWhite));
		//Tinted Statusbar
		SystemBarTintManager stm = new SystemBarTintManager(this);
		stm.setStatusBarTintEnabled(true);
		stm.setStatusBarTintColor(getResources().getColor(R.color.etUlineColor));
					
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
				boolean exiInverseD = false;
				boolean userInputOk = false;
				boolean calcOperationDetOk = false;
				boolean calcOperationInvOk = false;
				String detA = "";
				String inverse = "";
				
				MatrizenBerechnung mb = new MatrizenBerechnung();
				
				try {
					a11 = Integer.parseInt(etA11.getText().toString());
					a12 = Integer.parseInt(etA12.getText().toString());
					a21 = Integer.parseInt(etA21.getText().toString());
					a22 = Integer.parseInt(etA22.getText().toString());
					userInputOk = true;
				} catch (Exception e) {
					Crouton.makeText(ZweierMatrixActivity.this, "Fehlerhafte Werteingabe!", Style.ALERT).show();
				}
				
				try {
					determinante = mb.berechneDeterminateZweierMatrix(a11, a12, a21, a22);
					exiInverseD = mb.existiertInverse(determinante);
					calcOperationDetOk = true;
				} catch (Exception e) {
					Crouton.makeText(ZweierMatrixActivity.this, "Berechnung fehlgeschlagen!", Style.ALERT).show();
				}		
				
				try {
					inverse = mb.berechnungInverseZweiterMatrix(determinante, a11, a12, a21, a22);
					calcOperationInvOk = true;
				} catch (Exception e) {
					Crouton.makeText(ZweierMatrixActivity.this, "Berechnung Inverse fehlgeschlagen!", Style.ALERT).show();
				}
			
				if (userInputOk && calcOperationDetOk && calcOperationInvOk) {
					Locale locale = Locale.GERMANY;
					detA = NumberFormat.getNumberInstance(locale).format(determinante);
					tvErgebnis.setText("Die Determinate lautet: " + detA);
				
					if (exiInverseD) {
						tvInverse.setText("Die Inverse lautet:\n\n" + inverse);
					} else {
						tvInverse.setText("Es existiert keine Inverse.");
					}
				}
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
	
	@Override
	public void onDestroy() {
		Crouton.cancelAllCroutons();
		super.onDestroy();
	}
}