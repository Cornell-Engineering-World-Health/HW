import java.util.*;
import java.lang.*;
import java.io.*;
import static java.lang.Math.*;

import java.awt.Point;

public class hw1 {

	public static void main(String[] args) throws Exception {
		// Overlaps
		FileReader fr = new FileReader("test3.txt");
		BufferedReader input = new BufferedReader(fr);

		int minX1 = 21, minY1 = 21;
		int maxX1 = 0, maxY1 = 0;
		int minX2 = 21, minY2 = 21;
		int maxX2 = 0, maxY2 = 0;

		try {

			String line = input.readLine();
			int lineNum = 0;
			int row = Integer.parseInt(line);
			int s1[][] = new int[row][4];
			while (line != null) {
				if (lineNum == row){
					break;
				}
				else {
					String[] num = line.split(" ");
					for (int r = 0; r < row; r++) {
						for (int col = 0; col < num.length; col++) {
							int coord = Integer.parseInt(num[col]);
							s1[r][col] = coord;
							if (col % 2 == 0) {
								if (coord < minX1) minX1 = coord;
								if  (coord > maxX1) maxX1 = coord;
							}
							else {
								if (coord < minY1) minY1 = coord;
								if  (coord > maxY1) maxY1 = coord;
							}
						}
					}
				}
			}
			row = Integer.parseInt(line);
			int s2[][] = new int[row][4];
			while (line != null) {
				String[] num = line.split(" ");	
				for (int r = 0; r < row; r++) {
					for (int col = 0; col < num.length; col++) {
						int coord = Integer.parseInt(num[col]);
						s2[r][col] = coord;
						if (col % 2 == 0) {
							if (coord < minX1) minX1 = coord;
							if  (coord > maxX1) maxX1 = coord;
						}
						else {
							if (coord < minY1) minY1 = coord;
							if  (coord > maxY1) maxY1 = coord;
						}
					}
				}
			}
			if (overlaps(s1, s2)) {
				int maxX = Math.min(maxX1, maxX2);
				int minX = Math.max(minX1, minX2);
				int maxY = Math.min(maxY1, maxY2);
				int minY = Math.max(minY1, minY2);

				ArrayList<Point> inter = new ArrayList<Point>();
				int seg = 0;
				for (int y = minY; y < maxY; y++) {
					for (int x = minX; x < maxX; x++) {	
						if (inSeg(x, y, s1[seg][0], s1[seg][1], s1[seg][2], s1[seg][3]) && 
								inSeg(x, y, s2[seg][0], s2[seg][1], s2[seg][2], s2[seg][3])) {
							inter.add(new Point(x,y));
						}
					}
				}
				for (int i = 0; i < inter.size(); i++) {
					System.out.println("(" + inter.get(i).x + "," + inter.get(i).y + ")");

				}
			}
		}
		catch (Exception ex) {
			System.out.println("The code throws an exception");
			System.out.println(ex.getMessage());
		}
		input.close();
	}
	
	
//			//Test determinant function
//			FileReader fr = new FileReader("test2.txt");
//			BufferedReader input = new BufferedReader(fr);
//			try {
//				String line = input.readLine();
//				int row = Integer.parseInt(line);
//				int[][] matrix = new int[row][row];
//				int c = 0;
//				while (line != null) {
//					String[] num = line.split(" ");
//					for(int i = 0; i < num.length; i++) {
//						matrix[c][i] = Integer.parseInt(num[i]);
//					}
//					c++;
//				}
//			}
//			catch (Exception ex) {
//				System.out.println("The code throws an exception");
//				System.out.println(ex.getMessage());
//			}
//			input.close();
//		}
//	}

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
			for (int r = 1; r < mlen; r++) {
				for (int c = 0; c < mlen; c++) {
					if (c < i) small[r-1][c] = matrix[r][c];
					else if (c > i) small[r-1][c-1] = matrix[r][c];
				}
			}
			det += pow(-1, i) * determinant(small);
		}

		return det;
	}

	// Returns of overlaps
	public static boolean overlaps(int[][] shape1, int[][] shape2) {
		//find min/max x and y for s1, s2
		int minX1 = 21, minY1 = 21;
		int maxX1 = 0, maxY1 = 0;
		int minX2 = 21, minY2 = 21;
		int maxX2 = 0, maxY2 = 0;

		//S1,S2 min/max
		for (int row = 0; row < shape1.length; row++) {
			for (int col = 0; col < shape1[row].length; col++) {

			}
		}
		for (int i = 0 ; i < shape1.length; i++) {
			if (i % 2 == 0) {
				if (shape1[i][0] < minX1) minX1 = shape1[i][0];
				if (shape1[i][0] > maxX1) maxX1 = shape1[i][0];
				if (shape2[i][0] < minX2) minX2 = shape2[i][0];
				if (shape2[i][0] > maxX2) maxX2 = shape2[i][0];
			}

			if (shape1[i][1] < minY1) minY1 = shape1[i][1];
			if (shape1[i][1] > maxY1) maxY1 = shape1[i][1];
			if (shape2[i][1] < minY2) minY2 = shape2[i][1];
			if (shape2[i][1] > maxY2) maxY2 = shape2[i][1];
		}
		// Overlaps
		boolean over = true;
		if (maxX1 < minX2) over = false;
		if (minX1 > maxX2) over = false;
		if (maxY1 < minY2) over = false;
		if (minY1 > maxY2) over = false;

		return over;
	}

	public static boolean inSeg(int x, int y, int x1, int y1, int x2, int y2) {
		double slope = (y2 - y1) / (x2 - x1);
		if (slope != 0) {
			double yinter = y1 - slope * x1;
			//double xinter = yinter / slope;
			double yplot = (slope * x) + yinter;
			if (x > Math.min(x1, x2) && x < Math.max(x1, x2) && yplot > Math.min(y1, y2) && yplot < Math.max(y1, y2)) {
				return true;
			}
		}
		else if (slope == 0) {
			if (x > Math.min(x1, x2) && x < Math.max(x1, x2) && y > y1) {
				return true;
			}
		}
		return false;
	}

}
