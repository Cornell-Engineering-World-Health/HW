import java.util.*;
import java.lang.*;
import java.io.*;
import static java.lang.Math.*;

public class hw1 {

	public static void main(String[] args) throws Exception {
////		//Read file
//		int[][] matrix = new int[100][100];
//		File file = new File("test2.txt");
//		BufferedReader input = new BufferedReader(new FileReader(file));
//		try {
//			String line = input.readLine();
//			int lineNum = 0;
//			int row = 0;
//			while (line != null) {
//				lineNum++;
//				if (lineNum == 1){
//					row = Integer.parseInt(line);
//				}
//				else {
//					String[] num = line.split(" ");		//get numbers
//					for(int i = 0; i < num.length; i++) {
//						matrix[row][i] = Integer.parseInt(num[i]);
//					}
//					row++;
//				}
//			}
//		}
//		catch (Exception ex) {
//			System.out.println("The code throws an exception");
//			System.out.println(ex.getMessage());
//		}
//		input.close();

		//Test determinant function
		File file3 = new File("test3.txt");
		int[][] matrix = new int[100][100];
		File file = new File("test2.txt");
		BufferedReader input = new BufferedReader(new FileReader(file));
		try {
			String line = input.readLine();
			int lineNum = 0;
			int row = 0;
			while (line != null) {
				if (lineNum == 0) {
					row = Integer.parseInt(line);
					System.out.println("linenum0 " + lineNum);
					lineNum++;
					System.out.println("row " + row);
					System.out.println("linenum1 " + lineNum);
				}
				else {
					String[] num = line.split(" ");		//get numbers
					for(int i = 0; i < num.length; i++) {
						matrix[row][i] = Integer.parseInt(num[i]);
						System.out.println(Integer.parseInt(num[i]));
					}
					row++;
				}
			}
		}
		catch (Exception ex) {
			System.out.println("The code throws an exception");
			System.out.println(ex.getMessage());
		}
		input.close();
		System.out.println(determinant(matrix));
	}

	// Sort in ascending order
	public static void selection(int[] b) {
		for (int j = 0;  j < b.length - 1; j++) {
			int min = j;
			for (int i = j + 1; i < b.length; i++) {
				if (b[i] < b[min]) {
					min = i;
				}
				if (min != j) {			// swap
					int temp = b[i];
					b[i] = b[min];
					b[min] = b[temp];
				}
			}
		}
	}

	// Return determinant of nxn matrix
	public static int determinant(int[][] matrix) {
		int det = 0;
		int mlen = matrix.length;

		if (mlen == 1) return matrix[0][0];
		if (mlen == 2) return matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1];

		for (int i = 0; i < mlen; i++) {
			int[][] small = new int[mlen - 1][mlen - 1];
			for (int r = 1; r < mlen; r++) {			//iterate through rows
				for (int c = 0; c < mlen; c++) {
					if (c < i) small[r-1][c] = matrix[r][c];
					else if (c > i) small[r-1][c-1] = matrix[r][c];
				}
			}
			det += pow(-1, i) * determinant(small);
		}

		return det;
	}
	
	// Return overlapping vertices
	//Given multiD array
	//BBox array
	public void overlaps(int[][] shape1, int[][] shape2) {
		//find min/max x and y for s1, s2
		int minX1 = 21, minY1 = 21;
		int maxX1 = 0, maxY1 = 0;
		int minX2 = 21, minY2 = 21;
		int maxX2 = 0, maxY2 = 0;
		
		//S1,S2 min/max
		for (int i = 0 ; i < shape1.length; i++) {
			if (shape1[i][0] < minX1) minX1 = shape1[i][0];
			if (shape1[i][1] < minY1) minY1 = shape1[i][1];
			if (shape1[i][0] > maxX1) maxX1 = shape1[i][0];
			if (shape1[i][1] > maxY1) maxY1 = shape1[i][1];
			
			if (shape2[i][0] < minX2) minX2 = shape2[i][0]; 
			if (shape2[i][1] < minY2) minY2 = shape2[i][1];
			if (shape2[i][0] > maxX2) maxX2 = shape2[i][0];
			if (shape2[i][1] > maxY2) maxY2 = shape2[i][1];
		}
		//Overlaps?
		boolean over = true;
		if (maxX1 < minX2) over = false;
        if (minX1 > maxX2) over = false;
        if (maxY1 < minY2) over = false;
        if (minY1 > maxY2) over = false;
        
        if (!over) {
        	System.out.println("Nothing overlaps");
        }
        else {
        	if (minX1 < minX2)		//s2 right of s1; find intersection point
        	for(int i = minX1; i < minX2; i++) {
        		System.out.print("X values:" + i);
        	}
        }
        
	}

}