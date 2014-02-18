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
	 * Inverse simple 
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
}