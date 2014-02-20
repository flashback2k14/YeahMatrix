package com.flashback.yeahmatrix;

import java.text.NumberFormat;
import java.util.Locale;

import com.flashback.yeahmatrix.Matrix.MatrizenBerechnung;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DreierMatrixActivity extends Activity {

	private EditText etA11, etA12, etA13, etA21, etA22, etA23, etA31, etA32, etA33;
	private Button btnCalcDM;
	private TextView tvErgebnisDM, tvInverseDM;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dreier_matrix);
		setTitle(R.string.dreiermatrix);
		setTitleColor(getResources().getColor(R.color.headlineWhite));
		
		etA11 = (EditText)findViewById(R.id.etA11DM);
		etA12 = (EditText)findViewById(R.id.etA12DM);
		etA13 = (EditText)findViewById(R.id.etA13DM);
		etA21 = (EditText)findViewById(R.id.etA21DM);
		etA22 = (EditText)findViewById(R.id.etA22DM);
		etA23 = (EditText)findViewById(R.id.etA23DM);
		etA31 = (EditText)findViewById(R.id.etA31DM);
		etA32 = (EditText)findViewById(R.id.etA32DM);
		etA33 = (EditText)findViewById(R.id.etA33DM);
		btnCalcDM = (Button)findViewById(R.id.btnCalcDM);
		tvErgebnisDM = (TextView)findViewById(R.id.tvErgebnisDM);
		tvInverseDM = (TextView)findViewById(R.id.tvInverseDM);
		
		btnCalcDM.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				int a11 = 0;
				int a12 = 0;
				int a13 = 0;
				int a21 = 0;
				int a22 = 0;
				int a23 = 0;
				int a31 = 0;
				int a32 = 0;
				int a33 = 0;
				int determinante = 0;
				boolean exiInverse = false;
				String detA = "";
				String inverse = "";
				
				MatrizenBerechnung mb = new MatrizenBerechnung();
				
				try {
					a11 = Integer.parseInt(etA11.getText().toString());
					a12 = Integer.parseInt(etA12.getText().toString());
					a13 = Integer.parseInt(etA13.getText().toString());
					a21 = Integer.parseInt(etA21.getText().toString());
					a22 = Integer.parseInt(etA22.getText().toString());
					a23 = Integer.parseInt(etA23.getText().toString());
					a31 = Integer.parseInt(etA31.getText().toString());
					a32 = Integer.parseInt(etA32.getText().toString());
					a33 = Integer.parseInt(etA33.getText().toString());
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "Fehlerhafte Werteingabe!", Toast.LENGTH_LONG).show();
				}
				
				try {
					determinante = mb.berechneDeterminateDritterMatrix(a11, a12, a13, a21, a22, a23, a31, a32, a33);
					exiInverse = mb.existiertInverse(determinante);
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "Berechnung fehlgeschlagen!", Toast.LENGTH_LONG).show();
				}
				
				try {
					inverse = mb.berechnungInverseDreierMatrix(determinante, a11, a12, a13, a21, a22, a23, a31, a32, a33);
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "Berechnung Inverse fehlgeschlagen!", Toast.LENGTH_LONG).show();
				}
			
				Locale locale = Locale.GERMANY;
				detA = NumberFormat.getNumberInstance(locale).format(determinante);
				tvErgebnisDM.setText("Die Determinate lautet: " + detA);
							
				if (exiInverse) {
					tvInverseDM.setText("Die Inverse lautet:\n\n" + inverse);
				} else {
					tvInverseDM.setText("Es existiert keine Inverse.");
				}
			}
		});
	}

	public void clearAll() {
		etA11.setText("");
		etA12.setText("");
		etA13.setText("");
		etA21.setText("");
		etA22.setText("");
		etA23.setText("");
		etA31.setText("");
		etA32.setText("");
		etA33.setText("");
		tvErgebnisDM.setText("");
		tvInverseDM.setText("");
		tvErgebnisDM.setHint(getResources().getString(R.string.ergebnis));
		tvInverseDM.setHint(getResources().getString(R.string.inverse));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.dreier_matrix, menu);
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
