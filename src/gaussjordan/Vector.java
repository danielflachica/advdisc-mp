package gaussjordan;

import java.util.ArrayList;

/*
 * Vector class implemented by
 * GALLEGA, Rgee
 * LACHICA, Daniel
 * RICANOR, Marl
 *
 * Submitted on: 
 * ADVDISC X22
 */

public class Vector {
	private static ArrayList<Integer> vector;
	
	/* default constructor */
	public Vector(int dimension) {
		vector = new ArrayList<>();
		
		for(int i = 0; i < dimension; i++) {
			vector.add(0);
		}
	}
	
	/* constructor */
	public Vector(double[] array, int dimension) {
		vector = new ArrayList<>();
		
		//convert primitive double[] array to Vector class??
	}
	
	/* Gauss-Jordan Elimination function */
	Vector Gauss_Jordan(ArrayList<Vector> vectors, int dimension, Vector constants) {
		Vector v = new Vector(dimension);
		
		return v;		
	}
	
	
	/* A helper function to print a given vector */
	public void show() {
		System.out.print("[");
		for(int i = 0; i < vector.size(); i++) {
			System.out.print(vector.get(i));
			if(i < vector.size()-1)
				System.out.print(", ");
		}
		System.out.println("]\n");
	}
}
