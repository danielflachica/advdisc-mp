package gaussjordan;

import java.util.ArrayList;
import java.util.List;

public class Matrix 
{
	private Double matrix[][];
	private int rows;
	private int columns;
	
	public Matrix(int dimension)
	{
		
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

}
