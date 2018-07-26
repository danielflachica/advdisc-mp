package gaussjordan;

public class Driver {
	public static void main(String[] args) {
		Integer dimension = 5;
		double[] arr = {3.1, 9.2, 28.2, 59.6, 18.7};
		Vector v;
		
		v = new Vector(dimension);
		v.show();
		
		v = new Vector(arr, arr.length);
		v.show();
	}
}
