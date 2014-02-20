package com.flashback.yeahmatrix.Matrix;

public class MatrizenBerechnung {
	/*
	 * Determinante Faktor 2 Matrix
	 */
	public int berechneDeterminateZweierMatrix(int a11, int a12, int a21, int a22) {
		return ((a11 * a22) - (a12 * a21));
	}
	/*
	 * Faktor 3 Matrix
	 */
	public int berechneDeterminateDritterMatrix(int a11, int a12, int a13, int a21, int a22, int a23, int a31, int a32, int a33) {
		return ((a11 * a22 * a33) + (a12 * a23 * a31) + (a13 * a21 * a32) - (a13 * a22 * a31) - (a11 * a23 * a32) - (a12 * a21 * a33));
	}
	/*
	 * Pruefung, ob Inverse existiert 
	 */
	public boolean existiertInverse(int determinate) {
		boolean ergebnis;
		
		if (determinate != 0) {
			ergebnis = true;
		} else {
			ergebnis = false;
		}
		return ergebnis;
	}
	/*
	 * Berechnung der Inversen einer Faktor 2 Matrix
	 */
	public String berechnungInverseZweiterMatrix(int detA, int a11, int a12, int a21, int a22) {
		
		StringBuilder sb = new StringBuilder();
		sb.append("\t |" + a22 + ",\t" + (a12 * -1) + "|\n");
		sb.append("\t |" + (a21 * -1) + ",\t" + a11 + "| * 1 / " + detA + "\n");
	
		return sb.toString();
	}
	/*
	 * Berechnung der Inversen einer Faktor 3 Matrix
	 */
	public String berechnungInverseDreierMatrix(int detA ,int a11, int a12, int a13, int a21, int a22, int a23, int a31, int a32, int a33) {
		int aI11, aI12, aI13, aI21, aI22, aI23, aI31, aI32, aI33;
		
		aI11 = berechneDeterminateZweierMatrix(a22, a23, a32, a33);
		aI12 = berechneDeterminateZweierMatrix(a22, a23, a31, a33);
		aI13 = berechneDeterminateZweierMatrix(a21, a22, a31, a32);
		aI21 = berechneDeterminateZweierMatrix(a12, a13, a32, a33);
		aI22 = berechneDeterminateZweierMatrix(a11, a13, a31, a33);
		aI23 = berechneDeterminateZweierMatrix(a11, a12, a31, a32);
		aI31 = berechneDeterminateZweierMatrix(a12, a13, a22, a23);
		aI32 = berechneDeterminateZweierMatrix(a11, a13, a21, a23);
		aI33 = berechneDeterminateZweierMatrix(a11, a12, a21, a22);
		
		StringBuilder sb = new StringBuilder();
		sb.append("\t |" + aI11 + ",\t" + aI21 + ",\t" + aI31 + "|\n");
		sb.append("\t |" + aI12 + ",\t" + aI22 + ",\t" + aI32 + "| * 1 / " + detA + "\n");
		sb.append("\t |" + aI13 + ",\t" + aI23 + ",\t" + aI33 + "|\n");
	
		return sb.toString();
	}	
}