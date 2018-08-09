package gaussjordan; /*** REMOVE PACKAGE DECLARATION BEFORE SUBMITTING!!! ***/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
 * Vector class implemented by
 * GALLEGA, Rgee
 * LACHICA, Daniel
 * RICANOR, Marl
 *
 * Submitted on: 07/31/18
 * ADVDISC X22
 */

public class Vector {
	private ArrayList<Double> data;
	
	public ArrayList<Double> getData() {
		return data;
	}

	public Integer getDimension() {
		return dimension;
	}

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
	
	public Vector scale(double scalar)
	{
		for(int i = 0; i < this.data.size(); i++)
		{
			Double currentElem = this.data.get(i);
			Double scaledElem = currentElem * scalar;
			this.data.set(i, scaledElem);
		}
		
		return this;

	}
	
	public Vector add(Vector addend)
	{
		if(this.data.size() == addend.data.size())
		{
			for(int i = 0; i < this.data.size(); i++)
			{
				Double currentElem = this.data.get(i);
				Double addendElem = addend.data.get(i);
				this.data.set(i, currentElem + addendElem);
			}
		}
		else
		{
			System.out.println("Error: Size mismatch between the vectors.");
		}
		
		return this;
	}
	
	public static ArrayList<Vector> make_matrix(ArrayList<Vector> vectors) {
		// TODO Auto-generated method stub
		
		ArrayList<Vector>matrix = new ArrayList<Vector>();
		
		for(int i = 0; i < vectors.get(0).data.size(); i++)
		{
			double[] arr = new double[vectors.size()];
			
			for(int j = 0; j < vectors.size(); j++)
			{
				arr[j] = vectors.get(j).data.get(i);
			}
				
			
			matrix.add(new Vector(arr, arr.length));
	
			
		}
		
		
		return matrix;
	}
        
        public static void swap(ArrayList<Vector> vectors,Vector constants,int v1, int v2){        
            Collections.swap(constants.data, v1, v2);
            Collections.swap(vectors, v1, v2);
            
        }
        
        public static void solve(ArrayList<Vector> vectors, Vector constants, int rowStart){
            double[] tempArr = new double[vectors.get(rowStart).dimension];
            
            for(int n = rowStart+1; n < vectors.size(); n++){ //gets the vector
                if(vectors.get(n).data.get(rowStart) != 0.00){// will only do row addition if the candidate row is not 0.00
                    double scale = vectors.get(n).data.get(rowStart);
                    //System.out.println("Scale for row addition: "+scale);
                    //scale the constants vector
                    double tempCons = constants.data.get(rowStart) * scale * -1; 
                    //scale the vectors
                    for(int m = 0; m < vectors.get(rowStart).dimension; m++){
                        tempArr[m] = vectors.get(rowStart).data.get(m) * scale * -1;
                    }
                
                    Vector temp = new Vector(tempArr,tempArr.length);
                    // performs row addition to the vector 
                    vectors.set(n, temp.add(vectors.get(n)));
                    // performs row addition to the constant 
                    constants.data.set(n, constants.data.get(n) + tempCons); //adjusts the vectors under the selected vector
                }
            }
        }
	
        public static void ref(ArrayList<Vector> vectors, int dimension, Vector constants){
            int i =0;
            
            while(i < vectors.size()){
                //scale the row accordingly
                
                if(vectors.get(i).data.get(i) == 0){ // if diagonal is 0, swap it!
                    // find pivot row 
                    int max = i;
                
                    for (int y = i+1; y < dimension; y++) {
                        //System.out.println("Comparing: "+Math.abs(vectors.get(y).data.get(i))+" AND "+Math.abs(vectors.get(max).data.get(i)));
                        if (Math.abs(vectors.get(y).data.get(i)) > Math.abs(vectors.get(max).data.get(i))) {
                            max = y;
                        }
                    }
                    //System.out.println("Swapping: "+max+" and "+i);
                
                    if(max != i)
                        swap(vectors,constants,max,i);
                }
                
/************************************************************************************************************/   

                double scale = 1.00;
                
                for(int x = i; x < dimension; x++){ // for the row being used
                    if(i == x && vectors.get(i).data.get(x) != 0.00){ // part of the diagonal
                        scale = vectors.get(i).data.get(x);
                        vectors.get(i).data.set(x, 1.00);
                        //System.out.println("Before constant scaling: "+constants.data.get(x));
                        constants.data.set(x, constants.data.get(x)/scale);
                        //System.out.println("After constant scaling: "+constants.data.get(x));
                        
                        //System.out.println("Scale @ index"+i+": "+scale);
                        //System.out.println("elemt contains at index"+i+": "+vectors.get(i).data.get(x));
                        //System.out.println("Constant @ index"+i+": "+constants.data.get(x));
                    }else
                        vectors.get(i).data.set(x,vectors.get(i).data.get(x)/scale);
                }
                
                solve(vectors, constants,i); // doing the row operations on the matrix
                
                
                //System.out.println(i + "th Iteration for Vector: "+Arrays.toString(vectors.get(i).data.toArray()));
                //System.out.println(i + "th Iteration for Constants: "+Arrays.toString(constants.data.toArray()));
                //System.out.println(Arrays.toString(constants.data.toArray()));
                //System.out.println("After "+i+"th iteration, matrix looks like: ");
//                for(int f = 0; f < dimension;f++)
//                    System.out.println(Arrays.toString(vectors.get(f).data.toArray()));
//                System.out.println("After "+i+"th iteration, constants looks like: ");
//                for(int f = 0; f < constants.dimension ;f++)
//                    System.out.println(constants.data.get(f));
                i++;
            }
        }
        

