package gaussjordan;
 import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
 public class Matrix 
{
	private double matrix[][];
	private int rows;
	private int columns;
	
	public double[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(double[][] matrix) {
		this.matrix = matrix;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	
	public Matrix(int rows, int cols)
	{
		this.rows = rows;
		this.columns = cols;
		
		this.matrix = new double[rows][cols];
		
		for(int row = 0; row < this.rows; row++)
		{
			for(int col = 0; col < this.columns; col++)
			{
				matrix[row][col] = 0.0;
			}
		}
	}
	
	public Matrix(double [][] matrix)
	{
		this.matrix = matrix;
		this.rows = matrix.length;
		this.columns = matrix[0].length;
	}

	
	
	public Matrix(int dimension)
	{
		rows = dimension;
		columns = dimension;
		
		matrix = new double[rows][columns];
		
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
		
		matrix = new double[rows][columns];
		
		ArrayList<Vector>tempMatrix = Vector.make_matrix(list);
		for(int row = 0; row < rows; row++)
		{
			for(int col = 0; col < columns; col++)
			{
				matrix[row][col] = tempMatrix.get(row).getData().get(col);
			}
		}
		
	}
	
	public Matrix times(Matrix other)
	{
		if(this.getColumns() != other.getRows())
		{
			System.out.println("\nERROR: Size mismatch between matrices.\n");
			return null;
		}
		
		Matrix productMatrix = new Matrix(this.getRows(), other.getColumns());
		productMatrix.showMatrix();
		
		for(int pRow = 0; pRow < productMatrix.getRows(); pRow++)
		{
			for(int pCol = 0; pCol < productMatrix.getColumns(); pCol++)
			{
					System.out.println("For row " + pRow + " and col " + pCol);
					for(int col = 0; col < this.getColumns(); col++)
					{
						productMatrix.matrix[pRow][pCol] += this.matrix[pRow][col] * other.matrix[col][pCol];
					}
			}
		}

		
		return productMatrix;
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
	
	public void showMatrix(double [][] matrix)
	{	
		System.out.println("No. of rows: " + matrix.length);
		System.out.println("No. of cols: " + matrix[0].length);
		
		for(int row = 0; row < matrix.length; row++)
		{
			for(int col = 0; col < matrix[0].length; col++)
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
        
        private double [][] mergeMatrices(double [][] matrix1, double [][] matrix2)
        {
        	double [][] mergedMatrix = new double [matrix1.length][matrix1[0].length+ matrix2[0].length];
        	
        	for(int i = 0; i < matrix1.length; i++)
        	{
        		for(int j = 0; j < matrix1[0].length; j++)
        		{
        			mergedMatrix[i][j] = matrix1[i][j];
        		}
        		for(int k = matrix1[0].length; k < mergedMatrix[0].length; k++)
        		{
        			mergedMatrix[i][k] = matrix2[i][k-matrix1[0].length];
        		}
        	}
        	
        	return mergedMatrix;
        }
        
        public Matrix inverse()
        {
        	if(this.rows != this.columns || det() == 0)
        		return null;
        	
        	Matrix identityMatrix = new Matrix(this.rows);
        	
        	//System.out.println("Inverse matrix (identity) initialized: \n" );
        	//identityMatrix.showMatrix();
        	
        	double [][] merged = mergeMatrices(this.matrix, identityMatrix.matrix);
        	Matrix mergedMatrix = new Matrix(merged);
        	
        	
        	ref(mergedMatrix.matrix);
        	rref(mergedMatrix.matrix);
        	
        	double [][] inverse = new double[mergedMatrix.getRows()][];
        	
        	for(int i = 0; i < mergedMatrix.getRows(); i++)
        	{
        		inverse[i] = Arrays.copyOfRange(mergedMatrix.matrix[i],mergedMatrix.getColumns()/2 ,mergedMatrix.getColumns());
        	}
        	Matrix inverseMatrix = new Matrix(inverse);
        	
        	
        	return inverseMatrix;
        }
        
        public void ref(double [][] matrix)
        {
        	
        	int i = 0;
        	while(i < matrix.length)
        	{
        		if(matrix[i][i] == 0)
        		{
        			int pivot = i;
        			
        			for(int y = i+1; y < matrix.length; y++)
        			{
        				if(Math.abs(matrix[y][i]) > Math.abs(matrix[pivot][i]))
        				{
        					pivot = y;
        				}
        			}
        			if(pivot != i)
        			{
        				swap(matrix, pivot, i);
        			}
        		}
        		/*
        		 * If the diagonal element is not 1, divide it by itself to get 1. 
        		Then divide the rest of the row by that divisor.
        		*/
        		if(matrix[i][i] != 1)
        		{
        			Vector v = new Vector(matrix[i], matrix[i].length);
        			v.scale(1.0/matrix[i][i]);
        			matrix[i] = v.getAsArray();

        		}
        		
        		for(int lowerRow = i+1; lowerRow < matrix.length; lowerRow++)
        		{
        			if(matrix[lowerRow][i] != 0.0)
        			{
        				Vector scaledRow = new Vector(matrix[i], matrix[i].length);
        				
        				double scalar = matrix[lowerRow][i]*-1;
        				
        				scaledRow.scale(scalar);
        				
        				Vector rowAdd = new Vector(matrix[lowerRow], matrix[lowerRow].length);
 
        				//rowAdd.scale(-1);
        				//identityRowAdd.scale(-1);
        				
        				rowAdd = rowAdd.add(scaledRow);
        				
        				matrix[lowerRow] = rowAdd.getAsArray();
        				
        				
        			}
        		}
        		//System.out.println("Matrix at " + i + ": \n");
        		//this.showMatrix();
        		
        		i++;

        	}
        	
        }
        
        public void rref(double matrix[][])
        {
        	int i = matrix.length-1;
        	while(i >= 0)
        	{

        		
        		for(int upperRow = i-1; upperRow >= 0; upperRow--)
        		{
        			if(matrix[upperRow][i] != 0.0)
        			{
        				Vector scaledRow = new Vector(matrix[i], matrix[i].length);
        				
        				
        				double scalar = (matrix[upperRow][i]*-1);
        				
        				scaledRow.scale(scalar);
        				
        				Vector rowAdd = new Vector(matrix[upperRow], matrix[upperRow].length);
        				
        				//rowAdd.scale(-1);
        				//identityRowAdd.scale(-1);
        				
        				rowAdd = rowAdd.add(scaledRow);
        				
        				matrix[upperRow] = rowAdd.getAsArray();
        				
        				
        			}
        		}
        		
        		i--;
        	}
        }
        
        private void swap(double matrix[][], int row1, int row2)
        {
        	double temp[] = matrix[row1];
        	matrix[row1] = matrix[row2];
        	matrix[row2] = temp;
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