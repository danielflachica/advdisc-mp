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
	private ArrayList<Double> data;
	private Integer dimension;
	
	/* default constructor */
	public Vector(Integer dimension) {
		this.data = new ArrayList<>();
		this.dimension = dimension;
		
		for(int i = 0; i < this.dimension; i++) {
			data.add(0.00);
		}
	}
	
	/* constructor */
	public Vector(double[] array, int dimension) {
		this.data = new ArrayList<>();
		this.dimension = dimension;
		
		for(int i = 0; i < this.dimension; i++) {
			data.add(array[i]);
		}
	}
	
	/* Gauss-Jordan Elimination function */
	Vector Gauss_Jordan(ArrayList<Vector> vectors, int dimension, Vector constants) {
		Vector v = new Vector(dimension);
		
		return v;		
	}
	
	/* span function */
	int span(ArrayList<Vector> vectors, int dimension) {
		
		return -1;
	}
	
	
	/* A helper function to print this vector */
	public void show() {
		System.out.print("[");
		for(int i = 0; i < this.dimension; i++) {
			System.out.print(data.get(i));
			if(i < this.dimension-1)
				System.out.print(", ");
		}
		System.out.println("]\n");
	}
}
