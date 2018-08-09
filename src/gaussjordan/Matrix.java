package gaussjordan;
 import java.util.ArrayList;
import java.util.List;
 public class Matrix 
{
	private Double matrix[][];
	private int rows;
	private int columns;
	
	public Double[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(Double[][] matrix) {
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
		
		this.matrix = new Double[rows][cols];
		
		for(int row = 0; row < this.rows; row++)
		{
			for(int col = 0; col < this.columns; col++)
			{
				matrix[row][col] = 0.0;
			}
		}
	}
	
	public Matrix(Double [][] matrix)
	{
		this.matrix = matrix;
		this.rows = matrix.length;
		this.columns = matrix[0].length;
	}

	
	
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
 }