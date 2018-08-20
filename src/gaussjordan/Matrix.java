package gaussjordan;
 import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
 public class Matrix 
{
	private Double matrix[][];
	private int rows;
	private int columns;
	
	public Matrix(int dimension)
	{
		rows = dimension;
		columns = dimension;
		
		matrix = new Double[rows][columns];
		
		for(int row = 0; row < rows; row++)
		{
			for(int col = 0; col < columns; col++)
			{
				if(row == col)
				{
					matrix[row][col] = (double) 1;
				}
				else
				{
					matrix[row][col] = (double) 0;
				}
			
			}
		}
	}
	
	public Matrix(ArrayList<Vector>list, int dimension)
	{
		rows = dimension;
		columns = list.size();
		
		matrix = new Double[rows][columns];
		
		ArrayList<Vector>tempMatrix = Vector.make_matrix(list);
		for(int row = 0; row < rows; row++)
		{
			for(int col = 0; col < columns; col++)
			{
				matrix[row][col] = tempMatrix.get(row).getData().get(col);
			}
		}
		
	}
	
	public void showMatrix()
	{	
		System.out.println("No. of rows: " + rows);
		System.out.println("No. of cols: " + columns);
		
		for(int row = 0; row < rows; row++)
		{
			for(int col = 0; col < columns; col++)
			{
				System.out.print(matrix[row][col] + " ");
			}
			System.out.println("\n");
		}
		
	}
        
        public double det(){
            double det = 1.00;
        
            ArrayList<Vector> list = new ArrayList();
            
            translateToVector(list);
            
            System.out.println("Converted to a List of Vectors");
            
            for(int i = 0; i < matrix.length; i++)
                System.out.println(Arrays.toString(list.get(i).getData().toArray()));
            
            det = Gauss_Jordan(list,list.size(),det);
            System.out.println("Determinant at det fxn is: "+det);
            det = 1/det;
            System.out.println("Determinant after over 1 is: "+det);
            return det;
        }
        
        public static double swap(ArrayList<Vector> vectors,int v1, int v2,double det){        
            Collections.swap(vectors, v1, v2);
            System.out.println("Det Before Swapping: "+det);
            
            det*=-1;
            
            System.out.println("Det After Swapping: "+det);
            
            return det;
            
        }
        
        public double solve(ArrayList<Vector> vectors, int rowStart, double det){
            double[] tempArr = new double[vectors.get(rowStart).getDimension()];
            
            for(int n = rowStart+1; n < vectors.size(); n++){ //gets the vector
                if(vectors.get(n).getData().get(rowStart) != 0.00){// will only do row addition if the candidate row is not 0.00
                    double scale = vectors.get(n).getData().get(rowStart);
                    
                    System.out.println("Det Before Row Addition: "+det);
              
                    //updates the determinant only if you need to scale
                    //det*=(scale);
                    
                    System.out.println("Det After Row Addition: "+det);
                    
                    //scale the vectors
                    for(int m = 0; m < vectors.get(rowStart).getDimension(); m++){
                        tempArr[m] = vectors.get(rowStart).getData().get(m) * scale * -1;
                    }
                
                    Vector temp = new Vector(tempArr,tempArr.length);
                    // performs row addition to the vector 
                    vectors.set(n, temp.add(vectors.get(n)));
                    
                }
            }
            
            return det;
        }
        
        public double ref(ArrayList<Vector> vectors, int dimension, double det){
            int i =0;
            
            while(i < vectors.size()){
                //scale the row accordingly
                
                if(vectors.get(i).getData().get(i) == 0){ // if diagonal is 0, swap it!
                    // find pivot row 
                    int max = i;
                
                    for (int y = i+1; y < dimension; y++) {
                        //System.out.println("Comparing: "+Math.abs(vectors.get(y).data.get(i))+" AND "+Math.abs(vectors.get(max).data.get(i)));
                        if (Math.abs(vectors.get(y).getData().get(i)) > Math.abs(vectors.get(max).getData().get(i))) {
                            max = y;
                        }
                    }
                    //System.out.println("Swapping: "+max+" and "+i);
                
                    if(max != i)
                        det = swap(vectors,max,i,det);
                }
                
/************************************************************************************************************/   

                double scale = 1.00;
                
                for(int x = i; x < dimension; x++){ // for the row being used
                    if(i == x && vectors.get(i).getData().get(x) != 0.00){ // part of the diagonal
                        scale = vectors.get(i).getData().get(x);
                        vectors.get(i).getData().set(x, 1.00);

                        System.out.println("Det Before Scaling to 1: "+det);
                        
                        det/=scale;
                        
                        System.out.println("Det After Scaling to 1: "+det);
                        
                        //System.out.println("Scale @ index"+i+": "+scale);
                        //System.out.println("elemt contains at index"+i+": "+vectors.get(i).data.get(x));
                        //System.out.println("Constant @ index"+i+": "+constants.data.get(x));
                    }else
                        vectors.get(i).getData().set(x,vectors.get(i).getData().get(x)/scale);
                }
                
                det = solve(vectors,i,det); // doing the row operations on the matrix
                
                
                //System.out.println(i + "th Iteration for Vector: "+Arrays.toString(vectors.get(i).data.toArray()));
                //System.out.println(i + "th Iteration for Constants: "+Arrays.toString(constants.data.toArray()));
                //System.out.println(Arrays.toString(constants.data.toArray()));
                //System.out.println("After "+i+"th iteration, matrix looks like: ");
                for(int f = 0; f < dimension;f++)
                    System.out.println(Arrays.toString(vectors.get(f).getData().toArray()));
//                System.out.println("After "+i+"th iteration, constants looks like: ");
//                for(int f = 0; f < constants.dimension ;f++)
//                    System.out.println(constants.data.get(f));
                i++;
            }
            
            return det;
        }
       
        
        public double Gauss_Jordan(ArrayList<Vector> vectors, int dimension,double det){
                 
            if(vectors.size() != dimension)
                return 0.00;
            
            
            det = ref(vectors, dimension,det);
            //det = rref(vectors, dimension,det);
            System.out.println("Determinant at Gauss Jordan is: "+det);
            return det;
        }

        private void translateToVector(ArrayList<Vector> list) {
            
            for(int i = 0; i < matrix.length; i++){
                Vector v;
                
                double[] vector = new double[matrix[i].length];
                
                for(int j =0; j < matrix[i].length; j++){
                   vector[j] = matrix[i][j]; 
                }
                v = new Vector(vector,matrix[i].length);
                list.add(v); 
            }
        }
 }