import java.io.*; 
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.lang.Object;
import java.awt.geom.Point2D;
import java.awt.Point;

class Manish {

  public static int[][] getPartMatrix(int[][] matrix, int col, int n){
  	int[][] partial = new int[n][n];
  	for (int i = 0 ; i < col; i++){
  		for (int j = 0 ; j < col ; j++){
  			partial[j][i] = matrix[j+1][i];
  		}
  	}
  	for (int i = col+1 ; i < n ; i++){
  		for (int j = 0 ; j < col ; j++){
  			partial[j][i] = matrix[j+1][i];
  		}
  	}
  	return partial;
  }

  public static int getDet(int[][] matrix, int n) {
  	int size = matrix.length;
  	int det = 0;
  	if (size == 2){
  		return matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1];
  	}
  	for (int k = 0 ; k < matrix.length ; k ++){
  		int sign = 1;
  		if ((k % 2) == 1)
  			sign = -1;
  		det += sign * matrix[k][0] * getDet(getPartMatrix(matrix, k, n-1), n-1);
  	}
  	return det;
  }

  public static void main(String[] args) throws FileNotFoundException, IOException  {
  
  	String filePath = "test2.txt";
    String line = null;

    FileReader fileReader = new FileReader(filePath);
    BufferedReader bufferedReader = new BufferedReader(fileReader);

    int numRows = Integer.parseInt(bufferedReader.readLine());
    int[][] matrix = new int[numRows][numRows];
    int j = 0;

    while ((line = bufferedReader.readLine()) != null){
        String[] oneline = line.split(" ");
        for (int i = 0 ;  i < numRows ; i++){
          int n = Integer.parseInt(oneline[i]);
          matrix[j][i] = n;
    	}
		j++;
	}
    bufferedReader.close();  

    int determinant = getDet(matrix, numRows);
    System.out.println(determinant);
  }
}