        public static void rref(ArrayList<Vector> vectors, int dimension, Vector constants){
            int i = vectors.size()-1; 
            //System.out.println("*************** AT RREF ********************");
            while(i >= 0){
                
                double[] tempArr = new double[vectors.get(i).dimension];
            
                if(vectors.get(i).data.get(i) != 0.00){// if the diagonal is not a zero
                    for(int n = i-1; n >= 0; n--){ //gets the vector
                        if(vectors.get(n).data.get(i) != 0.00){// will only do row addition if the candidate row is not 0.00
                            double scale = vectors.get(n).data.get(i);
                            //System.out.println("Scale for row addition: "+scale);
                            //scale the constants vector
                            double tempCons = constants.data.get(i) * scale * -1; 
                            //scale the vectors
                            for(int m = 0; m < vectors.get(i).dimension; m++){
                                tempArr[m] = vectors.get(i).data.get(m) * scale * -1;
                            }
                
                            Vector temp = new Vector(tempArr,tempArr.length);
                            // performs row addition to the vector 
                            vectors.set(n, temp.add(vectors.get(n)));
                            // performs row addition to the constant 
                            constants.data.set(n, constants.data.get(n) + tempCons); //adjusts the vectors under the selected vector
                        }
                    }
                }else
                    System.out.println("Vector "+i+"s diagonal is  0");
                               
               
                i--;
            }
        }
        
	 /* Gauss-Jordan Elimination function */

     public static Vector Gauss_Jordan(ArrayList<Vector> vectors, int dimension, Vector constants) {
		
		
            if(vectors.size() != dimension)
            {
            	System.out.println("\nNO SOLUTION\n");
            	return null;
            }  
            //else
            ref(vectors, dimension,constants);
            rref(vectors, dimension, constants);
                
            return constants;			

	}
	
	/* span function */
	public static int span(ArrayList<Vector> vectors, int dimension) {
		int span = 0;
		double[] c = {5,8,2};
		
        Vector vc = new Vector(c,c.length);
        vc = Vector.Gauss_Jordan(vectors, vc.dimension, vc);
        
        System.out.println();
        for(int i = 0; i < vectors.size();i++)
            System.out.println(Arrays.toString(vectors.get(i).data.toArray()));
        System.out.println();
        System.out.println(Arrays.toString(vc.data.toArray()));
        
        for(Vector v: vectors)
        {
        	boolean isNonZero = false;
        	
        	for(int i = 0; i < v.dimension; i++)
        	{
        		if(v.data.get(i) != 0)
        		{
        			isNonZero = true;
        			break;
        		}
        	}
        	if(isNonZero) span++;
        }
		
		return span;
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
	
	/**********************************************************************************/
	
	/* Driver Method */
	public static void main(String[] args) {
		Integer dimension = 5;
		double[] arr = {1, 2, 4};
        double[] arr2 = {1,3,0};
        double[] arr3 = {1,5,5};
                
        ArrayList<Vector> list = new ArrayList<>();
        Vector v = new Vector(arr,arr.length);
        Vector v1 = new Vector(arr2,arr2.length);
        Vector v2 = new Vector(arr3,arr3.length);

        list.add(v);
        list.add(v1);
        list.add(v2);
        System.out.println("Vectors before being put into matrix: ");
        for(Vector row:list)
        {
        	row.show();
        }
        ArrayList<Vector> transposedList = Vector.make_matrix(list);
        
        System.out.println("Vectors put into matrix: ");
        for(Vector row : transposedList)
        {
        	row.show();
        }
        
        int span = Vector.span(transposedList, transposedList.get(0).dimension);
        System.out.println("Span is " + span);
        
        Matrix m = new Matrix(4);
        System.out.println("\nInitializing m as an identity matrix: ");
        m.showMatrix();
        
        m = new Matrix(list,list.get(0).dimension);
        System.out.println("\nInitializing m from existing list of vectors: ");
        m.showMatrix();
                
		

		
	}

	
}
