package gaussjordan;

import java.util.ArrayList;

public class Vector {
	private static ArrayList<Integer> vector;
	
	public Vector(int dimension) {
		vector = new ArrayList<>();
		
		for(int i = 0; i < dimension; i++) {
			vector.add(0);
		}
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
